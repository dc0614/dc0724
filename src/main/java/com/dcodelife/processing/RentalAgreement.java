package com.dcodelife.processing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * RentalAgreement represents an agreement for renting a tool. It contains information about the tool,
 * rental duration, charges, and discounts.
 */
@NoArgsConstructor
public class RentalAgreement {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("M/d/yy", Locale.US);

    @Getter
    @Setter
    private String toolCode, toolType, toolBrand;

    @Getter
    @Setter
    private int rentalDays, chargeDays, discountPercent;

    @Getter
    @Setter
    private LocalDate checkoutDate, dueDate;

    @Getter
    @Setter
    private BigDecimal dailyCharge, preDiscountCharge, discountAmount, finalCharge;

    @Override
    public String toString() {
        return buildString();
    }

    private String buildString() {
        return "Tool Code: " + getToolCode() + "\n" +
                "Tool Type: " + getToolType() + "\n" +
                "Tool Brand: " + getToolBrand() + "\n" +
                "Rental Days: " + getRentalDays() + "\n" +
                "Check out date: " + formatDate(getCheckoutDate()) + "\n" +
                "Due Date: " + formatDate(getDueDate()) + "\n" +
                "Daily Rental Charge: $" + getDailyCharge() + "\n" +
                "Charge Days: " + getChargeDays() + "\n" +
                "Pre-Discount Charge: $" + getPreDiscountCharge() + "\n" +
                "Discount Percent: " + getDiscountPercent() + "%\n" +
                "Discount Amount: $" + getDiscountAmount() + "\n" +
                "Final Charge: $" + getFinalCharge();
    }

    private String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }
}
