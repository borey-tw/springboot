package com.example.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitle(String title);
}
