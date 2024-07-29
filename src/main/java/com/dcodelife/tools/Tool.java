package com.dcodelife.tools;

import java.math.BigDecimal;

/**
 * Tool is an interface that represents a tool.
 * It provides methods to retrieve information about the tool such as the tool code, type, brand, daily charge, and charges for weekdays, weekends, and holidays.
 */
public interface Tool {
    String getToolCode();

    String getType();

    String getBrand();

    BigDecimal getDailyCharge();

    boolean isWeekDayCharge();

    boolean isWeekEndCharge();

    boolean isHolidayCharge();
}
