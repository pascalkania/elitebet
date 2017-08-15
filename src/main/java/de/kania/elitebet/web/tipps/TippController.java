package de.kania.elitebet.web.tipps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tipps")
public class TippController {

    @RequestMapping
    public String handleIndexRequest(Model model) {
        return "tipps";
    }

}
