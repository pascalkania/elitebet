package de.kania.elitebet.web.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping
    @RolesAllowed("ADMIN")
    public String handleAdminRequest(Model model) {
        List<String> beans = Arrays.asList(context.getBeanDefinitionNames());
        model.addAttribute("beans", beans);
        return "admin";
    }
}
