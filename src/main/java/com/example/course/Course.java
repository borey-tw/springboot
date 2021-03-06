package com.example.course;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Course {

    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) Long id;

    @NotEmpty
    @Size(min = 2, max = 125)
    private String title;

    @NotEmpty
    @Size(min = 2, max = 500)
    private String description;

    Course() {}

    Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString () {
        return "Course - id: "+this.id+" title: "+this.title+" description: "+this.description;
    }
}
