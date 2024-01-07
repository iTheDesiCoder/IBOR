package com.java.ibor.converter;

import org.springframework.stereotype.Component;

@Component
public class TradeToPositionConverterFactory {
    public TradeToPositionConverter createConverter(String assetType) {
        switch (assetType.toLowerCase()) {
            case "equity":
                return new EquityTradeToPositionConverter();
            case "option":
                return new OptionTradeToPositionConverter();
            case "mutual fund":
                return new MutualFundTradeToPositionConverter();
            default:
                throw new IllegalArgumentException("Unsupported trade type: " + assetType);
        }
    }
}
