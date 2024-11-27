package com.stockflow.controllers;

import com.stockflow.model.company.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/signup/company")
    public String signupCompanyPage(Model model) {
        model.addAttribute("company", new Company());
        return "signup-company";
    }

    @GetMapping("/signup/team")
    public String signupTeamPage() {
        return "signup-team";
    }

    @GetMapping("/signup/personal")
    public String signupPersonalPage() {
        return "signup-personal";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}