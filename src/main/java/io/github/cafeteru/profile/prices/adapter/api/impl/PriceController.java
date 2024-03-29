package io.github.cafeteru.profile.prices.adapter.api.impl;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.api.PriceApi;
import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.port.PricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {
    private final PricePort pricePort;
    private final DateConverter dateConverter;

    @Override
    public ResponseEntity<PriceRS> getPrice(String applicationDate, Integer idProduct, Integer idBrand) {
        var localDate = dateConverter.stringToLocalDateTime(applicationDate);
        var priceRS = pricePort.getPrice(localDate, idProduct, idBrand);
        return Objects.nonNull(priceRS) ?
                ResponseEntity.ok(priceRS) :
                ResponseEntity.noContent().build();
    }
}
