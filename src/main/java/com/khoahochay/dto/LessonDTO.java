package com.khoahochay.dto;

public class LessonDTO {
    private Long id;
    private String title;
    private String content;
    private String videoUrl;
    private String documentUrl;
    private int lessonOrder;
    private int duration;
    private Long courseId;
    private String courseTitle;

    // Constructors
    public LessonDTO() {}

    public LessonDTO(Long id, String title, String content, int lessonOrder) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.lessonOrder = lessonOrder;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }

    public int getLessonOrder() { return lessonOrder; }
    public void setLessonOrder(int lessonOrder) { this.lessonOrder = lessonOrder; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }
}