package com.stockflow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String registerPage() {
        return "signup";
    }

    @GetMapping("/signup/company")
    public String registerCompanyPage(){
        return "signup-company";
    }

    @GetMapping("/signup/team")
    public String registerTeamPage(){
        return "signup-team";
    }

    @GetMapping("/signup/personal")
    public String registerPersonalPage(){
        return "signup-personal";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}