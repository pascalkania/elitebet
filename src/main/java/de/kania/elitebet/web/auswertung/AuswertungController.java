package de.kania.elitebet.web.auswertung;

import de.kania.elitebet.database.BenutzerRepository;
import de.kania.elitebet.domain.BenutzerAuswertung;
import de.kania.elitebet.domain.jsonfootballdata.Entity;
import de.kania.elitebet.service.AuswertungService;
import de.kania.elitebet.service.FootballDataService;
import de.kania.elitebet.service.RankingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auswertung")
public class AuswertungController {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private FootballDataService footballDataService;

    @Autowired
    private AuswertungService auswertungService;

    @Autowired
    private RankingService rankingService;

    private static final Log LOGGER = LogFactory.getLog(AuswertungController.class);

    @RequestMapping
    public String handleIndexRequest(Model model) {
        Map<String, Integer> aktuelleTabellenplatzMap = footballDataService.holeAktuelleTabellenplatzMap();
        model.addAttribute("teams", aktuelleTabellenplatzMap);
        Map<Integer,BenutzerAuswertung> rankingMap = auswertungService.berechneRankingMap();
        model.addAttribute("rankingmap",rankingMap);
        String matchday = holeSpielTag();
        rankingService.speicherRankingZuSpieltag(matchday, rankingMap);
        return "auswertung";
    }

    private String holeSpielTag() {
        Entity aktuelleTabellenDaten = footballDataService.holeAktuelleTabellenDaten();
        return aktuelleTabellenDaten.matchday;
    }
}
