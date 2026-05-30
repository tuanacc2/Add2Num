package com.mybignumber.lib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBigNumber {
    private static final Logger logger = LoggerFactory.getLogger(MyBigNumber.class);
    
    private static char add(char c1, char c2) {
        if (Character.isDigit(c1) && Character.isDigit(c2)) {
            int value = c2 - 48;    // '0' = 48 ASCII
            char result = (char)(c1 + value > 57 ? c1 + value - 10 : c1 + value);
            return result;
        }
        return '0';
    }

    private static boolean leftover(char c1, char c2) {
        if (Character.isDigit(c1) && Character.isDigit(c2) && (c1 + c2 - 96 > 9)) {
            return true;
        }
        return false;
    }
 
    /**
     * Tính tổng của hai số nguyên lớn dưới dạng chuỗi.
     * @param stn1 Số thứ nhất (chuỗi ký số)
     * @param stn2 Số thứ hai (chuỗi ký số)
     * @return Chuỗi kết quả phép cộng
     */
    public static String sum(String stn1, String stn2) {
        logger.info("Đang tính tổng của {} và {}", stn1, stn2);

        StringBuilder result = new StringBuilder(); 
        int i = stn1.length() - 1;
        int j = stn2.length() - 1;
        int idx = 0;
        boolean carry = false;

        while (i >= 0 || j >= 0 || carry) {
            char c1 = '0'; char c2 = '0';

            if (i >= 0) {
                c1 = stn1.charAt(i);
                i--;
            }

            if (j >= 0) {
                c2 = stn2.charAt(j);
                j--;
            }

            char new_c = add(c1, c2);
            boolean flag = leftover(c1, c2);
            
            if (carry) {
                flag = flag || leftover(new_c, '1');
                new_c = add(new_c, '1');
            }

            carry = flag;

            if (result.length() <= idx) {
                result.insert(0, new_c);

                if (carry) {
                    result.insert(0, '1');
                }
            } else {
                result.replace(0, 1, "" + new_c);
                if (carry) {
                    result.insert(0, '1');
                }
            }

            idx++;
            logger.info("Tính toán hàng hiện tại: kết quả tạm thời = {}", result);
        }
        
        logger.info("Kết quả cuối cùng: {}", result);
        return result.toString();
    }
}