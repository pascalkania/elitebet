package de.kania.elitebet.service;

import de.kania.elitebet.domain.Teamdaten;
import de.kania.elitebet.domain.jsonfootballdata.Entity;
import de.kania.elitebet.domain.jsonfootballdata.StandingItem;
import de.kania.elitebet.properties.FootballDataProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FootballDataService {

    private static final Log LOGGER = LogFactory.getLog(FootballDataService.class);

    @Autowired
    private FootballDataProperties footballDataProperties;


    public Entity holeAktuelleTabellenDaten() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", footballDataProperties.getToken());
        headers.add("X-Response-Control", "full");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Entity> entity = new HttpEntity<Entity>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(footballDataProperties.getUrl());

        HttpEntity<Entity> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                Entity.class);

        LOGGER.info("hole aktuelle Tabellendaten");
        return response.getBody();
    }

    @Cacheable(CacheNames.TEAMLIST)
    public List<String> holeTeamliste() {
        LOGGER.info("hole TeamListe");
        return holeAktuelleTabellenDaten().standing.stream().map(e -> e.teamName).collect(Collectors.toList());
    }


    @Cacheable(CacheNames.AKTUELLE_TABLLENPLATZ_MAP)
    public Map<String, Teamdaten> holeAktuelleTabellenplatzMap() {
        return holeAktuelleTabellenDaten().standing.stream().map(t -> mappeStandingItemTOTeamdaten(t)).collect
                (Collectors.toMap((Teamdaten e) -> e.getName(), (e)-> e, (v1,v2) -> v1, LinkedHashMap::new));
    }

    private Teamdaten mappeStandingItemTOTeamdaten(StandingItem item) {
        Teamdaten team = new Teamdaten();
        team.setName(item.teamName);
        team.setPosition(item.position);
        team.setGespielteSpiele(item.playedGames);
        team.setPunkte(item.points);
        return team;
    }
}
