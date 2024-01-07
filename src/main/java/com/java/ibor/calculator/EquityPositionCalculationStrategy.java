package com.java.ibor.calculator;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EquityPositionCalculationStrategy implements PositionCalculationStrategy {

    @Bean
    @Override
    public Position calculatePosition(Position newPosition, Position existingPosition) {
        return null;
    }
}
