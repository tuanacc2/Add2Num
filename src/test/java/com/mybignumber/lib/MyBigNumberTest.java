package com.mybignumber.lib;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyBigNumberTest {

    @Test
    @DisplayName("Should correctly add two numbers of equal length without carry")
    void testEqualLengthNoCarry() {
        String result = MyBigNumber.sum("123", "456");
        assertEquals("579", result, "123 + 456 should equal 579");
    }

    @Test
    @DisplayName("Should correctly handle single carry over during addition")
    void testEqualLengthWithCarry() {
        String result = MyBigNumber.sum("58", "67");
        assertEquals("125", result, "58 + 67 should equal 125");
    }

    @Test
    @DisplayName("Should handle cases where the first number is longer")
    void testFirstNumberLonger() {
        String result = MyBigNumber.sum("100020", "35");
        assertEquals("100055", result, "100020 + 35 should equal 100055");
    }

    @Test
    @DisplayName("Should handle cases where the second number is longer")
    void testSecondNumberLonger() {
        String result = MyBigNumber.sum("12", "99988");
        assertEquals("100000", result, "12 + 99988 should equal 100000");
    }

    @Test
    @DisplayName("Should correctly propagate a chained carry all the way to the front")
    void testChainedCarryPropagation() {
        String result = MyBigNumber.sum("999", "1");
        assertEquals("1000", result, "999 + 1 should equal 1000");
    }

    @Test
    @DisplayName("Should handle additions involving zeros")
    void testAdditionWithZero() {
        assertEquals("0", MyBigNumber.sum("0", "0"), "0 + 0 should equal 0");
        assertEquals("8675309", MyBigNumber.sum("8675309", "0"), "Adding 0 should return the same number");
    }
}