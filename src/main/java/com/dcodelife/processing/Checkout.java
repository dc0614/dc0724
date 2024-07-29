package com.dcodelife.processing;

import com.dcodelife.tools.Tool;
import com.dcodelife.tools.ToolStore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

/**
 * The Checkout class represents a tool checkout system.
 */
@NoArgsConstructor
public class Checkout {

    ToolStore toolStore = new ToolStore();
    @Getter
    @Setter
    String toolCode;
    @Getter
    @Setter
    int rentalDays;
    @Getter
    @Setter
    int discountPct;
    @Getter
    @Setter
    LocalDate checkoutDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy", Locale.US);

    private static final List<MonthDay> INDEPENDENCE_DAY = List.of(MonthDay.of(Month.JULY, 4));
    private static final BigDecimal DISCOUNT_FACTOR = BigDecimal.valueOf(100);

    public void validateToolCode(String toolCode) throws IllegalArgumentException {
        Tool currentTool = toolStore.findToolByCode(toolCode);
        if (currentTool == null) {
            throw new IllegalArgumentException("Tool with code " + toolCode + " does not exist.");
        } else {
            this.toolCode = toolCode;
        }
    }

    public void validateRentalDays(int rentalDays) throws IllegalArgumentException {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Please ensure that rental day count is 1 or greater.");
        } else {
            this.rentalDays = rentalDays;
        }
    }

    public void validateDiscountPct(int discountPct) throws IllegalArgumentException {
        if (discountPct < 0 || discountPct > 100) {
            throw new IllegalArgumentException("Please ensure discount percentage is between 0 and 100.");
        } else {
            this.discountPct = discountPct;
        }
    }

    /**
     * Processes the checkout of a tool and calculates the rental agreement.
     *
     * @param toolCode      The code of the tool being rented.
     * @param rentalDays    The number of days the tool will be rented for.
     * @param discountPct   The discount percentage applied to the rental charge.
     * @param checkoutDate  The date of tool checkout.
     * @return The rental agreement for the tool.
     */
    public RentalAgreement processCheckout(String toolCode, int rentalDays, int discountPct, LocalDate checkoutDate) {
        validateToolCode(toolCode);
        validateRentalDays(rentalDays);
        validateDiscountPct(discountPct);
        Tool currentTool = toolStore.findToolByCode(toolCode);

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        int chargeDays = getChargeDays(checkoutDate, dueDate, currentTool);
        BigDecimal preDiscCharge = calculatePreDiscountCharge(currentTool, chargeDays);
        BigDecimal discountAmount = calculateDiscountAmount(preDiscCharge);
        BigDecimal finalCharge = calculateFinalCharge(preDiscCharge, discountAmount);

        return createRentalAgreement(currentTool, dueDate, chargeDays, preDiscCharge, discountAmount, finalCharge);
    }

    private BigDecimal calculatePreDiscountCharge(Tool currentTool, int chargeDays) {
        return currentTool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));
    }

    private BigDecimal calculateDiscountAmount(BigDecimal preDiscCharge) {
        BigDecimal discountPercent = BigDecimal.valueOf(getDiscountPct()).divide(DISCOUNT_FACTOR);
        return preDiscCharge.multiply(discountPercent).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalCharge(BigDecimal preDiscCharge, BigDecimal discountAmount) {
        return preDiscCharge.subtract(discountAmount);
    }

    private String formatDate(LocalDate date) {
        return date.format(formatter);
    }

    /**
     * Creates a rental agreement for a tool.
     *
     * @param currentTool    The tool being rented.
     * @param dueDate        The date when the tool is due.
     * @param chargeDays     The number of chargeable days between the checkout date and due date for the given tool.
     * @param preDiscCharge  The pre-discount charge for renting the tool.
     * @param discountAmount The discount amount applied to the rental charge.
     * @param finalCharge    The final charge for renting the tool after applying the discount.
     * @return The rental agreement for the tool.
     */
    private RentalAgreement createRentalAgreement(Tool currentTool, LocalDate dueDate, int chargeDays,
                                                  BigDecimal preDiscCharge, BigDecimal discountAmount, BigDecimal finalCharge) {
        RentalAgreement agreement = new RentalAgreement();
        agreement.setToolCode(currentTool.getToolCode());
        agreement.setToolType(currentTool.getType());
        agreement.setToolBrand(currentTool.getBrand());
        agreement.setRentalDays(getRentalDays());
        agreement.setCheckoutDate(getCheckoutDate());
        agreement.setDueDate(dueDate);
        agreement.setDailyCharge(currentTool.getDailyCharge());
        agreement.setChargeDays(chargeDays);
        agreement.setPreDiscountCharge(preDiscCharge);
        agreement.setDiscountPercent(getDiscountPct());
        agreement.setDiscountAmount(discountAmount);
        agreement.setFinalCharge(finalCharge);
        return agreement;
    }

    /**
     * Calculates the number of chargeable days between the checkout date and due date for a given tool.
     *
     * @param checkoutDate The date of tool checkout.
     * @param dueDate The date when the tool is due.
     * @param currentTool The tool being rented.
     * @return The number of chargeable days between the checkout date and due date for the given tool.
     */
    private int getChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool currentTool) {
        LocalDate dayAfterCheckout = checkoutDate.plusDays(1);
        long totalDays = ChronoUnit.DAYS.between(dayAfterCheckout, dueDate.plusDays(1));
        LocalDate tempDate = dayAfterCheckout;
        long nonChargeDays = 0;
        while (!tempDate.isAfter(dueDate)) {
            if ((isWeekday(tempDate) && !currentTool.isWeekDayCharge()) ||
                    (isWeekend(tempDate) && !currentTool.isWeekEndCharge()) ||
                    (isHoliday(tempDate) && !currentTool.isHolidayCharge())) {
                nonChargeDays++;
            }
            tempDate = tempDate.plusDays(1);
        }
        return (int) (totalDays - nonChargeDays);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return !(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }

    private boolean isHoliday(LocalDate date) {
        MonthDay monthDay = MonthDay.from(date);

        // Independence Day
        if (INDEPENDENCE_DAY.contains(monthDay)) {
            return true; // it's a fixed date holiday
        } else if (date.getDayOfWeek() == DayOfWeek.FRIDAY && MonthDay.from(date.plusDays(2)).equals(MonthDay.of(Month.JULY, 4))) {
            return true; // When July 4 is a Sunday, the Friday before is off
        } else if (date.getDayOfWeek() == DayOfWeek.MONDAY && MonthDay.from(date.minusDays(2)).equals(MonthDay.of(Month.JULY, 4))) {
            return true; // When July 4 is a Saturday, the Monday after is off
        }

        // Labor Day (First Monday in September)
        return date.getMonth() == Month.SEPTEMBER && date.getDayOfMonth() <= 7 && date.getDayOfWeek() == DayOfWeek.MONDAY;
    }
}
