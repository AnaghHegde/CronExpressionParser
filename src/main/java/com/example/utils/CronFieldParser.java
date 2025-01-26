package com.example.utils;

import com.example.fields.FieldParser;

public class CronFieldParser {

    public static String parse(String field, FieldParser parser) {
        return parser.parse(field);
    }
}
