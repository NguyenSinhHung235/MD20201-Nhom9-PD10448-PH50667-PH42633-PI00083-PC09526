package com.khoahochay.services;

import com.khoahochay.dao.CourseRepository;
import com.khoahochay.dao.LessonRepository;
import com.khoahochay.dto.LessonDTO;
import com.khoahochay.models.Course;
import com.khoahochay.models.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<LessonDTO> getLessonsByCourse(Long courseId) {
        return lessonRepository.findByCourseIdOrderByLessonOrderAsc(courseId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LessonDTO createLesson(LessonDTO lessonDTO) {
        Course course = courseRepository.findById(lessonDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setContent(lessonDTO.getContent());
        lesson.setVideoUrl(lessonDTO.getVideoUrl());
        lesson.setDocumentUrl(lessonDTO.getDocumentUrl());
        lesson.setLessonOrder(lessonDTO.getLessonOrder());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setCourse(course);

        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDTO(savedLesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    private LessonDTO convertToDTO(Lesson lesson) {
        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setContent(lesson.getContent());
        dto.setVideoUrl(lesson.getVideoUrl());
        dto.setDocumentUrl(lesson.getDocumentUrl());
        dto.setLessonOrder(lesson.getLessonOrder());
        dto.setDuration(lesson.getDuration());
        dto.setCourseId(lesson.getCourse().getId());
        dto.setCourseTitle(lesson.getCourse().getTitle());
        return dto;
    }
}