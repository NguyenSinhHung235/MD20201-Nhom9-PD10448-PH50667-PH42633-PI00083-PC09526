package com.khoahochay.dao;

import com.khoahochay.models.Course;
import com.khoahochay.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
    List<Course> findByPublishedTrue();
    List<Course> findByCategory(String category);
    List<Course> findByTeacherId(Long teacherId);

    @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% OR c.description LIKE %:keyword%")
    List<Course> searchByKeyword(String keyword);

    @Query("SELECT COUNT(c) FROM Course c")
    long countTotalCourses();
}