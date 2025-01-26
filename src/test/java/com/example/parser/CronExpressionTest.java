package com.example.parser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CronExpressionTest {

    @Test
    public void testWildcardFields() {
        CronExpression cron = new CronExpression("* * * * * /bin/echo hello");
        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59", cron.getFields().get("minute"));
    }

    @Test
    public void testStepValues() {
        CronExpression cron = new CronExpression("*/15 0 * * * /usr/bin/find");
        assertEquals("0 15 30 45", cron.getFields().get("minute"));
    }

    @Test
    public void testListValues() {
        CronExpression cron = new CronExpression("0 0 1,15 * * /bin/echo run");
        assertEquals("1 15", cron.getFields().get("day of month"));
    }

    @Test
    public void testInvalidMinuteValue() {
        assertThrows(IllegalArgumentException.class, () -> new CronExpression("60 0 * * * /bin/error"));
    }

    @Test
    public void testSingleValues() {
        CronExpression cron = new CronExpression("5 14 20 6 2 /bin/backup.sh");
        assertEquals("5", cron.getFields().get("minute"));
        assertEquals("14", cron.getFields().get("hour"));
        assertEquals("20", cron.getFields().get("day of month"));
        assertEquals("6", cron.getFields().get("month"));
        assertEquals("2", cron.getFields().get("day of week"));
    }

    @Test
    public void testEmptyCommand() {
        assertThrows(IllegalArgumentException.class, () -> new CronExpression("*/5 * * * *"));
    }
}
