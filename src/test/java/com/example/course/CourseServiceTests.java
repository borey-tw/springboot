package com.example.course;

import com.example.course.exception.CourseDuplicationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceTests {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void findCourseById() {
        long id = 1;
        Course expectCourse = new Course("Course Test Service", "This course for testing service");
        Mockito.when(courseRepository.findById(id)).thenReturn(Optional.of(expectCourse));

        Course responseCourse = courseService.findById(id);

        assertEquals(responseCourse.getId(), expectCourse.getId());
        assertEquals(responseCourse.getTitle(), expectCourse.getTitle());
        assertEquals(responseCourse.getDescription(), expectCourse.getDescription());
    }

    @Test
    public void findAllCourse() {
        List<Course> courses = new ArrayList<>();
        Collections.addAll(
                courses,
                new Course("Course Test Service", "This course for testing service"),
                new Course("Course Test Service","This course for testing service")
        );
        Mockito.when(courseRepository.findAll()).thenReturn(courses);

        List<Course> responseCourses = courseService.findAll();

        assertEquals(responseCourses.size(), courses.size());
    }

    @Test
    public void cannotCreateDuplicateCourseTitle() {
        Course course = new Course("Course Test Service", "This course for testing service");
        List<Course> courses = new ArrayList<>();
        Collections.addAll(courses, course);
        Mockito.when(courseRepository.findByTitle(course.getTitle())).thenReturn(courses);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Course response = courseService.create(course);
        });

        assertTrue(exception instanceof CourseDuplicationException);
        assertEquals(exception.getMessage(), "Course Test Service is already exist.");
    }
}
