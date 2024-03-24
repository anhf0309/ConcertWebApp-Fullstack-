package com.anhfuentes.concertcapstone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/")
public class MainController {
    
    @GetMapping("/")
    public String root() {
        System.out.println("IN MainController -> root()");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                System.out.println("USER ROLE = " + authority.getAuthority());
                return "redirect:/admin";
            }
        }

        System.out.println("USER ROLE Defaults to Regular USER");
        return "redirect:/userProfile";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        System.out.println("IN MainController -> admin()");
        return "admin";
    }

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("IN MainController -> login()");
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        System.out.println("IN MainController -> userIndex()");
        return "userProfile";
    }

    @ResponseBody
    @GetMapping("/logoutSuccess")
    public String logoutResponse() {
        System.out.println("IN MainController -> logoutResponse()");
        return "Logged Out!!!!";
    }
}
