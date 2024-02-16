package io.github.cafeteru.profile.prices.adapter.db;

import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p " +
            "WHERE p.startDate <= :localDateTime AND p.endDate >= :localDateTime AND " +
            "p.productId = :productId AND " +
            "p.brandId = :brandId AND " +
            "p.priority = (SELECT MIN(p2.priority) FROM Price p2 " +
            "WHERE p2.startDate <= :localDateTime AND p2.endDate >= :localDateTime) " +
            "ORDER BY p.price DESC")
    Page<Price> consult(
            @Param("localDateTime") LocalDateTime localDateTime,
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId,
            Pageable pageable);
}
