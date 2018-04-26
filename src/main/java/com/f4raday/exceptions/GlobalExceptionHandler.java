package com.f4raday.exceptions;

import com.f4raday.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(Exception ex) {
        logger.debug("Problem with database: " + ex.getStackTrace());

        ModelAndView model = new ModelAndView("error");
        model.addObject("error", "Problem with database. Contact support");
        return model;
    }

}
