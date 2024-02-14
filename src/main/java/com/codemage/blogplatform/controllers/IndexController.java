package com.codemage.blogplatform.controllers;

import com.codemage.blogplatform.constants.Pages;
import com.codemage.blogplatform.constants.Route;
import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @GetMapping(Route.DASHBOARD)
    public ModelAndView home(@RequestParam(name = "searchQuery", required = false) String searchQuery) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Pages.DASHBOARD);
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Blog> blogs = blogService.searchBlog(searchQuery, username);
            modelAndView.addObject("blogs", blogs);
            modelAndView.addObject("successMessage", "Blog saved successfully");
        } catch (Exception e) {
            modelAndView.setViewName(Pages.ERROR_NOT_FOUND);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
