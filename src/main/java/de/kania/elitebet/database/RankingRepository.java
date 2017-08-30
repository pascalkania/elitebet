package de.kania.elitebet.database;

import de.kania.elitebet.domain.BenutzerTipp;
import de.kania.elitebet.domain.Ranking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RankingRepository extends MongoRepository<Ranking, String> {
    Ranking findBySpieltag(String spieltag);
}
