package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.Booking;
import com.anhfuentes.concertcapstone.model.Payment;
import com.anhfuentes.concertcapstone.service.BookingService;
import com.anhfuentes.concertcapstone.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    @Autowired
    public PaymentController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }



    @GetMapping
    public String listRecentPayments(Model model) {
        model.addAttribute("payments", paymentService.getRecentPayments());
        return "payments/list";
    }

    @GetMapping("/process/{bookingId}")
    public String showProcessPaymentForm(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Booking ID: " + bookingId));
        Payment payment = new Payment();
        payment.setBooking(booking);
        model.addAttribute("payment", payment);

        return "payments/process";
    }

    @PostMapping("/process")
    public String processPayment(@ModelAttribute Payment payment, RedirectAttributes redirectAttributes) {
        paymentService.processPayment(payment);
        redirectAttributes.addFlashAttribute("message", "Payment processed successfully.");
        return "redirect:/payments";
    }

    @GetMapping("/refund/{paymentId}")
    public String refundPayment(@PathVariable Long paymentId, RedirectAttributes redirectAttributes) {
        boolean refunded = paymentService.refundPayment(paymentId);
        if(refunded) {
            redirectAttributes.addFlashAttribute("message", "Payment refunded successfully.");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to refund payment.");
        }
        return "redirect:/payments";
    }

    @GetMapping("/booking/{bookingId}")
    public String getPaymentByBooking(@PathVariable Long bookingId, Model model) {
        Payment payment = paymentService.getPaymentByBooking(bookingId);
        model.addAttribute("payment", payment);
        return "payments/detail";
    }
}
