package io.github.cafeteru.profile.prices.domain;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.api.mapper.PriceMapper;
import io.github.cafeteru.profile.prices.adapter.api.mapper.PriceMapperImpl;
import io.github.cafeteru.profile.prices.adapter.db.PriceRepository;
import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Import({DateConverter.class, PriceMapperImpl.class})
public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceService priceService;

    @Autowired
    private PriceMapper priceMapper;

    @Autowired
    private DateConverter dateConverter;

    private Price price;

    @BeforeEach
    void setUp() {
        price = Price.builder()
                .productId(1)
                .brandId(1)
                .priceList(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .price(BigDecimal.ONE)
                .priority(0)
                .build();
        priceService = new PriceService(priceRepository, priceMapper);
    }

    @Test
    void when_getPrice_not_found_results_should_return_null() {
        when(priceRepository.getPrice(any(), anyInt(), anyInt())).thenReturn(Collections.emptyList());
        var result = priceService.getPrice(LocalDateTime.now(), 1, 1);
        assertNull(result);
    }

    @Test
    void when_getPrice_found_one_result_should_return_it() {
        when(priceRepository.getPrice(any(), anyInt(), anyInt())).thenReturn(Collections.singletonList(price));
        var expected = priceMapper.toPriceRS(price);
        var result = priceService.getPrice(LocalDateTime.now(), 1, 1);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void when_getPrice_found_many_result_should_return_the_one_with_the_highest_priority() {
        var pricePriority = Price.builder()
                .productId(10)
                .brandId(10)
                .priceList(10)
                .startDate(LocalDateTime.MAX)
                .endDate(LocalDateTime.MAX)
                .price(BigDecimal.TEN)
                .priority(1)
                .build();
        when(priceRepository.getPrice(any(), anyInt(), anyInt())).thenReturn(List.of(price, pricePriority));
        var expected = priceMapper.toPriceRS(pricePriority);
        var result = priceService.getPrice(LocalDateTime.now(), 1, 1);
        assertNotNull(result);
        assertEquals(expected, result);
    }
}
