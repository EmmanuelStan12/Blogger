package com.codemage.blogplatform.controllers;

import com.codemage.blogplatform.constants.Pages;
import com.codemage.blogplatform.constants.Route;
import com.codemage.blogplatform.dto.UserDTO;
import com.codemage.blogplatform.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(Route.REGISTER)
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return Pages.REGISTER;
    }

    @GetMapping(Route.LOGIN)
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return Pages.LOGIN;
    }

    @PostMapping(Route.REGISTER)
    public ModelAndView register(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userDTO);
        modelAndView.setViewName(Pages.REGISTER);
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            bindingResult.addError(new FieldError("user", "matchingPassword", "Passwords don't match"));
            return modelAndView;
        }
        try {
            userService.register(userDTO);
            modelAndView.setView(new RedirectView(Route.LOGIN));
        } catch (Exception e) {
            modelAndView.setViewName(Pages.REGISTER);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

}
