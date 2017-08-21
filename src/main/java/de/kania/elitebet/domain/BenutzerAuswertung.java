package de.kania.elitebet.domain;

public class BenutzerAuswertung {
    String name;
    int punktzahl;

    public BenutzerAuswertung(String name, int punktzahl) {

        this.name = name;
        this.punktzahl = punktzahl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPunktzahl() {
        return punktzahl;
    }

    public void setPunktzahl(int punktzahl) {
        this.punktzahl = punktzahl;
    }
}
