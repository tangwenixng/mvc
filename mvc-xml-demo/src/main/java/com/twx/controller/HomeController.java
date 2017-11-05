package com.twx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by twx on 2017/11/2.
 */
@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("serverTime", "11-02");
        return "home";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        model.addAttribute("myList", list);
        return "list";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String login() {
        logger.info("Login....");
        return "login";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String getForm(Model model) {
        return "form";
    }
}
