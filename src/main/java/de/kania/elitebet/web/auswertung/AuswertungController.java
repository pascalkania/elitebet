package de.kania.elitebet.web.auswertung;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auswertung")
public class AuswertungController {

    @RequestMapping
    public String handleIndexRequest(Model model) {
        return "auswertung";
    }

}
