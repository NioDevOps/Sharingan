package com.nio.swc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class WebController {

    /**
     * 初始HOME PAGE
     */
    @RequestMapping("/")
    public void home(HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }
}
