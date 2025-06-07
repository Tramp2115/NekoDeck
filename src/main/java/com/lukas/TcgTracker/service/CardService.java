package com.lukas.TcgTracker.service;

import java.io.IOException;

public interface CardService {
    void fetchCards() throws IOException, InterruptedException;
}
