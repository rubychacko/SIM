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

import static org.rubychacko.SIM.util.SIMConstants.*;

@Slf4j
@Controller
public class UserController {
    /**
     * Works with @RequestMapping and initializes a WebData Binder
     * used for populating from objects
     * also binds data from web request to Java Bean objects
     */

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

    // Redirect to login page
    @GetMapping("/")
    private String redirectToLogin() {
        return HTML_LOGIN_REDIRECT;
    }

    //HTML view of signup page
    @GetMapping(SIGNUP_PATH)
    public String signUp(Model model) {
        model.addAttribute("userDto", new UserDTO());
        return HTML_SIGNUP_VIEW;
    }

    //warning given for wrong credentials
    @PostMapping(SIGNUP_PROCESS_PATH)
    public String signupProcess(@Valid @ModelAttribute("userDto") UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Wrong attempt");
            return HTML_SIGNUP_VIEW;
        }
        userDetailsService.createUser(userDTO);
        return HTML_LOGIN_REDIRECT;
    }

    // HTML page view of the login page
    @GetMapping(LOGIN_PATH)
    public String getLoginPage() {
        log.info("Login page displayed");
        return HTML_LOGIN_VIEW;
    }

    // Redirected to the home page storeLocation page
    @RequestMapping(HOME_PATH)
    public String getHome() {
        log.info("home page is displayed");
        return HTML_STORE_REDIRECT;
    }

    // Display report issue page
    @RequestMapping("/report_issue")
    public String getReportIssue() {
        log.info("Report issue page is displayed");
        return "issue";
    }
}
