package io.github.cafeteru.profile.prices.adapter.db;

import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(name = "Prices.getPrice")
    List<Price> getPrice(
            @Param("dateTime") LocalDateTime dateTime,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId);
}
