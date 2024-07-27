package com.dcodelife.tools;

public class JackHRidgid implements Tool{
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
