package com.stockflow.exceptions.handler;

import com.stockflow.exceptions.EntityValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    public ModelAndView handleEntityValidationException(EntityValidationException ex) {
        ModelAndView modelAndView = new ModelAndView("signup-company");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}
