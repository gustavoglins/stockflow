package com.stockflow.controllers;

import com.stockflow.dto.companyDtos.CompanyRequestDTO;
import com.stockflow.dto.teamDtos.TeamRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {


// -------------------- Home Mapping --------------------
    @GetMapping
    public String getHomePage(){
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

    @GetMapping("/signup/company")
    public String signupCompanyPage(Model model) {
        model.addAttribute("companyRequestDTO", new CompanyRequestDTO());
        return "signup-company";
    }

    @GetMapping("/signup/team")
    public String signupTeamPage(Model model) {
        model.addAttribute("teamRequestDTO", new TeamRequestDTO());
        return "signup-team";
    }

    @GetMapping("/signup/personal")
    public String signupPersonalPage() {
        return "signup-personal";
    }



// -------------------- Login Mapping --------------------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }



// -------------------- Dashboard Mapping --------------------
    @GetMapping("/dashboard")
    public String dashboardPage(){
        return "dashboard";
    }
}