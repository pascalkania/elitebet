package de.kania.elitebet.database;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RankingRepository extends MongoRepository<Ranking, String> {
    Ranking findBySpieltag(String spieltag);
}
