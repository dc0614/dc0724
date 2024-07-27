package com.dcodelife.tools;

public class ChainsawStihl implements Tool{
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
    public float getDailyCharge() {
        return 1.49F;
    }

    @Override
    public boolean getWeekDayCharge() {
        return true;
    }

    @Override
    public boolean getWeekEndCharge() {
        return false;
    }

    @Override
    public boolean getHolidayCharge() {
        return true;
    }
}
