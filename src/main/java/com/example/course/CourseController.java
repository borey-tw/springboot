package com.example.course;

import com.example.course.exception.CourseDuplicationException;
import com.example.course.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseRepository repository;
    private final CourseService service;

    CourseController(CourseRepository repository, CourseService service) {
        this.repository = repository; this.service = service;
    }

    @GetMapping("/courses")
    List<Course> all() {
        return service.findAll();
    }

    @GetMapping("/courses/{id}")
    Course getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    Course create(@Valid @RequestBody Course course) {

        List<Course> courses = repository.findByTitle(course.getTitle());

        if (courses.size() > 0) throw new CourseDuplicationException(course);

        return this.repository.save(course);
    }

    @PutMapping("/courses/{id}")
    Course edit(@RequestBody Course newCourse, @PathVariable Long id) {
        return this.repository.findById(id).map(course -> {
            course.setTitle(newCourse.getTitle());
            course.setDescription(newCourse.getDescription());
            return this.repository.save(course);
        }).orElseGet(() -> {
            newCourse.setId(id);
            System.out.println(newCourse.toString());
            return this.repository.save(newCourse);
        });
    }

    @DeleteMapping("/courses/{id}")
    void delete(@PathVariable Long id) {
        this.repository.deleteById(id);
    }
}
