package com.dcodelife.tools;

public class LadderWerner implements Tool{
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
    public float getDailyCharge() {
        return 1.99F;
    }

    @Override
    public boolean getWeekDayCharge() {
        return true;
    }

    @Override
    public boolean getWeekEndCharge() {
        return true;
    }

    @Override
    public boolean getHolidayCharge() {
        return false;
    }
}
