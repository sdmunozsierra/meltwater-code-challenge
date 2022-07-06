package com.meltwater;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Main App
 */
public final class App {
    private App() {
    }

    /**
     * Mask text using keywords and phrases.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String keywords = "   ,,  Hello world “Boston Red Sox”, ‘Pepperoni Pizza’, ‘Cheese Pizza’, beer, amigo enemy,  , , ,";
        String text = "I went to see the Boston Red Sox while drinking beer and eating a Pepperoni Pizza.";

        // Create unique maskable words and phrases
        Set<String> maskable = createMaskableKeywords(keywords);
        System.out.println(maskable);

        // Mask Text
        String result = maskText(maskable, text);
        System.out.println(result);

    }// end main

    public static String maskText(Set<String> maskedKeywords, String text) {
        if (text == null || text.length() < 1) {
            return "";
        }

        // Check for empty set
        if (maskedKeywords == null || maskedKeywords.isEmpty()) {
            return text;
        }

        // Create an output string
        String output = text;

        // Iterate through set using iterator
        Iterator<String> maskedKeywordsIterator = maskedKeywords.iterator();
        while (maskedKeywordsIterator.hasNext()) {
            output = output.replaceAll(maskedKeywordsIterator.next(), "XXXX");
        }
        return output;
    }

    public static Set<String> createMaskableKeywords(String keywords) {
        // Check the length of the String
        if (keywords == null || keywords.length() < 1) {
            return new HashSet<String>();
        }

        // Create Set to hold unique maskableKeywords
        Set<String> maskable = new HashSet<String>();
        // ArrayList<String> maskable = new ArrayList<String>();

        // Create two pointers one for start and one for next char
        int left = 0;
        int right = 1;
        int len = keywords.length() - 1;

        // Iterate until the end of the string
        while (right <= len) {
            // base case
            if (right == len) {
                String sub = keywords.substring(left, right + 1); // (inclusive, exclusive]
                maskable.add(sub);
            }
            // left is space or comma (start of word)
            if (keywords.charAt(left) == ' ' || keywords.charAt(left) == ',') {
                while (keywords.charAt(left) == ' ' || keywords.charAt(left) == ',') {
                    if (left >= len) {
                        System.out.println("Finished with leading space or comma!\nExit...");
                        return maskable;
                    }
                    left++;
                }
                right = left; // Update right pointer
                right++; // next char
            }
            // right is space of comma (end of word)
            if (keywords.charAt(right) == ' ' || keywords.charAt(right) == ',') {
                String sub = keywords.substring(left, right); // (inclusive, exclusive]
                maskable.add(sub);
                left = right; // Update left pointer
            }
            // left is double quotes (start of word)
            if (keywords.charAt(left) == '“') {
                boolean found = true;
                while (keywords.charAt(right) != '”') {
                    if (right >= len) {
                        System.out.println("Did not find matching double quote!");
                        found = false;
                        break;
                    }
                    right++; // increment pointer
                } // ending quotes found
                if (found) {
                    String sub = keywords.substring(++left, right); // (inclusive remove quote, exclusive]
                    maskable.add(sub);
                    left = ++right;
                }
                left = right;
                right++;
            }
            // left is single quotes (start of word)
            if (keywords.charAt(left) == '‘') {
                boolean found = true;
                while (keywords.charAt(right) != '’') {
                    if (right >= len) {
                        System.out.println("Did not find matching single quote!");
                        found = false;
                        break;
                    }
                    right++; // increment pointer
                } // ending quotes found
                if (found) {
                    String sub = keywords.substring(++left, right); // (inclusive remove quote, exclusive]
                    maskable.add(sub);
                    left = ++right; // update left pointer
                }
                left = right;
                right++;
            }
            right++; // update right pointer
        } // end while loop
        return maskable;
    }

}// endmain
