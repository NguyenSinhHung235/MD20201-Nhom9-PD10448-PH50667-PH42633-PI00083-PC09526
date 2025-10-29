package com.khoahochay;

import com.khoahochay.dao.UserRepository;
import com.khoahochay.models.Role;
import com.khoahochay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra nếu chưa có user nào thì tạo dữ liệu mẫu
        if (userRepository.count() == 0) {
            // Tạo admin user
            User admin = new User();
            admin.setEmail("admin@khoahochay.com");
            admin.setPassword("admin123");
            admin.setRole(Role.ADMIN);
            admin.setFullName("Quản trị viên");
            admin.setPhone("0123456789");
            admin.setActive(true);
            userRepository.save(admin);

            // Tạo teacher user
            User teacher = new User();
            teacher.setEmail("teacher@khoahochay.com");
            teacher.setPassword("teacher123");
            teacher.setRole(Role.TEACHER);
            teacher.setFullName("Giảng viên mẫu");
            teacher.setPhone("0987654321");
            teacher.setActive(true);
            userRepository.save(teacher);

            // Tạo student user
            User student = new User();
            student.setEmail("student@khoahochay.com");
            student.setPassword("student123");
            student.setRole(Role.STUDENT);
            student.setFullName("Học viên mẫu");
            student.setPhone("0369852147");
            student.setActive(true);
            userRepository.save(student);

            System.out.println("Đã tạo dữ liệu mẫu thành công!");
        }
    }
}