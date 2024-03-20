package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    private final ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping
    public String listConcerts(Model model) {
        model.addAttribute("concerts", concertService.getAllConcerts());
        return "concerts/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("concert", new Concert());
        return "concerts/add";
    }

    @PostMapping("/create")
    public String addConcert(@ModelAttribute("concert") Concert concert, RedirectAttributes redirectAttributes) {
        concertService.createConcert(concert);
        redirectAttributes.addFlashAttribute("message", "Concert added successfully");
        return "redirect:/concerts";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        model.addAttribute("concert", concert);
        return "concerts/edit";
    }

    @PostMapping("/update/{id}")
    public String updateConcert(@PathVariable Long id, @ModelAttribute("concert") Concert concert, RedirectAttributes redirectAttributes) {
        concert.setId(id);
        concertService.updateConcert(concert);
        redirectAttributes.addFlashAttribute("message", "Concert updated successfully");
        return "redirect:/concerts";
    }

    @GetMapping("/delete/{id}")
    public String deleteConcert(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean isDeleted = concertService.deleteConcert(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Concert deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error deleting concert");
        }
        return "redirect:/concerts";
    }
}
