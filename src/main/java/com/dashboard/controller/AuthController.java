package com.dashboard.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/login", method = { RequestMethod.GET })
    public String loginPage() {
        if (isAuthenticated()) {
            return "redirect:/list_users";
        }
        return "/login";
    }

    @GetMapping("/")
    public String index() {
        if (isAuthenticated()) {
            return "redirect:/list_users";
        }
        return "redirect:/login";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
