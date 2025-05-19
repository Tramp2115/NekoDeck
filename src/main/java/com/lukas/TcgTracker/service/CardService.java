package com.lukas.TcgTracker.service;

import java.io.IOException;

public interface CardService {
    int fetchCards() throws IOException, InterruptedException;
}
