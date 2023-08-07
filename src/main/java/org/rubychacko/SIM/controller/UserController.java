package org.rubychacko.SIM.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.rubychacko.SIM.security.UserDTO;
import org.rubychacko.SIM.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private UserServiceImpl userDetailsService;

    @Autowired
    public UserController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    private String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("userDto") UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Wrong attempt");
            return "signup";
        }
        userDetailsService.createUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        log.info("Login page displayed");
        return "login";
    }

    @RequestMapping("/home")
    public String getHome() {
        log.info("home page is displayed");
        return "redirect:/store_location";
    }
}
