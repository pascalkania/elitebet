package de.kania.elitebet.service;

import de.kania.elitebet.database.BenutzerRepository;
import de.kania.elitebet.domain.BenutzerTipp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BenutzerTippService {

    private static final Log LOGGER = LogFactory.getLog(BenutzerTippService.class);
    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private FootballDataService footballDataService;

    public BenutzerTipp holeBenutzerTipp(String username){
        BenutzerTipp benutzerTipp = benutzerRepository.findBenutzerTippByName(username);
        if(benutzerTipp == null){
            benutzerTipp = BenutzerTipp.erzeugeMitBenutzername(username);
        }
        return benutzerTipp;
    }

    public void speicherBenutzerTipp(BenutzerTipp benutzerTipp){
        BenutzerTipp savedTipp = benutzerRepository.save(benutzerTipp);
        LOGGER.info("BenutzerTipp wurde gespeichert unter id: " + savedTipp.getId());

    }


    public List<BenutzerTipp> holeAlleBenutzerTipps() {
        return benutzerRepository.findAll();
    }

    @Transactional
    public void tippFreigebenFuerBenutzerId(String benutzerId) {
        BenutzerTipp benutzer = benutzerRepository.findOne(benutzerId);
        benutzer.setTippVorhanden(false);
        benutzerRepository.save(benutzer);
    }

    public Map<String,Integer> berechneAktuelleDifferenzMapFuerBenutzer(String username){
        Map<String, Integer> aktuelleTabellenplatzMap = footballDataService.holeAktuelleTabellenplatzMap();
        BenutzerTipp benutzerTipp = holeBenutzerTipp(username);
        Map<Integer, String> benutzerTippMap = benutzerTipp.getTippliste();

        Map<String, Integer> differenzMap = new HashMap<>();
        for (Map.Entry<Integer, String> e : benutzerTippMap.entrySet()) {
            Integer platz = aktuelleTabellenplatzMap.get(e.getValue());
            Integer tippPosition = e.getKey();
            Integer absoluteDifferenz = Math.abs(platz-tippPosition);
            differenzMap.put(e.getValue(),absoluteDifferenz);
        }
        return differenzMap;
    }
}
