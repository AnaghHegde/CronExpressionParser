package com.example.parser;

import java.util.Map;

public class CronExpressionParser {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide a cron expression as a single argument.");
            System.exit(1);
        }

        String cronExpression = String.join(" ", args);
        CronExpression cron = new CronExpression(cronExpression);

        // Print each field in the expected format
        for (Map.Entry<String, String> entry : cron.getFields().entrySet()) {
            System.out.printf("%-14s%s%n", entry.getKey(), entry.getValue());
        }
        System.out.printf("%-14s%s%n", "command", cron.getCommand());
    }
}
