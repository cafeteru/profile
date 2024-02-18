package io.github.cafeteru.profile.prices;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.db.PriceRepository;
import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
class LoadPrices {
    private final DateConverter dateConverter;

    @Bean
    protected CommandLineRunner initDatabase(PriceRepository repository) {
        return args -> {
            var prices = createPrices();
            prices.forEach(price -> log.info("Preloading price" + repository.save(price)));
        };
    }

    public List<Price> createPrices() {
        return List.of(
                createPrice("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, BigDecimal.valueOf(35.50)),
                createPrice("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, BigDecimal.valueOf(25.45)),
                createPrice("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, BigDecimal.valueOf(30.50)),
                createPrice("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, BigDecimal.valueOf(38.95))
        );
    }

    private Price createPrice(
            String startDate, String endDate, Integer priceList, Integer priority, BigDecimal price) {
        var startDateConverted = dateConverter.stringToLocalDateTime(startDate);
        var endDateConverted = dateConverter.stringToLocalDateTime(endDate);
        return Price.builder()
                .brandId(1)
                .startDate(startDateConverted)
                .endDate(endDateConverted)
                .priceList(priceList)
                .productId(35455)
                .priority(priority)
                .price(price)
                .curr("EUR")
                .build();
    }
}
