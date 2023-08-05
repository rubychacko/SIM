package org.rubychacko.SIM.controller;

import lombok.extern.slf4j.Slf4j;
import org.rubychacko.SIM.model.StoreLocation;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

@Slf4j
@Controller
public class UserController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    private String redirectToLogin(Model model) {
        model.addAttribute("storeLocation", new StoreLocation());
        return "redirect:/store_location";
    }
}
