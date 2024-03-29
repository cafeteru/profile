package io.github.cafeteru.profile.prices.port;

import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;

import java.time.LocalDateTime;

public interface PricePort {
    PriceRS getPrice(LocalDateTime applicationDate, Integer idProduct, Integer idBrand);
}
