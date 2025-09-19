package com.khoahochay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @GetMapping
    public String courseManagement(Model model) {
        // Add mock data for courses
        return "courses";
    }
}