package com.java.ibor.converter;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.stereotype.Component;

@Component
public class OptionTradeToPositionConverter implements TradeToPositionConverter {
    @Override
    public Position convert(Trade trade) {
        // Implement conversion logic for option trades
        return null;
    }
}
