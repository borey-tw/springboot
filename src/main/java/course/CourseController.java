package course;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseRepository repository;

    CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courses")
    List<Course> all() {
        return repository.findAll();
    }

    @GetMapping("/courses/{id}")
    Optional<Course> getById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/courses")
    Course create(@RequestBody Course course) {
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
