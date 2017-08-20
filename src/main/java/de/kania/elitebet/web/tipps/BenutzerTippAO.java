package de.kania.elitebet.web.tipps;

import de.kania.elitebet.domain.BenutzerTipp;

import java.util.Map;

public class BenutzerTippAO {

    private BenutzerTipp benutzerTipp;
    private String name;
    private Map<String,Integer> aktuelleBenutzerDifferenzMap;

    public BenutzerTippAO(BenutzerTipp benutzerTipp) {
        this.benutzerTipp = benutzerTipp;
        this.name = benutzerTipp.getName();
    }

    public static BenutzerTippAO erzeugeBenutzerTippAO(BenutzerTipp benutzerTipp) {
        return new BenutzerTippAO(benutzerTipp);
    }

    public BenutzerTipp getBenutzerTipp() {
        return benutzerTipp;
    }

    public void setBenutzerTipp(BenutzerTipp benutzerTipp) {
        this.benutzerTipp = benutzerTipp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getAktuelleBenutzerDifferenzMap() {
        return aktuelleBenutzerDifferenzMap;
    }

    public void setAktuelleBenutzerDifferenzMap(Map<String, Integer> aktuelleBenutzerDifferenzMap) {
        this.aktuelleBenutzerDifferenzMap = aktuelleBenutzerDifferenzMap;
    }
}
