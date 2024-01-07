package com.codemage.blogplatform.controllers;

import com.codemage.blogplatform.constants.Pages;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return Pages.ERROR_NOT_FOUND;
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return Pages.ERROR_NOT_FOUND;
            }
        }
        return Pages.ERROR_NOT_FOUND;
    }
}
