package com.lukas.TcgTracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas.TcgTracker.entity.Card;
import com.lukas.TcgTracker.mapper.CardsMapper;
import com.lukas.TcgTracker.repository.CardRepository;
import com.lukas.TcgTracker.util.ScryfallFileDownloader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final WebClient webClient = WebClient.create();
    private final ScryfallFileDownloader scryfallFileDownloader;

    @Override
    public void fetchCards() throws IOException{
        scryfallFileDownloader.downloadJsonFile("default_cards", "cards.json");
        List<Card> cardList = CardsMapper.toCardList("cards.json");
        cardRepository.saveAll(cardList);
    }
}
