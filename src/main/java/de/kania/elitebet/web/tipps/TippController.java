package de.kania.elitebet.web.tipps;

import de.kania.elitebet.domain.BenutzerTipp;
import de.kania.elitebet.domain.jsonfootballdata.Entity;
import de.kania.elitebet.service.FootballDataService;
import de.kania.elitebet.service.BenutzerTippService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tipps")
public class TippController {

    private static final Log LOGGER = LogFactory.getLog(TippController.class);

    @Autowired
    private BenutzerTippService benutzerTippService;

    @Autowired
    private FootballDataService footballDataService;

    @ModelAttribute("benutzertipp")
    public BenutzerTipp holeBenutzerTipp(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return benutzerTippService.holeBenutzerTipp(authentication.getName());
    }

    @ModelAttribute("ligadaten")
    public Entity holeLigadaten(){
        return footballDataService.holeAktuelleTabellenDaten();
    }


    @ModelAttribute("alleBenutzerTipps")
    public List<BenutzerTippAO> leseAlleBenutzerTipps() {
        List<BenutzerTipp> benutzerTipps = benutzerTippService.holeAlleBenutzerTipps();
        return benutzerTipps.stream().map(e -> erzeugeBenutzerTippAOMitDifferenzen(e)).collect(Collectors.toList());
    }

    private BenutzerTippAO erzeugeBenutzerTippAOMitDifferenzen(BenutzerTipp benutzerTipp) {
        BenutzerTippAO benutzerTippAO = BenutzerTippAO.erzeugeBenutzerTippAO(benutzerTipp);
        benutzerTippAO.setAktuelleBenutzerDifferenzMap(benutzerTippService
                .berechneAktuelleDifferenzMapFuerBenutzer(benutzerTipp.getName()));
        return benutzerTippAO;
    }

    @ModelAttribute("teamliste")
    public List<String> holeTeamliste(){
        return footballDataService.holeTeamliste();
    }

    @RequestMapping
    public String zeigeTippUebersicht(Model model) {
        return "tippuebersicht";
    }

    @GetMapping("/user")
    public String zeigeUserTipp(Model model){
        return "tippeingabe";
    }

    @PostMapping("/speicherTipp")
    public String speicherUserTipp(@ModelAttribute("benutzertipp") BenutzerTipp benutzertipp, BindingResult errors, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LOGGER.info("Tipp von " + username + " wurde abgegeben");
        benutzertipp.setTippVorhanden(Boolean.TRUE);
        benutzerTippService.speicherBenutzerTipp(benutzertipp);
        return "redirect:/tipps";
    }
}
