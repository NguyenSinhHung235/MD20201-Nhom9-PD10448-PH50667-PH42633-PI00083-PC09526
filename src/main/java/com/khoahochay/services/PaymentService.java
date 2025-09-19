package com.khoahochay.services;

import com.khoahochay.dao.CourseRepository;
import com.khoahochay.dao.PaymentRepository;
import com.khoahochay.dao.UserRepository;
import com.khoahochay.dto.PaymentDTO;
import com.khoahochay.models.Course;
import com.khoahochay.models.Payment;
import com.khoahochay.models.PaymentMethod;
import com.khoahochay.models.PaymentStatus;
import com.khoahochay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(paymentDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setCourse(course);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setTransactionId(generateTransactionId());

        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    public double getTotalRevenue() {
        BigDecimal revenue = paymentRepository.getTotalRevenue();
        return revenue != null ? revenue.doubleValue() : 0.0;
    }

    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setUserId(payment.getUser().getId());
        dto.setUserName(payment.getUser().getFullName());
        dto.setCourseId(payment.getCourse().getId());
        dto.setCourseTitle(payment.getCourse().getTitle());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}