package de.kania.elitebet.web.auswertung;

import de.kania.elitebet.database.BenutzerRepository;
import de.kania.elitebet.domain.jsonfootballdata.Entity;
import de.kania.elitebet.service.FootballDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/auswertung")
public class AuswertungController {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private FootballDataService footballDataService;

    private static final Log LOGGER = LogFactory.getLog(AuswertungController.class);

    @RequestMapping
    public String handleIndexRequest(Model model) {
        Map<String, Integer> aktuelleTabellenplatzMap = footballDataService.holeAktuelleTabellenplatzMap();
        model.addAttribute("teams", aktuelleTabellenplatzMap);
        return "auswertung";
    }
}
