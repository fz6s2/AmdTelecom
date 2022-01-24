package org.amd.task02;

public class Task02 {
    /**
     * Accepts an integer and calculates the sum of its digits.
     * If the sum is greater than 9 repeats the calculation of the sum of its digits until we get sum < 10.
     *
     * @param number Integer
     * @return Sum of digits
     */
    public static int digitSum(int number) {
        while(true) {
            if (number < 10) {
                return number;
            }
            number = sumOfDigits(number);
        }
    }

    private static int sumOfDigits(int number) {
        int sum = 0;
        for (int n = number; n > 0; n /= 10) {
            sum += n % 10;
        }
        return sum;
    }
}
