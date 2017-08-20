package de.kania.elitebet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public ModelAndView handleIndexRequest() {
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/tipps");
        return model;
    }

}
