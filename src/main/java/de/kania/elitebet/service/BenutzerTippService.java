package de.kania.elitebet.service;

import de.kania.elitebet.database.BenutzerRepository;
import de.kania.elitebet.database.BenutzerTipp;
import de.kania.elitebet.domain.Teamdaten;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        Optional<BenutzerTipp> benutzer = benutzerRepository.findById(benutzerId);
        if(benutzer.isPresent()){
            BenutzerTipp benutzerTipp = benutzer.get();
            benutzerTipp.setTippVorhanden(false);
            benutzerRepository.save(benutzerTipp);
        }
    }

    public Map<String,Integer> berechneAktuelleDifferenzMapFuerBenutzer(String username){
        Map<String, Teamdaten> aktuelleTabellenplatzMap = footballDataService.holeAktuelleTabellenplatzMap();
        BenutzerTipp benutzerTipp = holeBenutzerTipp(username);
        Map<Integer, String> benutzerTippMap = benutzerTipp.getTippliste();

        Map<String, Integer> differenzMap = new HashMap<>();
        for (Map.Entry<Integer, String> e : benutzerTippMap.entrySet()) {
            Teamdaten teamdaten = aktuelleTabellenplatzMap.get(e.getValue());
            Integer platz = teamdaten.getPosition();
            Integer tippPosition = e.getKey();
            Integer absoluteDifferenz = Math.abs(platz-tippPosition);
            differenzMap.put(e.getValue(),absoluteDifferenz);
        }
        return differenzMap;
    }
}
