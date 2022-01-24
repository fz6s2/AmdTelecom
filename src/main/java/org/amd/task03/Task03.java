package org.amd.task03;

/**
 * Task 03 - remaking words on string
 */
public class Task03 {
    private static final char [] VOWELS = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    private static final char [] WORD_DELIMITER = {' ', ',', '.', '!', '?', ';', '-', ':'};

    /**
     * Takes a string of words and
     *  - Move the first letter of each word to the end of the word.
     *  - Add "ay" to the end of the word.
     *  - Words starting with a vowel (a,e,i,o,u, A, E, I, O, U) simply have "way" appended to the end.
     *
     *  Fast algorithm without extra String operations.
     *
     * @param text Text
     * @return Text with remaded words
     */
    public static String doRemake(String text) {
        if (text == null) {
            return null;
        }

        final StringBuilder result = new StringBuilder();
        int start = 0;
        int end = 0;

        char [] chars = text.toCharArray();

        for(; end < chars.length; end++) {
            char c = chars[end];

            if (isWordDelimiter(c)) {
                result
                    .append(remakeWord(chars, start, end - 1))
                    .append(c);
                start = end + 1;
            }
        }

        //last word without delimiter at the end
        if (start < end) {
            result.append(remakeWord(chars, start, end - 1));
        }

        return result.toString();
    }

    private static StringBuilder remakeWord(char [] chars, int start, int end) {
        if (start > end) {
            return new StringBuilder();
        }

        int wordLength = end - start + 1;
        char firstChar = chars[start];

        // StringBuilder is handy for combining string and character operations
        StringBuilder result = new StringBuilder();

        if (isVowel(firstChar)) {
            return result
                    .append(chars, start, wordLength)
                    .append("way");
        }

        // one letter word (not vowel)
        if (wordLength == 1) {
            return result
                    .append(chars, start, wordLength)
                    .append("ay");
        }

        char secondChar = Character.isUpperCase(firstChar) ? Character.toUpperCase(chars[start + 1]) : chars[start + 1];

        return result.append(secondChar)
                .append(chars, start + 2, wordLength - 2)
                .append(Character.toLowerCase(firstChar))
                .append("ay");
    }

    private static boolean isWordDelimiter(char c) {
        return arrayConsists(WORD_DELIMITER, c);
    }

    private static boolean isVowel(char c) {
        return arrayConsists(VOWELS, c);
    }

    private static boolean arrayConsists(char[] array, char c) {
        for(char item: array) {
            if (item == c) {
                return true;
            }
        }
        return false;
    }
}
