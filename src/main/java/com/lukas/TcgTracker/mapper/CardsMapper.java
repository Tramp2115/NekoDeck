package com.lukas.TcgTracker.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lukas.TcgTracker.entity.Card;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CardsMapper {
    private static ObjectMapper objectMapper;

    public static List<Card> toCardList(String filename) throws IOException {
        objectMapper = JsonMapper.builder()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .addModule(new JavaTimeModule())
                .build();
        File jsonFile = new File(filename);
        return objectMapper.readValue(jsonFile, new TypeReference<List<Card>>() {});
    }
}
