package com.dcodelife;

import com.dcodelife.processing.Checkout;
import com.dcodelife.processing.RentalAgreement;
import com.dcodelife.tools.ToolStore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * The Main class handles the tool checkout process.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy", Locale.US);

    public static void main(String[] args) {
        ToolStore toolStore = new ToolStore();
        toolStore.displayTools();

        String toolCode = getToolCode();

        toolStore.displayToolPrice(toolCode);

        if (isAddToCheckout()) {
            System.out.println("Checkout Process Starting...");

            LocalDate checkoutDate = getCheckoutDate();
            int rentalDayCount = getRentalDays();
            int discountPerc = getDiscount();
            processCheckout(toolCode, checkoutDate, rentalDayCount, discountPerc);
        } else {
            System.out.println("Returning to the list...");
            toolStore.displayTools();
        }
    }

    private static String getToolCode() {
        System.out.println("Please enter a tool code from the list below :");
        return scanner.nextLine();
    }

    private static boolean isAddToCheckout() {
        System.out.println("Would you like to add this item to checkout (y) or return to the list (n) ?");
        return "y".equalsIgnoreCase(scanner.nextLine());
    }

    private static LocalDate getCheckoutDate() {
        System.out.println("Please enter checkout date...");
        LocalDate checkoutDate;
        while (true) {
            try {
                String dateString = scanner.nextLine();
                if (dateString.isEmpty()) {
                    checkoutDate = LocalDate.now();
                    System.out.println("Checkout starts today: " + checkoutDate.format(formatter));
                } else {
                    checkoutDate = LocalDate.parse(dateString, formatter);
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter a valid date in the format MM/dd/yy");
            }
        }
        return checkoutDate;
    }

    private static int getRentalDays() {
        System.out.println("Please enter amount of rental days...");
        int rentalDayCount;
        while (true) {
            try {
                rentalDayCount = scanner.nextInt();
                new Checkout().validateRentalDays(rentalDayCount);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage()); // print the friendly error message
            }
        }
        return rentalDayCount;
    }

    private static int getDiscount() {
        System.out.println("Please enter discount amount...");
        int discountPerc;
        while (true) {
            try {
                discountPerc = scanner.nextInt();
                new Checkout().validateDiscountPct(discountPerc);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());  // print the error message from the exception
            }
        }
        return discountPerc;
    }

    private static void processCheckout(String toolCode, LocalDate checkoutDate, int rentalDayCount, int discountPerc) {
        Checkout checkout = new Checkout();
        checkout.setCheckoutDate(checkoutDate);
        RentalAgreement generatedAgreement = checkout.processCheckout(toolCode, rentalDayCount, discountPerc, checkoutDate);
        System.out.println(generatedAgreement.toString());
    }
}