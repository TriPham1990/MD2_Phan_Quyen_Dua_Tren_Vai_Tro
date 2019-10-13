package com.codegym.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }

    @GetMapping(value = "/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }

    @GetMapping(value = "/Access_Denied")
    public ModelAndView accessDeniedPage() {
        ModelAndView modelAndView = new ModelAndView("accessDenied");
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }

    @GetMapping(value = "/dba")
    public ModelAndView dbaPage() {
        ModelAndView modelAndView = new ModelAndView("/dba");
        modelAndView.addObject("user", getPrincipal());
        return modelAndView;
    }
}
