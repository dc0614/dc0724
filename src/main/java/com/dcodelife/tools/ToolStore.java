package com.dcodelife.tools;

import java.util.*;

/**
 * ToolStore is a class that represents a tool store.
 * It provides methods to retrieve tools from the store, find a tool by its code, and display tool information and price lists.
 */
public class ToolStore {
    private final List<Tool> tools = Arrays.asList(
            new ChainsawStihl(),
            new JackHDewalt(),
            new JackHRidgid(),
            new LadderWerner()
    );

    public List<Tool> getTools() {
        return Collections.unmodifiableList(tools);
    }

    /**
     * Finds a tool in the tool store by its code.
     *
     * @param toolCode The code of the tool to search for.
     * @return The tool with the given code, or null if no tool is found.
     */
    public Tool findToolByCode(String toolCode) {
        return tools.stream().filter(tool -> tool.getToolCode().equals(toolCode)).findFirst().orElse(null);
    }

    public void displayTools() {
        tools.forEach(this::displayToolInfo);
    }

    public void displayToolPriceList() {
        Map<String, Tool> uniqueTypeTools = new LinkedHashMap<>();
        for (Tool tool : tools) {
            uniqueTypeTools.putIfAbsent(tool.getType(), tool);
        }
        uniqueTypeTools.values().forEach(this::displayToolChargesInfo);
    }

    /**
     * Displays the price and information of a tool.
     *
     * @param toolCode The code of the tool to display the price of.
     */
    public void displayToolPrice(String toolCode) {
        Tool tool = findToolByCode(toolCode);
        if (tool != null) {
            displayToolInfo(tool);
            displayToolChargesInfo(tool);
        }
    }

    private void displayToolInfo(Tool tool) {
        System.out.println("Tool Code: " + tool.getToolCode() + ", Type: " + tool.getType() + ", Brand: " + tool.getBrand());
    }

    private void displayToolChargesInfo(Tool tool) {
        System.out.println("Daily Charge: $" + tool.getDailyCharge() +
                ", Weekday Charge: " + (tool.isWeekDayCharge() ? "Yes" : "No") +
                ", Weekend Charge: " + (tool.isWeekEndCharge() ? "Yes" : "No") +
                ", Holiday Charge: " + (tool.isHolidayCharge() ? "Yes" : "No"));
    }
}
