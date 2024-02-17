package io.github.cafeteru.profile.prices.adapter.api.impl;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.domain.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceControllerTest {
    @Mock
    private PriceService priceService;
    private final DateConverter dateConverter = new DateConverter();
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        priceController = new PriceController(priceService, dateConverter);
    }

    @Test
    void when_getPrice_not_obtain_a_price_should_return_empty() {
        when(priceService.getPrice(any(), any(), any())).thenReturn(null);
        var result = priceController.getPrice("2020-06-13-23.59.00", 35455, 1);
        assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    void when_getPrice_obtain_a_price_should_return_ok() {
        var priceRS = PriceRS.builder().build();
        when(priceService.getPrice(any(), any(), any())).thenReturn(priceRS);
        var result = priceController.getPrice("2020-06-13-23.59.00", 35455, 1);
        assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
        assertEquals(priceRS, result.getBody());
    }
}
