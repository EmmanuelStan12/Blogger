package com.codemage.blogplatform.controllers;

import com.codemage.blogplatform.constants.Pages;
import com.codemage.blogplatform.constants.Route;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(Route.DASHBOARD)
    public String home() {
        return Pages.DASHBOARD;
    }
}
