package io.github.cafeteru.profile.common.dates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@Slf4j
public class DateConverter {
    public final String PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public LocalDateTime stringToLocalDateTime(String applicationDate) {
        try {
            var formatter = DateTimeFormatter.ofPattern(PATTERN);
            return LocalDateTime.parse(applicationDate, formatter);
        } catch (NullPointerException | DateTimeParseException e) {
            log.error("Invalid LocalDateTime: " + applicationDate);
            throw new IllegalArgumentException("Invalid LocalDateTime: " + applicationDate);
        }
    }

    public String localDateTimeToString(LocalDateTime localDateTime) {
        try {
            var formatter = DateTimeFormatter.ofPattern(PATTERN);
            return localDateTime.format(formatter);
        } catch (NullPointerException e) {
            log.error("Invalid LocalDateTime: " + localDateTime);
            throw new IllegalArgumentException("Invalid LocalDateTime: " + localDateTime);
        }
    }
}
