package io.github.cafeteru.profile.prices.adapter.db;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Import(DateConverter.class)
class PriceRepositoryTest {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private DateConverter dateConverter;

    private final int productId = 35455;
    private final int brandId = 1;

    @BeforeEach
    public void init() {
        List<Price> priceList = List.of(
                createPrice("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, BigDecimal.valueOf(35.50)),
                createPrice("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, BigDecimal.valueOf(25.45)),
                createPrice("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, BigDecimal.valueOf(30.50)),
                createPrice("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, BigDecimal.valueOf(38.95))
        );
        priceRepository.saveAll(priceList);
    }

    @Test
    void when_getPrice_with_date_before_all_should_return_a_empty_list() {
        var dateTime = dateConverter.stringToLocalDateTime("2020-06-13-23.59.00");
        var found = priceRepository.getPrice(dateTime, productId, brandId);
        assertTrue(found.isEmpty());
    }

    @Test
    void when_getPrice_with_date_after_all_should_return_a_empty_list() {
        var localDateTime = dateConverter.stringToLocalDateTime("2026-06-13-23.59.00");
        var found = priceRepository.getPrice(localDateTime, productId, brandId);
        assertTrue(found.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2020-06-14-14.59.59",
            "2020-06-14-18.30.01",
            "2020-06-14-23.59.59",
            "2020-06-15-11.00.01",
            "2020-06-15-15.59.59"
    })
    void when_getPrice_with_date_without_conflicts_should_return_one_result(String applicationDate) {
        var localDateTime = dateConverter.stringToLocalDateTime(applicationDate);
        var found = priceRepository.getPrice(localDateTime, productId, brandId);
        assertEquals(1, found.size());
        assertEquals(BigDecimal.valueOf(35.50), found.get(0).getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2020-06-14-15.00.00",
            "2020-06-14-18.30.00",
            "2020-06-15-00.00.00",
            "2020-06-15-11.00.00",
            "2020-06-15-16.00.00"
    })
    void when_getPrice_with_date_with_conflicts_should_return_many_result(String applicationDate) {
        var localDateTime = dateConverter.stringToLocalDateTime(applicationDate);
        var found = priceRepository.getPrice(localDateTime, productId, brandId);
        assertFalse(found.isEmpty());
        assertNotEquals(1, found.size());
    }


    private Price createPrice(
            String startDate, String endDate, Integer priceList, Integer priority, BigDecimal price) {
        var startDateConverted = dateConverter.stringToLocalDateTime(startDate);
        var endDateConverted = dateConverter.stringToLocalDateTime(endDate);
        return Price.builder()
                .brandId(brandId)
                .startDate(startDateConverted)
                .endDate(endDateConverted)
                .priceList(priceList)
                .productId(productId)
                .priority(priority)
                .price(price)
                .curr("EUR")
                .build();
    }
}
