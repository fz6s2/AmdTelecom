package org.amd.task01;

public class Task01 {
    /**
     * Takes an array of numbers and return "Found" if the character 7 appears in the array of the numbers.
     * Otherwise, return "there is no 7 in the array"
     *
     * @param array Array of numbers
     * @return Result of searching 7
     */
    public static String findSeven(int [] array) {
        String resultWithoutSeven = "there is no 7 in the array";
        if (array == null) {
            return resultWithoutSeven;
        }

        for (int j : array) {
            if (hasSeven(j)) {
                return "Found";
            }
        }

        return resultWithoutSeven;
    }

    public static boolean hasSeven(int item) {
        return String.valueOf(item).contains("7");
    }
}
