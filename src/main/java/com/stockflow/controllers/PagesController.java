package com.stockflow.controllers;

import com.stockflow.dto.company.CompanyRequestDTO;
import com.stockflow.dto.team.TeamRequestDTO;
import com.stockflow.dto.user.UserSignupRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {


    // -------------------- Home Mapping --------------------
    @GetMapping
    public String getHomePage() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }


    // -------------------- Signup Mappings --------------------
    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/signup/personal")
    public String signupPersonalPage(Model model) {
        model.addAttribute("userSignupRequestDTO", new UserSignupRequestDTO());
        return "signup-personal";
    }

    @GetMapping("/signup/team")
    public String signupTeamPage(Model model) {
        model.addAttribute("teamRequestDTO", new TeamRequestDTO());
        return "signup-team";
    }

    @GetMapping("/signup/company")
    public String signupCompanyPage(Model model) {
        model.addAttribute("companyRequestDTO", new CompanyRequestDTO());
        return "signup-company";
    }


    // -------------------- Login Mapping --------------------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    // -------------------- Menu Mappings --------------------
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/inventory")
    public String inventoryPage() {
        return "inventory";
    }

    @GetMapping("/help-and-support")
    public String helpAndSupportPage() {
        return "help-and-support";
    }

    @GetMapping("/settings")
    public String settingsPage() {
        return "settings";
    }
}