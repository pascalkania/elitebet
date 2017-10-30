package de.kania.elitebet.service;

import de.kania.elitebet.domain.BenutzerAuswertung;
import de.kania.elitebet.database.BenutzerTipp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AuswertungService {
    private static final Log LOGGER = LogFactory.getLog(AuswertungService.class);

    @Autowired
    private BenutzerTippService benutzerTippService;

    @Autowired
    private FootballDataService footballDataService;

    public List<BenutzerAuswertung> berechneAuswertungslisteFuerAlleBenutzer(){
        List<BenutzerTipp> benutzerTipps = benutzerTippService.holeAlleBenutzerTipps();

        List<BenutzerAuswertung> auswertungList = new ArrayList<>();
        for (BenutzerTipp benutzerTipp: benutzerTipps) {
            Map<String, Integer> differenzMapFuerBenutzer = benutzerTippService
                    .berechneAktuelleDifferenzMapFuerBenutzer(benutzerTipp.getName());
            int punkte = differenzMapFuerBenutzer.values().stream().mapToInt(Integer::intValue).sum();
            BenutzerAuswertung benutzerAuswertung = new BenutzerAuswertung(benutzerTipp.getName(),punkte);
            auswertungList.add(benutzerAuswertung);
        }
        return auswertungList;
    }

    public Map<Integer, BenutzerAuswertung> berechneRankingMap() {
        List<BenutzerAuswertung> auswertungList = berechneAuswertungslisteFuerAlleBenutzer();
        List<BenutzerAuswertung> collect = auswertungList.stream().sorted((a, b) -> a.getPunktzahl() - b.getPunktzahl()).collect(Collectors.toList());
        Map<Integer,BenutzerAuswertung> rankingmap = new TreeMap<>();
        IntStream.range(0,collect.size()).forEach(i -> rankingmap.put(i+1,collect.get(i)));
        return rankingmap;
    }
}
