package de.kania.elitebet.database;

import de.kania.elitebet.domain.BenutzerAuswertung;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class Ranking {
    @Id
    private String id;
    private String spieltag;
    private Map<Integer, BenutzerAuswertung> rankingMap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpieltag() {
        return spieltag;
    }

    public void setSpieltag(String spieltag) {
        this.spieltag = spieltag;
    }

    public Map<Integer, BenutzerAuswertung> getRankingMap() {
        return rankingMap;
    }

    public void setRankingMap(Map<Integer, BenutzerAuswertung> rankingMap) {
        this.rankingMap = rankingMap;
    }
}
