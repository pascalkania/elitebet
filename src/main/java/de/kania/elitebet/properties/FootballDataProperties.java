package de.kania.elitebet.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("footballdata")
public class FootballDataProperties {

    private String url;

    private String token;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
