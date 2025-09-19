package com.khoahochay.services;

import com.khoahochay.dao.CourseRepository;
import com.khoahochay.dao.EnrollmentRepository;
import com.khoahochay.dao.PaymentRepository;
import com.khoahochay.dao.UserRepository;
import com.khoahochay.dto.StatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class StatsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public StatsDTO getSystemStats() {
        StatsDTO stats = new StatsDTO();

        stats.setTotalUsers(userRepository.count());
        stats.setTotalStudents(userRepository.countStudents());
        stats.setTotalTeachers(userRepository.countTeachers());
        stats.setTotalCourses(courseRepository.countTotalCourses());
        stats.setTotalEnrollments(enrollmentRepository.countTotalEnrollments());

        BigDecimal revenue = paymentRepository.getTotalRevenue();
        stats.setTotalRevenue(revenue != null ? revenue : BigDecimal.ZERO);

        return stats;
    }
}