package de.kania.elitebet.domain;

public class Teamdaten {
	private String name;
	private Integer position;
	private Integer gespielteSpiele;
	private Integer punkte;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getGespielteSpiele() {
		return gespielteSpiele;
	}

	public void setGespielteSpiele(Integer gespielteSpiele) {
		this.gespielteSpiele = gespielteSpiele;
	}

	public Integer getPunkte() {
		return punkte;
	}

	public void setPunkte(Integer punkte) {
		this.punkte = punkte;
	}
}
