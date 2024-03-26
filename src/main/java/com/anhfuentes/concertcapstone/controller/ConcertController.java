package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.Booking;
import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.model.User;
import com.anhfuentes.concertcapstone.service.BookingService;
import com.anhfuentes.concertcapstone.service.ConcertService;
import com.anhfuentes.concertcapstone.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ConcertController {

    private final ConcertService concertService;
    private final BookingService bookingService;

    private final UserService userService;

    @Autowired
    public ConcertController(ConcertService concertService, BookingService bookingService, UserService userService) {
        this.concertService = concertService;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @GetMapping("/details/{id}")
    public String concertDetails(@PathVariable Long id, Model model) {
        try {
            Concert concert = concertService.getConcertById(id);
            model.addAttribute("concert", concert);
            return "details";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Concert not found");
            return "index";
        }
    }

    @PostMapping("/bookConcert")
    public String bookConcert(@RequestParam Long concertId, Principal principal) {

        String userEmail = principal.getName();
        User user = userService.findUserByEmail(userEmail);
        Concert concert = concertService.getConcertById(concertId);

        Booking booking = new Booking();
        booking.setConcert(concert);
        booking.setUser(user);
        booking.setNumTickets(1);
        booking.setBookingDate(LocalDateTime.now());
        bookingService.createBooking(booking);
        return "bookingConfirmation";
    }

    @PostMapping("/cancelBooking/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return "redirect:/userProfile";
    }

}
