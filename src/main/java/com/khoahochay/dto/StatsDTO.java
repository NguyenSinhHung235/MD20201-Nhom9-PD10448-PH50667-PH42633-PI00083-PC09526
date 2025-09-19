package com.khoahochay.dto;

import java.math.BigDecimal;

public class StatsDTO {
    private long totalUsers;
    private long totalStudents;
    private long totalTeachers;
    private long totalCourses;
    private BigDecimal totalRevenue;
    private long totalEnrollments;

    // Constructors
    public StatsDTO() {}

    public StatsDTO(long totalUsers, long totalStudents, long totalCourses, BigDecimal totalRevenue) {
        this.totalUsers = totalUsers;
        this.totalStudents = totalStudents;
        this.totalCourses = totalCourses;
        this.totalRevenue = totalRevenue;
    }

    // Getters and Setters
    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }

    public long getTotalStudents() { return totalStudents; }
    public void setTotalStudents(long totalStudents) { this.totalStudents = totalStudents; }

    public long getTotalTeachers() { return totalTeachers; }
    public void setTotalTeachers(long totalTeachers) { this.totalTeachers = totalTeachers; }

    public long getTotalCourses() { return totalCourses; }
    public void setTotalCourses(long totalCourses) { this.totalCourses = totalCourses; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }

    public long getTotalEnrollments() { return totalEnrollments; }
    public void setTotalEnrollments(long totalEnrollments) { this.totalEnrollments = totalEnrollments; }
}