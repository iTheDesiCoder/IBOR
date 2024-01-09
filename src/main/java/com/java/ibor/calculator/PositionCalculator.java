package com.java.ibor.calculator;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import com.java.ibor.repository.PositionRepository;
import org.springframework.stereotype.Component;

@Component
public class PositionCalculator {

    private final PositionCalculationStrategyFactory strategyFactory;

    public PositionCalculator(PositionCalculationStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public Position calculatePosition(Position newPosition, Position existingPosition) {
        PositionCalculationStrategy strategy = strategyFactory.createStrategy(newPosition.getAssetType());
        return strategy.calculatePosition(newPosition, existingPosition);
    }

}
