package com.dcodelife;

import com.dcodelife.processing.Checkout;
import com.dcodelife.tools.ToolStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Please enter a tool code from the list below :");
        ToolStore toolStore = new ToolStore();
        toolStore.displayTools();
        Scanner scanner = new Scanner(System.in);
        String toolCode = scanner.nextLine();
        toolStore.displayToolPrice(toolCode);
        System.out.println("Would you like to add this item to checkout (y) or return to the list (n) ?");
        if ( "y".equalsIgnoreCase(scanner.nextLine()) )
        {
            System.out.println("Checkout Process Starting...");
            Checkout checkout = new Checkout();
            //TODO
        } else {
            System.out.println("Returning to the list...");
            toolStore.displayTools();
        }

       // toolStore.displayToolPriceList();
    }
}