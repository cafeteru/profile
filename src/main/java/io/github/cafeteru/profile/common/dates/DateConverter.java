package io.github.cafeteru.profile.common.dates;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter {
    public static final String PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public LocalDateTime stringToLocalDateTime(String applicationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDateTime.parse(applicationDate, formatter);
    }

    public String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return localDateTime.format(formatter);
    }
}
