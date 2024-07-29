package com.dcodelife.processing;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    /* Tests for the processCheckout method in the Checkout class. */

    Checkout testCheckout = new Checkout();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");

    @Test
    void testProcessCheckout() {
        assertThrows(RuntimeException.class, () -> testCheckout.processCheckout("", 0, 0,LocalDate.now()));
    }

    @Test
    void testSetToolCode() {
        assertThrows(IllegalArgumentException.class, () -> testCheckout.validateToolCode("UnknownToolCode"));

        testCheckout.validateToolCode("LADW");
        assertEquals("LADW", testCheckout.getToolCode());
    }

    @Test
    void testSetRentalDays() {
        assertThrows(IllegalArgumentException.class, () -> testCheckout.validateRentalDays(0));

        testCheckout.validateRentalDays(5);
        assertEquals(5, testCheckout.getRentalDays());
    }

    @Test
    void testSetDiscountPct() {
        assertThrows(IllegalArgumentException.class, () -> testCheckout.validateDiscountPct(-1));
        assertThrows(IllegalArgumentException.class, () -> testCheckout.validateDiscountPct(101));

        testCheckout.validateDiscountPct(10);
        assertEquals(10, testCheckout.getDiscountPct());
    }

    @Test
    void test1() {
        LocalDate localDate = LocalDate.parse("09/03/15", formatter);
        assertThrows(IllegalArgumentException.class, () -> testCheckout.processCheckout("JAKR", 5, 101, localDate));
    }

    @Test
    void test2() {
        LocalDate localDate = LocalDate.parse("07/02/20", formatter);
        RentalAgreement test2 = testCheckout.processCheckout("LADW", 3, 10, localDate);
        assertEquals(LocalDate.parse("07/05/20", formatter), test2.getDueDate());
        assertEquals(2, test2.getChargeDays());
        assertEquals(new BigDecimal("3.98"), test2.getPreDiscountCharge());
        assertEquals(new BigDecimal("3.58"), test2.getFinalCharge());
    }

    @Test
    void test3() {
        LocalDate localDate = LocalDate.parse("7/2/20", formatter);
        RentalAgreement agreement = testCheckout.processCheckout("CHNS", 5, 25, LocalDate.parse("2015-07-02"));
        assertNotNull(agreement);
        assertEquals(LocalDate.parse("7/7/15", formatter), agreement.getDueDate());
        assertEquals(3, agreement.getChargeDays());
        assertEquals(new BigDecimal("4.47"), agreement.getPreDiscountCharge());
        assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount());
        assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge());
    }

    @Test
    void test4() {
        LocalDate localDate = LocalDate.parse("09/03/15", formatter);
        RentalAgreement test4 = testCheckout.processCheckout("JAKD", 6, 0, localDate);
        assertEquals(3, test4.getChargeDays());
        assertEquals(LocalDate.parse("09/09/15", formatter), test4.getDueDate());
    }

    @Test
    void test5() {
        LocalDate localDate = LocalDate.parse("07/02/15", formatter);
        RentalAgreement test5 = testCheckout.processCheckout("JAKR", 9, 0, localDate);
        assertEquals(5, test5.getChargeDays());
        assertEquals(LocalDate.parse("07/11/15", formatter), test5.getDueDate());
        assertEquals("Ridgid", test5.getToolBrand());
    }

    @Test
    void test6 () {
        LocalDate localDate = LocalDate.parse("07/02/20", formatter);
        RentalAgreement test6 = testCheckout.processCheckout("JAKR", 4, 50, localDate);
        assertEquals(new BigDecimal("2.99"), test6.getDailyCharge());
        assertEquals(1, test6.getChargeDays());
        assertEquals(new BigDecimal("2.99"), test6.getPreDiscountCharge());
        assertEquals(new BigDecimal("1.50"), test6.getDiscountAmount());
        assertEquals(new BigDecimal("1.49"), test6.getFinalCharge());
    }
}