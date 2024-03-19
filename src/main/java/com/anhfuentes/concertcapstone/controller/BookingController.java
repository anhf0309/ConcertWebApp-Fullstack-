package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.Booking;
import com.anhfuentes.concertcapstone.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // List all bookings
    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings/list";
    }

    // Show form to add a new booking
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "bookings/add";
    }

    // Process the form for adding a new booking
    @PostMapping("/create")
    public String createBooking(@ModelAttribute Booking booking, RedirectAttributes redirectAttributes) {
        bookingService.createBooking(booking);
        redirectAttributes.addFlashAttribute("message", "Booking created successfully.");
        return "redirect:/bookings";
    }

    // Show form to update an existing booking
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Booking booking = bookingService.getBookingById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invalid booking Id: " + id));
        model.addAttribute("booking", booking);
        return "bookings/edit";
    }

    // Process the form for updating a booking
    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Long id, @ModelAttribute Booking booking, RedirectAttributes redirectAttributes) {
        booking.setId(id);
        bookingService.updateBooking(booking);
        redirectAttributes.addFlashAttribute("message", "Booking updated successfully.");
        return "redirect:/bookings";
    }

    // Delete a booking
    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        bookingService.cancelBooking(id);
        redirectAttributes.addFlashAttribute("message", "Booking deleted successfully.");
        return "redirect:/bookings";
    }

    // List bookings by user ID
    @GetMapping("/user/{userId}")
    public String listBookingsByUserId(@PathVariable("userId") Long userId, Model model) {
        List<Booking> bookings = bookingService.getBookingsByUser(userId);
        model.addAttribute("bookings", bookings);
        return "bookings/userBookings"; // Assumes a Thymeleaf template named "userBookings.html"
    }
}
