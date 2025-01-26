package com.example.fields;

import com.example.utils.CronUtils;

public class MinuteField implements FieldParser {

    @Override
    public String parse(String field) {
        return CronUtils.expandField(field, 0, 59);
    }
}
