package io.github.cafeteru.profile.prices.domain;

import io.github.cafeteru.profile.prices.adapter.api.mapper.PriceMapper;
import io.github.cafeteru.profile.prices.adapter.db.PriceRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceService priceService;

}
