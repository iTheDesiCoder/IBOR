package com.java.ibor.calculator;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import com.java.ibor.repository.PositionRepository;
import org.springframework.stereotype.Component;

@Component
public class PositionCalculator {
    public Position calculatePosition(Position newPosition, Position existingPosition) {

        PositionCalculationStrategyFactory strategyFactory = new PositionCalculationStrategyFactory();
        PositionCalculationStrategy strategy = strategyFactory.createStrategy(newPosition.getAssetType());
        return strategy.calculatePosition(newPosition, existingPosition);
    }

}
