package com.gamasoft.hps.sab.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.gamasoft.hps.sab.dto.UserDto;
import com.gamasoft.hps.sab.service.SecurityService;


/**
 * FundsController class will expose a series of RESTful endpoints
 */
@Controller
public class SecurityController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private View jsonView;
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    public static boolean isEmpty(String s_p) {
        return (null == s_p) || s_p.trim().length() == 0;
    }

    public void setSecSecive_i(SecurityService secSecive_i) {
        this.securityService = secSecive_i;
    }

    /**
     * Injector methods.
     *
     * @param view the new json view
     */
    public void setJsonView(View view) {
        jsonView = view;
    }

    @RequestMapping(value = {"/login", "/", ""}, method = RequestMethod.GET)
    public String start() {
        return "login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String login(ModelMap model, Principal principal, HttpSession session) throws Exception {
        UserDto user = securityService.getUser(principal.getName());
        session.setAttribute("userLoggedIn", true);
        session.setAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        return "main";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "login";
    }
    
    @RequestMapping(value = "/emora", method = RequestMethod.GET)
    public UserDto emora(ModelMap model) {
        return new UserDto();
    }
}
