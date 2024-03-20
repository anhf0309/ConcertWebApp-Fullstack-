package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.Seat;
import com.anhfuentes.concertcapstone.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/concerts/{concertId}/seats")
public class SeatController {

    private final SeatService seatService;
    private static final int TOTAL_SEATS = 50;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // Display a list of all seats for a concert
    @GetMapping
    public String showSeats(@PathVariable Long concertId, Model model) {
        List<Seat> seats = seatService.getSeatsByConcert(concertId);
        model.addAttribute("seats", seats);
        model.addAttribute("concertId", concertId);
        model.addAttribute("totalSeats", TOTAL_SEATS);
        return "seats/list";
    }

    // Book a specific seat for a concert
    @PostMapping("/book/{seatId}")
    public String bookSeat(@PathVariable Long concertId, @PathVariable Long seatId, RedirectAttributes redirectAttributes) {
        try {
            seatService.bookSeat(seatId);
            redirectAttributes.addFlashAttribute("successMessage", "Seat booked successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to book seat: " + e.getMessage());
        }
        return "redirect:/concerts/{concertId}/seats";
    }

    // Release a specific seat for a concert
    @PostMapping("/release/{seatId}")
    public String releaseSeat(@PathVariable Long concertId, @PathVariable Long seatId, RedirectAttributes redirectAttributes) {
        if(seatService.releaseSeat(seatId)) {
            redirectAttributes.addFlashAttribute("successMessage", "Seat released successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Seat is already available.");
        }
        return "redirect:/concerts/{concertId}/seats";
    }

    // Display only available seats for a concert
    @GetMapping("/available")
    public String showAvailableSeats(@PathVariable Long concertId, Model model) {
        List<Seat> availableSeats = seatService.getAvailableSeatsByConcert(concertId);
        model.addAttribute("availableSeats", availableSeats);
        model.addAttribute("concertId", concertId);
        return "seats/available";
    }
}
