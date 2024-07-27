package com.dcodelife.tools;

public interface Tool {
    String getToolCode();
    String getType();
    String getBrand();
    float getDailyCharge();
    boolean getWeekDayCharge();
    boolean getWeekEndCharge();
    boolean getHolidayCharge();
}
