package com.java.ibor.processor;
import com.java.ibor.entity.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class TradeMessageDeserializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Trade deserializeTradeMessage(String kafkaMessage) {
        try {
            return objectMapper.readValue(kafkaMessage, Trade.class);
        } catch (Exception e) {
            // Handle deserialization errors
            e.printStackTrace();
            return null; // Or throw an exception
        }
    }
}
