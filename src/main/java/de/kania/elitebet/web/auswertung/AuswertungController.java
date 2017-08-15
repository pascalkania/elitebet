package de.kania.elitebet.web.auswertung;

import de.kania.elitebet.web.domain.jsonfootballdata.Entity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

@Controller
@RequestMapping("/auswertung")
public class AuswertungController {

    private static final Log LOGGER = LogFactory.getLog(AuswertungController.class);

    @RequestMapping
    public String handleIndexRequest(Model model) {
        Entity entity = holeAktuelleTabellenDaten();
        model.addAttribute("teams",entity);
        return "auswertung";
    }

    private Entity holeAktuelleTabellenDaten() {
        String url = "http://api.football-data.org/v1/competitions/452/leagueTable";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token","e1da70e1200343fab444c036b0081ad2");
        headers.add("X-Response-Control","full");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Entity> entity = new HttpEntity<Entity>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity<Entity> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                Entity.class);

        LOGGER.info(response.getBody().standing);
        return response.getBody();
    }

}
