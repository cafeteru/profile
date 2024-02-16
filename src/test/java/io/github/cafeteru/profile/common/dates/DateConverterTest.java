package io.github.cafeteru.profile.common.dates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateConverterTest {
    private final DateConverter dateConverter = new DateConverter();

    @Test
    void stringToLocalDateTime_with_correct_date() {
        String applicationDate = "2021-01-01-01.20.30";
        var result = dateConverter.stringToLocalDateTime(applicationDate);
        assertEquals(2021, result.getYear());
        assertEquals(1, result.getMonthValue());
        assertEquals(1, result.getDayOfMonth());
        assertEquals(1, result.getHour());
        assertEquals(20, result.getMinute());
        assertEquals(30, result.getSecond());
    }

    @Test
    void stringToLocalDateTime_with_null_date() {
        try {
            dateConverter.stringToLocalDateTime(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid LocalDateTime: " + null, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "2020",
            "2020-06",
            "2020-06-14",
            "2020-06-15-10",
            "2020-06-15-10.00",
            "2020-06-15 10:00:00",
            "invalid-date",
    })
    void stringToLocalDateTime_with_invalid_dates(String applicationDate) {
        try {
            dateConverter.stringToLocalDateTime(applicationDate);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid LocalDateTime: " + applicationDate, e.getMessage());
        }
    }

    @Test
    void localDateTimeToString_with_correct_date() {
        var localDateTime = LocalDateTime.of(2021, 1, 1, 1, 20, 30);
        var result = dateConverter.localDateTimeToString(localDateTime);
        assertEquals("2021-01-01-01.20.30", result);
    }

    @Test
    void localDateTimeToString_with_null_date() {
        try {
            dateConverter.localDateTimeToString(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid LocalDateTime: " + null, e.getMessage());
        }
    }
}
