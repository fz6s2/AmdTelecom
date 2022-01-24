package org.amd.task01;

/**
 * Task 01 - searching "7" in array
 */
public class Task01 {
    private static final String MESSAGE_WITH_SEVEN = "Found";
    private static final String MESSAGE_WITHOUT_SEVEN = "there is no 7 in the array";
    /**
     * Takes an array of numbers and return "Found" if the character 7 appears in the array of the numbers.
     * Otherwise, return "there is no 7 in the array"
     *
     * @param array Array of numbers
     * @return Result of searching 7
     */
    public static String findSeven(int [] array) {
        if (array == null) {
            return MESSAGE_WITHOUT_SEVEN;
        }

        for (int j : array) {
            if (hasSeven(j)) {
                return MESSAGE_WITH_SEVEN;
            }
        }

        return MESSAGE_WITHOUT_SEVEN;
    }

    public static boolean hasSeven(int item) {
        return String.valueOf(item).contains("7");
    }
}
