package com.java.ibor.calculator;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.stereotype.Component;

@Component
public class OptionPositionCalculationStrategy implements PositionCalculationStrategy{
    @Override
    public Position calculatePosition(Position newPosition, Position existingPosition) {
        return null;
    }
}
