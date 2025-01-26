package com.example.fields;

import com.example.utils.CronUtils;

public class DayOfWeekField implements FieldParser {

    @Override
    public String parse(String field) {
        return CronUtils.expandField(field, 1, 7);
    }
}
