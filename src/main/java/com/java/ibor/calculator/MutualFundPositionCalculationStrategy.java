package com.java.ibor.calculator;

import com.java.ibor.entity.Position;
import com.java.ibor.entity.Trade;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class MutualFundPositionCalculationStrategy implements PositionCalculationStrategy {

    @Override
    public Position calculatePosition(Position newPosition, Position existingPosition) {

        if(existingPosition == null) {
            return newPosition;
        }
        else
        {
            Position position = new Position();
            position.setSecurityId(newPosition.getSecurityId());
            position.setQuantity(newPosition.getQuantity() + existingPosition.getQuantity());
            return position;
        }
    }
}

