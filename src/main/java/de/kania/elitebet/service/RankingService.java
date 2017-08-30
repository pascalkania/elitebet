package de.kania.elitebet.service;

import de.kania.elitebet.database.RankingRepository;
import de.kania.elitebet.domain.BenutzerAuswertung;
import de.kania.elitebet.domain.Ranking;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Maps;

import java.util.Collections;
import java.util.Map;

@Service
public class RankingService {
    private static final Log LOGGER = LogFactory.getLog(RankingService.class);
    @Autowired
    RankingRepository rankingRepository;


    public void speicherRankingZuSpieltag(String spieltag, Map<Integer, BenutzerAuswertung> rankingMap) {
        Ranking spieltagRanking = rankingRepository.findBySpieltag(spieltag);
        if(spieltagRanking == null){
            Ranking ranking = new Ranking();
            ranking.setSpieltag(spieltag);
            ranking.setRankingMap(rankingMap);
            rankingRepository.save(ranking);
            LOGGER.info("Ranking gespeichert für Spieltag:" + spieltag);
        }
    }
}
