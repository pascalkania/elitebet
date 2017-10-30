package de.kania.elitebet.web.admin;

import de.kania.elitebet.database.BenutzerTipp;
import de.kania.elitebet.service.BenutzerTippService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Log LOGGER = LogFactory.getLog(AdminController.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private BenutzerTippService benutzerTippService;

    @ModelAttribute("alleBenutzerTipps")
    public List<BenutzerTipp> leseAlleBenutzerTipps(){
        return benutzerTippService.holeAlleBenutzerTipps();
    }

    @RequestMapping
    @RolesAllowed("ADMIN")
    public String handleAdminRequest(Model model) {
        List<String> beans = Arrays.asList(context.getBeanDefinitionNames());
        model.addAttribute("beans", beans);
        return "admin";
    }

    @PostMapping("/tippFreigeben")
    public String tippFreigeben(@ModelAttribute("benutzerId") String benutzerId, BindingResult errors, Model model){
        LOGGER.info("Tipp freigeben für: "+ benutzerId);
        benutzerTippService.tippFreigebenFuerBenutzerId(benutzerId);
        return "redirect:/admin";
    }
}
