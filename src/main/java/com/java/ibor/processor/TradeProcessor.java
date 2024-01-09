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

    private final TradeToPositionConverterFactory factory;
    private final PositionCalculator calculator;
    private final PositionRepository positionRepository;

    public TradeProcessor(TradeToPositionConverterFactory factory, PositionCalculator calculator, PositionRepository positionRepository) {
        this.factory = factory;
        this.calculator = calculator;
        this.positionRepository = positionRepository;
    }

    public void process(String tradeMessage) {
        TradeMessageDeserializer deserializer = new TradeMessageDeserializer();
        Trade trade = deserializer.deserializeTradeMessage(tradeMessage);

        //Convert trade to position
        TradeToPositionConverter converter = factory.createConverter(trade.getAssetType());
        Position newPosition = converter.convert(trade);

        //Query existing position
        Position existingPosition = positionRepository.getPositionFromDatabase(trade.getSecurityId(), trade.getAccountId());

        //Calculate new position
        Position posToSave = calculator.calculatePosition(newPosition,existingPosition);

        //Save position
        if(existingPosition == null)
            positionRepository.insertPosition(posToSave);
        else
            positionRepository.updatePosition(posToSave);

    }
}
