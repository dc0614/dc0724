package com.dcodelife.processing;

import com.dcodelife.tools.ToolStore;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class Checkout {

    ToolStore toolStore = new ToolStore();
    String toolCode;
    int rentalDays;
    int discountPct;
    LocalDate checkoutDate;

    public Checkout(String toolCode, int rentalDays, int discountPct, LocalDate checkoutDate) {

    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(int discountPct) {
        this.discountPct = discountPct;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void processCheckout(String toolCode, int rentalDays, int discountPct, LocalDate checkoutDate) {
    }
}
