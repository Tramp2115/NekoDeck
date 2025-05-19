package com.lukas.TcgTracker.controller;


import com.lukas.TcgTracker.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping(path = "/import")
    public ResponseEntity<String> loadCards() {
        try {
            int count = cardService.fetchCards();
            return ResponseEntity.ok(count + "cards imported");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
