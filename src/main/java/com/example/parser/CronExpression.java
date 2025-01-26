
package com.example.parser;

import com.example.fields.DayOfMonthField;
import com.example.fields.DayOfWeekField;
import com.example.fields.HourField;
import com.example.fields.MinuteField;
import com.example.fields.MonthField;
import com.example.utils.CronFieldParser;

import java.util.LinkedHashMap;
import java.util.Map;

public class CronExpression {

    private final Map<String, String> fields = new LinkedHashMap<>();
    private final String command;

    public CronExpression(String cronExpression) {
        String[] parts = cronExpression.split("\\s+", 6);

        if (parts.length < 6) {
            throw new IllegalArgumentException("Cron expression must have at least 6 parts.");
        }

        fields.put("minute", CronFieldParser.parse(parts[0], new MinuteField()));
        fields.put("hour", CronFieldParser.parse(parts[1], new HourField()));
        fields.put("day of month", CronFieldParser.parse(parts[2], new DayOfMonthField()));
        fields.put("month", CronFieldParser.parse(parts[3], new MonthField()));
        fields.put("day of week", CronFieldParser.parse(parts[4], new DayOfWeekField()));
        this.command = parts[5];
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public String getCommand() {
        return command;
    }
}
