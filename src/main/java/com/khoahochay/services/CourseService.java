package com.khoahochay.services;

import com.khoahochay.dao.UserRepository;
import com.khoahochay.dto.CourseDTO;
import com.khoahochay.models.Course;
import com.khoahochay.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private com.khoahochay.dao.CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        User teacher = userRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setTeacher(teacher);
        course.setCategory(courseDTO.getCategory());
        course.setDuration(courseDTO.getDuration());

        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public long getTotalCourses() {
        return courseRepository.countTotalCourses();
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setTeacherId(course.getTeacher().getId());
        dto.setTeacherName(course.getTeacher().getFullName());
        dto.setCategory(course.getCategory());
        dto.setDuration(course.getDuration());
        dto.setStudentCount(course.getStudentCount());
        dto.setRating(course.getRating());
        dto.setPublished(course.isPublished());
        dto.setCreatedAt(course.getCreatedAt());
        return dto;
    }
}