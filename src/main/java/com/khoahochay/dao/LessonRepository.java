package com.khoahochay.dao;

import com.khoahochay.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourseIdOrderByLessonOrderAsc(Long courseId);
    List<Lesson> findByCourseId(Long courseId);
    void deleteByCourseId(Long courseId);
}