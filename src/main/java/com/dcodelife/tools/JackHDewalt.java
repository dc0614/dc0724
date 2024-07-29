package com.dcodelife.tools;

import java.math.BigDecimal;

public class JackHDewalt implements Tool {
    @Override
    public String getToolCode() {
        return "JAKD";
    }

    @Override
    public String getType() {
        return "Jackhammer";
    }

    @Override
    public String getBrand() {
        return "DeWalt";
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
