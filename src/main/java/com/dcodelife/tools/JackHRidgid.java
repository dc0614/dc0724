package com.dcodelife.tools;

import java.math.BigDecimal;

public class JackHRidgid implements Tool {
    @Override
    public String getToolCode() {
        return "JAKR";
    }

    @Override
    public String getType() {
        return "Jackhammer";
    }

    @Override
    public String getBrand() {
        return "Ridgid";
    }

    @Override
    public BigDecimal getDailyCharge() {
        return new BigDecimal("2.99");
    }

    @Override
    public boolean isWeekDayCharge() {
        return true;
    }

    @Override
    public boolean isWeekEndCharge() {
        return false;
    }

    @Override
    public boolean isHolidayCharge() {
        return false;
    }
}
