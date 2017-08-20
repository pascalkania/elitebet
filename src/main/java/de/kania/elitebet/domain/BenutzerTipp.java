package de.kania.elitebet.domain;


import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class BenutzerTipp {
    @Id
    private String id;
    private String name;
    private Map<Integer,String> tippliste;
    private boolean tippVorhanden = false;

    public BenutzerTipp(){}

    public BenutzerTipp(String username) {
        this.name = username;
        tippliste = new TreeMap<>();
        IntStream.rangeClosed(1,18).forEach(i -> tippliste.put(i,new String()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getTippliste() {
        return tippliste;
    }

    public void setTippliste(Map<Integer, String> tippliste) {
        this.tippliste = tippliste;
    }

    public static BenutzerTipp erzeugeMitBenutzername(String username) {
        return new BenutzerTipp(username);
    }

    public boolean isTippVorhanden() {
        return tippVorhanden;
    }

    public void setTippVorhanden(boolean tippVorhanden) {
        this.tippVorhanden = tippVorhanden;
    }
}
