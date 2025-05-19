package com.lukas.TcgTracker.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukas.TcgTracker.entity.Card;
import com.lukas.TcgTracker.exception.BulkDataUriNotFound;
import com.lukas.TcgTracker.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int fetchCards() throws IOException, InterruptedException {
        String bulkUrl = "https://api.scryfall.com/bulk-data";
        JsonNode jsonNode = restTemplate.getForObject(bulkUrl, JsonNode.class);

        String downloadUri = null;
        for (JsonNode node : jsonNode.get("data")) {
            var nodeType = node.get("type");
            if("default_cards".equals(node.get("type").asText())) {
                downloadUri = node.get("download_uri").asText();
                break;
            }
        }
        if (downloadUri == null) {
            throw new BulkDataUriNotFound("Bulk data download URI not found");
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(downloadUri))
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        try (InputStream inputStream = response.body()) {
            List<Card> cards = objectMapper.readValue(inputStream, new TypeReference<>() {});
            cardRepository.saveAll(cards);
            return cards.size();
        }
    }
}
