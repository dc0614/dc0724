package com.dcodelife.tools;

import java.math.BigDecimal;

public class LadderWerner implements Tool {
    @Override
    public String getToolCode() {
        return "LADW";
    }

    @Override
    public String getType() {
        return "Ladder";
    }

    @Override
    public String getBrand() {
        return "Werner";
    }

    @Override
    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.99");
    }

    @Override
    public boolean isWeekDayCharge() {
        return true;
    }

    @Override
    public boolean isWeekEndCharge() {
        return true;
    }

    @Override
    public boolean isHolidayCharge() {
        return false;
    }
}
