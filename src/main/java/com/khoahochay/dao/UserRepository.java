package com.khoahochay.dao;

import com.khoahochay.models.Role;
import com.khoahochay.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByActiveTrue();
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'STUDENT'")
    long countStudents();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 'TEACHER'")
    long countTeachers();
}