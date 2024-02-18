package io.github.cafeteru.profile.prices.adapter.api.mapper;

import io.github.cafeteru.profile.common.dates.DateConverter;
import io.github.cafeteru.profile.prices.adapter.api.dto.PriceRS;
import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = DateConverter.class
)
public interface PriceMapper {
    @Mapping(target = "finalPrice", source = "price")
    PriceRS toPriceRS(Price price);
}
