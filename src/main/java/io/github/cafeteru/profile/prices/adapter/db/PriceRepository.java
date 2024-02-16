package io.github.cafeteru.profile.prices.adapter.db;

import io.github.cafeteru.profile.prices.adapter.db.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
