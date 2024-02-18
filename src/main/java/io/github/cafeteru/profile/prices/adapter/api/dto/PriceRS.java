package io.github.cafeteru.profile.prices.adapter.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRS {
    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private String startDate;
    private String endDate;
    private BigDecimal finalPrice;
}
