package com.dcodelife.tools;

import java.math.BigDecimal;

public class ChainsawStihl implements Tool {
    @Override
    public String getToolCode() {
        return "CHNS";
    }

    @Override
    public String getType() {
        return "Chainsaw";
    }

    @Override
    public String getBrand() {
        return "Stihl";
    }

    @Override
    public BigDecimal getDailyCharge() {
        return new BigDecimal("1.49");
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
        return true;
    }
}