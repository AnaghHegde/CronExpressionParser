package com.example.fields;

import com.example.utils.CronUtils;

public class HourField implements FieldParser {

    @Override
    public String parse(String field) {
        return CronUtils.expandField(field, 0, 23);
    }
}
