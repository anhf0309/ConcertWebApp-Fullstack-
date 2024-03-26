package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.service.BookingService;
import com.anhfuentes.concertcapstone.service.ConcertService;
import com.anhfuentes.concertcapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final BookingService bookingService;

    private final ConcertService concertService;

    @Autowired
    public MainController(UserService userService, BookingService bookingService, ConcertService concertService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.concertService = concertService;
    }

    
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

    @ResponseBody
    @GetMapping("/logoutSuccess")
    public String logoutResponse() {
        System.out.println("IN MainController -> logoutResponse()");
        return "Logged Out!!!!";
    }



    @GetMapping("/userProfile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findUserByEmail(currentPrincipalName);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("bookings", bookingService.getBookingsByUser(user.getId()));
            return "userProfile";
        } else {
            return "login";
        }
    }

    @GetMapping("/index")
    public String home(Model model) {
        List<Concert> concerts = concertService.getAllConcerts();
        model.addAttribute("concerts", concerts);
        return "index";
    }

    @GetMapping("/{id}")
    public String showConcertDetails(@PathVariable Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        model.addAttribute("concert", concert);
        return "details";
    }


}
