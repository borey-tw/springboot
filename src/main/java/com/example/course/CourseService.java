package com.example.course;

import com.example.course.exception.CourseDuplicationException;
import com.example.course.exception.CourseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    Course findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    List<Course> findAll() {
        return this.repository.findAll();
    }

    Course create(Course newCourse) {
        List<Course> courses = this.repository.findByTitle(newCourse.getTitle());

        if (courses.size() > 0) throw new CourseDuplicationException(newCourse);

        return this.repository.save(newCourse);
    }
}
