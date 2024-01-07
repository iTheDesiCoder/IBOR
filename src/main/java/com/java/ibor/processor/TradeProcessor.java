package com.java.ibor.processor;

import com.java.ibor.calculator.PositionCalculator;
import com.java.ibor.converter.TradeToPositionConverter;
import com.java.ibor.converter.TradeToPositionConverterFactory;
import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import com.java.ibor.repository.PositionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TradeProcessor {


    public void process(String tradeMessage) {
        TradeMessageDeserializer deserializer = new TradeMessageDeserializer();
        Trade trade = deserializer.deserializeTradeMessage(tradeMessage);

        //Convert trade to position
        TradeToPositionConverterFactory factory = new TradeToPositionConverterFactory();
        TradeToPositionConverter converter = factory.createConverter(trade.getAssetType());
        Position newPosition = converter.convert(trade);

        //Query existing position
        PositionRepository positionRepository = new PositionRepository();
        Position existingPosition = positionRepository.getPositionFromDatabase(trade.getSecurityId(), trade.getAccountId());

        //Calculate new position
        PositionCalculator calculator = new PositionCalculator();
        Position posToSave = calculator.calculatePosition(newPosition,existingPosition);

        //Save position
        if(existingPosition == null)
            positionRepository.insertPosition(posToSave);
        else
            positionRepository.updatePosition(posToSave);
    }
}
