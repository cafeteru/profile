package io.github.cafeteru.profile.prices.adapter.api;

import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/prices")
@Tag(name = "Prices", description = "Prices API")
public interface PriceApi {
    @Operation(summary = "REST endpoint for querying")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping("/consult")
    public ResponseEntity<PriceRS> consult(
            @Parameter(description = "Application date", example = "2020-06-14-00.00.00")
            String applicationDate,
            @Parameter(description = "Product identifier", example = "35455")
            Integer idProduct,
            @Parameter(description = "Brand identifier", example = "1")
            Integer idBrand
    );
}
