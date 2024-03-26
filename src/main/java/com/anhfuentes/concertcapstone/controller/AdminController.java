package com.anhfuentes.concertcapstone.controller;

import com.anhfuentes.concertcapstone.dto.UserDTO;
import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.service.ConcertService;
import com.anhfuentes.concertcapstone.service.RoleService;
import com.anhfuentes.concertcapstone.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final ConcertService concertService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(UserService userService, RoleService roleService, ConcertService concertService) {

        this.userService = userService;
        this.roleService = roleService;
        this.concertService = concertService;
    }

    @GetMapping("/admin/all")
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_only/users";
    }

    @GetMapping("/admin/delete-user-and-roles/{id}")
    public String deleteUserAndRoles(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean success = userService.deleteUserAndRolesById(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not delete user and roles. User might not exist, or there are constraints preventing deletion.");
        }
        return "redirect:/admin/all";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "admin_only/edit_user";
        }
        return "redirect:/admin/user/all";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") UserDTO userDTO) {
        logger.info("Updating user with ID: {}, firstName: {}, lastName: {}, email: {}", id, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
        userService.updateUser(userDTO);
        return "redirect:/admin/all";
    }


    @GetMapping("/admin/concerts")
    public String getAllConcerts(Model model) {
        List<Concert> concerts = concertService.getAllConcerts();
        model.addAttribute("concerts", concerts);
        return "admin_only/concert_list";
    }

    @GetMapping("/admin/add_concert")
    public String createConcertForm(Model model) {
        System.out.println("IN  ConcertController->createConcertForm()");
        Concert concert = new Concert();
        model.addAttribute("concert", concert);
        return "/admin_only/concert_add";
    }

    @PostMapping("/admin/save_concert")
    public String saveConcert(@ModelAttribute("concert") Concert concert) {
        System.out.println("IN  ConcertController->saveConcert()");
        concertService.createConcert(concert);
        return "redirect:/admin/concerts";
    }

    @GetMapping("/admin/delete-concert/{id}")
    public String deleteConcert(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean success = concertService.deleteConcert(id);
        if (!success) {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not delete concert. Concert might not exist, or there are constraints preventing deletion.");
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Concert and associated seats deleted successfully.");
        }
        return "redirect:/admin/concerts";
    }


    @GetMapping("/admin/edit-concert/{id}")
    public String editConcertForm(@PathVariable Long id, Model model) {
        model.addAttribute("concert", concertService.getConcertById(id));
        return "admin_only/concert_edit";
    }

    @PostMapping("/admin/update-concert/{id}")
    public String updateConcert(@PathVariable("id") Long id, @ModelAttribute("concert") Concert concert, Model model) {
        Concert existingConcert = concertService.getConcertById(id);
        existingConcert.setArtist(concert.getArtist());
        existingConcert.setDateTime(concert.getDateTime());
        existingConcert.setName(concert.getName());
        existingConcert.setPosterImage(concert.getPosterImage());

        concertService.updateConcert(existingConcert);
        return "redirect:/admin/concerts";
    }



}