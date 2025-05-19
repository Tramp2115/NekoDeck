package com.lukas.TcgTracker.repository;

import com.lukas.TcgTracker.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {
}
