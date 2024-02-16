package io.github.cafeteru.profile.prices.domain;

import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.adapter.api.mapper.PriceMapper;
import io.github.cafeteru.profile.prices.adapter.db.PriceRepository;
import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import io.github.cafeteru.profile.prices.port.PricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class PriceService implements PricePort {
    private final PriceRepository repository;
    private final PriceMapper priceMapper;

    @Override
    public PriceRS getPrice(LocalDateTime applicationDate, Integer idProduct, Integer idBrand) {
        var priceList = repository.getPrice(applicationDate, idProduct, idBrand);
        var result = priceList.size() > 1 ?
                priceList.stream().max(Comparator.comparing(Price::getPriority)).get() :
                priceList.get(0);
        return priceMapper.toPriceRS(result);
    }
}
