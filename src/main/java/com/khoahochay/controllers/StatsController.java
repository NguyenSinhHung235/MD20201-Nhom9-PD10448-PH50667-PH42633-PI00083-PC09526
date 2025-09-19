package com.khoahochay.controllers;

import com.khoahochay.dto.StatsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/stats")
public class StatsController {

    @GetMapping
    public String statsDashboard(Model model) {
        // Mock stats for testing
        StatsDTO stats = new StatsDTO();
        stats.setTotalUsers(1245);
        stats.setTotalStudents(987);
        stats.setTotalTeachers(45);
        stats.setTotalCourses(28);
        stats.setTotalRevenue(new BigDecimal("156800000"));
        stats.setTotalEnrollments(2345);

        model.addAttribute("stats", stats);
        return "stats";
    }
}