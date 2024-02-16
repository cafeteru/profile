package io.github.cafeteru.profile.prices.adapter.api.impl;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.api.PriceApi;
import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.port.PricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {
    private final PricePort pricePort;
    private final DateConverter dateConverter;

    @Override
    public ResponseEntity<PriceRS> consult(String applicationDate, Integer idProduct, Integer idBrand) {
        var localDate = dateConverter.stringToLocalDateTime(applicationDate);
        var priceRS = pricePort.consult(localDate, idProduct, idBrand);
        return ResponseEntity.ok(priceRS);
    }
}
