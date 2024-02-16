package io.github.cafeteru.profile.prices.domain;

import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.adapter.api.mapper.PriceMapper;
import io.github.cafeteru.profile.prices.adapter.db.PriceRepository;
import io.github.cafeteru.profile.prices.port.PricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements PricePort {
    private final PriceRepository repository;
    private final PriceMapper priceMapper;

    @Override
    public PriceRS consult(LocalDateTime applicationDate, Integer idProduct, Integer idBrand) {
        var priceRS = repository.findAll().get(0);
        return priceMapper.toPriceRS(priceRS);
    }
}
