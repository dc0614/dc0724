package com.dcodelife.tools;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToolStore {

    private final List<Tool> tools = Arrays.asList(
            new ChainsawStihl(),
            new JackHDewalt(),
            new JackHRidgid(),
            new LadderWerner()
    );

    public void displayTools() {
        for (Tool tool : tools) {
            System.out.println("Tool Code: " + tool.getToolCode() + ", Type: " + tool.getType() + ", Brand: " + tool.getBrand());
        }
    }

    public void displayToolPriceList() {
        Map<String, Tool> uniqueTypeTools = new LinkedHashMap<>();

        for (Tool tool : tools) {
            if (!uniqueTypeTools.containsKey(tool.getType())) {
                uniqueTypeTools.put(tool.getType(), tool);
            }
        }

        for (Tool uniqueTool : uniqueTypeTools.values()) {
            System.out.println("Type: " + uniqueTool.getType() + ", Daily Charge: $" + uniqueTool.getDailyCharge() +
                    ", Weekday Charge: " + (uniqueTool.getWeekDayCharge() ? "Yes" : "No") + ", Weekend Charge: " + (uniqueTool.getWeekEndCharge() ? "Yes" : "No") +
                    ", Holiday Charge: " + (uniqueTool.getHolidayCharge() ? "Yes" : "No"));
        }
    }

    public void displayToolPrice(String toolCode) {
        for (Tool tool : tools) {
            if (tool.getToolCode().equals(toolCode)) {
                System.out.println("Tool Code: " + tool.getToolCode() + ", Type: " + tool.getType() + ", Brand: " + tool.getBrand() +
                        "\n Daily Charge: $" + tool.getDailyCharge() + ", Weekday Charge: " + (tool.getWeekDayCharge() ? "Yes" : "No") +
                        ", Weekend Charge: " + (tool.getWeekEndCharge() ? "Yes" : "No") + ", Holiday Charge: " + (tool.getHolidayCharge() ? "Yes" : "No"));
                break;
            }
        }
    }
}
