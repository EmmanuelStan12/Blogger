package com.codemage.blogplatform.controllers;

import com.codemage.blogplatform.constants.Pages;
import com.codemage.blogplatform.constants.Route;
import com.codemage.blogplatform.dto.BlogDTO;
import com.codemage.blogplatform.dto.UserDTO;
import com.codemage.blogplatform.models.Blog;
import com.codemage.blogplatform.models.User;
import com.codemage.blogplatform.service.BlogService;
import com.codemage.blogplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping(Route.CREATE_BLOG)
    public String createBlog(Model model) {
        BlogDTO blogDTO = new BlogDTO();
        model.addAttribute("blog", blogDTO);
        return Pages.ADD_BLOG;
    }

    @PostMapping(Route.CREATE_BLOG)
    public ModelAndView createBlog(
            @ModelAttribute("blog") @Valid BlogDTO blogDTO,
            BindingResult bindingResult
    ) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blog", blogDTO);
        modelAndView.setViewName(Pages.ADD_BLOG);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        try {
            blogService.createBlog(blogDTO, username);
            modelAndView.addObject("successMessage", "Blog saved successfully");
            modelAndView.setView(new RedirectView(Route.DASHBOARD));
        } catch (Exception e) {
            modelAndView.setViewName(Pages.ADD_BLOG);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping(Route.GET_BLOG)
    public ModelAndView getBlog(@RequestParam(name = "blogId", required = false) Long blogId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        try {
            Blog blog = blogService.findBlogById(blogId, username).orElse(null);
            modelAndView.addObject("blog", blog);
            modelAndView.setViewName(Pages.GET_BLOG);
        } catch (Exception e) {
            modelAndView.setViewName(Pages.ADD_BLOG);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
}
