package de.kania.elitebet.database;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BenutzerRepository extends MongoRepository<BenutzerTipp, String> {
    public BenutzerTipp findBenutzerTippByName(String name);
}
