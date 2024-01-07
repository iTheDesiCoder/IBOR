package com.java.ibor.converter;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.stereotype.Component;

@Component
public class EquityTradeToPositionConverter implements TradeToPositionConverter {
    @Override
    public Position convert(Trade trade) {
        // Implement conversion logic for equity trades
        return null;
    }
}
