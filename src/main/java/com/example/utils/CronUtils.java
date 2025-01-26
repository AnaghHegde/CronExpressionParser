package com.example.utils;

import java.util.ArrayList;
import java.util.List;

public class CronUtils {

    public static String expandField(String field, int min, int max) {
        List<Integer> values = new ArrayList<>();

        // Split by commas for lists (e.g., "1,15")
        String[] parts = field.split(",");
        for (String part : parts) {
            if ("*".equals(part)) {
                // Expand wildcard (*)
                for (int i = min; i <= max; i++) {
                    values.add(i);
                }
            } else if (part.contains("-")) {
                // Expand range (e.g., "1-5")
                String[] range = part.split("-");
                int start = parseInt(range[0], "Invalid range start");
                int end = parseInt(range[1], "Invalid range end");

                // Validate the range
                validateRange(start, end, min, max);

                for (int i = start; i <= end; i++) {
                    values.add(i);
                }
            } else if (part.contains("/")) {
                // Handle step values (e.g., "*/15" or "1-5/2")
                String[] stepParts = part.split("/");
                String rangePart = stepParts[0];
                int step = parseInt(stepParts[1], "Invalid step value");

                int start = rangePart.equals("*") ? min : parseInt(rangePart.split("-")[0], "Invalid range start");
                int end = rangePart.equals("*") ? max : parseInt(rangePart.split("-")[1], "Invalid range end");

                // Validate the range
                validateRange(start, end, min, max);

                for (int i = start; i <= end; i += step) {
                    values.add(i);
                }
            } else {
                // Single value (e.g., "15")
                int singleValue = parseInt(part, "Invalid value");

                // Validate the single value
                validateRange(singleValue, singleValue, min, max);

                values.add(singleValue);
            }
        }

        // Return the list as a space-separated string
        return values.toString().replace("[", "").replace("]", "").replace(",", "");
    }

    /**
     * Parses a string into an integer with error handling.
     *
     * @param value   The string to parse.
     * @param message The error message to display if parsing fails.
     * @return The parsed integer.
     * @throws IllegalArgumentException If the string is not a valid integer.
     */
    private static int parseInt(String value, String message) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message + ": " + value);
        }
    }

    /**
     * Validates whether a given range or single value is within the allowed min and max range.
     *
     * @param start The start of the range or single value.
     * @param end   The end of the range or single value.
     * @param min   The minimum allowed value.
     * @param max   The maximum allowed value.
     * @throws IllegalArgumentException If the range or value is invalid.
     */
    private static void validateRange(int start, int end, int min, int max) {
        if (start < min || end > max || start > end) {
            throw new IllegalArgumentException(
                    String.format("Invalid range: %d-%d is not within %d-%d", start, end, min, max)
            );
        }
    }
}