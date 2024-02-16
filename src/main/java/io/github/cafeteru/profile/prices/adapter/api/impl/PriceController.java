package io.github.cafeteru.profile.prices.adapter.api.impl;

import io.github.cafeteru.profile.prices.adapter.api.PriceApi;
import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {
    @Override
    public ResponseEntity<PriceRS> consult(String applicationDate, Integer idProduct, Integer idBrand) {
        return null;
    }
}
