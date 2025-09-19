package com.khoahochay.services;

import com.khoahochay.dao.UserRepository;
import com.khoahochay.dto.UserDTO;
import com.khoahochay.dto.UserForm;
import com.khoahochay.models.Role;
import com.khoahochay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> getAllUsers() {
        // Tạm thời trả về mock data để test
        return getMockUsers();

        // Khi database hoạt động, dùng code này:
        // return userRepository.findAll().stream()
        //         .map(this::convertToDTO)
        //         .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        // Tạm thời tìm trong mock data
        return getMockUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        // Khi database hoạt động, dùng code này:
        // return userRepository.findById(id)
        //         .map(this::convertToDTO);
    }

    public UserDTO createUser(UserForm userForm) {
        // Tạm thời trả về mock user
        UserDTO mockUser = new UserDTO();
        mockUser.setId((long) (getMockUsers().size() + 1));
        mockUser.setEmail(userForm.getEmail());
        mockUser.setRole(userForm.getRole());
        mockUser.setFullName(userForm.getFullName());
        mockUser.setPhone(userForm.getPhone());
        mockUser.setActive(true);
        return mockUser;

        // Khi database hoạt động, dùng code này:
        // if (userRepository.existsByEmail(userForm.getEmail())) {
        //     throw new RuntimeException("Email đã tồn tại trong hệ thống");
        // }
        //
        // User user = new User();
        // user.setEmail(userForm.getEmail());
        // user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        // user.setRole(userForm.getRole());
        // user.setFullName(userForm.getFullName());
        // user.setPhone(userForm.getPhone());
        // user.setActive(true);
        //
        // User savedUser = userRepository.save(user);
        // return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserForm userForm) {
        // Tạm thời trả về mock user
        UserDTO mockUser = new UserDTO();
        mockUser.setId(id);
        mockUser.setEmail(userForm.getEmail());
        mockUser.setRole(userForm.getRole());
        mockUser.setFullName(userForm.getFullName());
        mockUser.setPhone(userForm.getPhone());
        mockUser.setActive(true);
        return mockUser;

        // Khi database hoạt động, dùng code này:
        // User user = userRepository.findById(id)
        //         .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
        //
        // if (!user.getEmail().equals(userForm.getEmail()) &&
        //     userRepository.existsByEmail(userForm.getEmail())) {
        //     throw new RuntimeException("Email đã tồn tại trong hệ thống");
        // }
        //
        // user.setEmail(userForm.getEmail());
        // user.setRole(userForm.getRole());
        // user.setFullName(userForm.getFullName());
        // user.setPhone(userForm.getPhone());
        //
        // if (userForm.getPassword() != null && !userForm.getPassword().trim().isEmpty()) {
        //     user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        // }
        //
        // User updatedUser = userRepository.save(user);
        // return convertToDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        // Tạm thời không làm gì với mock data
        System.out.println("Xóa user với ID: " + id);

        // Khi database hoạt động, dùng code này:
        // if (!userRepository.existsById(id)) {
        //     throw new RuntimeException("Không tìm thấy người dùng với ID: " + id);
        // }
        // userRepository.deleteById(id);
    }

    public UserDTO toggleUserStatus(Long id) {
        // Tạm thời trả về mock user
        Optional<UserDTO> userOpt = getMockUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        if (userOpt.isPresent()) {
            UserDTO user = userOpt.get();
            user.setActive(!user.isActive());
            return user;
        }
        throw new RuntimeException("Không tìm thấy người dùng với ID: " + id);

        // Khi database hoạt động, dùng code này:
        // User user = userRepository.findById(id)
        //         .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + id));
        //
        // user.setActive(!user.isActive());
        // User updatedUser = userRepository.save(user);
        // return convertToDTO(updatedUser);
    }

    // Mock data cho testing
    private List<UserDTO> getMockUsers() {
        UserDTO user1 = new UserDTO();
        user1.setId(1L);
        user1.setEmail("student@example.com");
        user1.setRole(Role.STUDENT);
        user1.setFullName("Nguyễn Văn A");
        user1.setPhone("0123456789");
        user1.setActive(true);

        UserDTO user2 = new UserDTO();
        user2.setId(2L);
        user2.setEmail("teacher@example.com");
        user2.setRole(Role.TEACHER);
        user2.setFullName("Trần Thị B");
        user2.setPhone("0987654321");
        user2.setActive(true);

        UserDTO user3 = new UserDTO();
        user3.setId(3L);
        user3.setEmail("admin@example.com");
        user3.setRole(Role.ADMIN);
        user3.setFullName("Lê Văn C");
        user3.setPhone("0369852147");
        user3.setActive(false);

        return Arrays.asList(user1, user2, user3);
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