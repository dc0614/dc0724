package com.dcodelife.tools;

public class JackHDewalt implements Tool{
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
    public float getDailyCharge() {
        return 2.99F;
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
        return false;
    }
}
