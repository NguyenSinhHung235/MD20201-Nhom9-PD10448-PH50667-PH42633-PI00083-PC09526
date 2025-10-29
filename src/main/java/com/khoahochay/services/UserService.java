package com.khoahochay.services;

import com.khoahochay.dao.UserRepository;
import com.khoahochay.dto.UserDTO;
import com.khoahochay.dto.UserForm;
import com.khoahochay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO);
    }

    public UserDTO createUser(UserForm userForm) {
        if (userRepository.existsByEmail(userForm.getEmail())) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống");
        }

        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setRole(userForm.getRole());
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserForm userForm) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));

        // Check if email is being changed and if it's already taken
        if (!user.getEmail().equals(userForm.getEmail()) &&
                userRepository.existsByEmail(userForm.getEmail())) {
            throw new RuntimeException("Email đã tồn tại trong hệ thống");
        }

        user.setEmail(userForm.getEmail());
        user.setRole(userForm.getRole());
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());

        // Only update password if provided
        if (userForm.getPassword() != null && !userForm.getPassword().trim().isEmpty()) {
            user.setPassword(userForm.getPassword());
        }

        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy người dùng với ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDTO toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));

        user.setActive(!user.isActive());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    public long getStudentCount() {
        return userRepository.countStudents();
    }

    public long getTeacherCount() {
        return userRepository.countTeachers();
    }

    public long getTotalUsers() {
        return userRepository.count();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}