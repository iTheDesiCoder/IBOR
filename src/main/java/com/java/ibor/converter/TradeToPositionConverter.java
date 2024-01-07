package com.java.ibor.converter;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.stereotype.Component;


public interface TradeToPositionConverter {
    Position convert(Trade trade);
}
