package com.java.ibor.calculator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PositionCalculationStrategyFactory {
    private final Map<String, PositionCalculationStrategy> strategyMap;

    public PositionCalculationStrategyFactory() {
        // Initialize the strategy map with trade type mappings
        strategyMap = new HashMap<>();
        strategyMap.put("equity", new EquityPositionCalculationStrategy());
        strategyMap.put("option", new OptionPositionCalculationStrategy());
        strategyMap.put("mutual_fund", new MutualFundPositionCalculationStrategy());
        // Add more mappings as needed for other trade types
    }

    public PositionCalculationStrategy createStrategy(String tradeType) {
        PositionCalculationStrategy strategy = strategyMap.get(tradeType.toLowerCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported trade type: " + tradeType);
        }
        return strategy;
    }
}
