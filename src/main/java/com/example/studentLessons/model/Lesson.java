package com.example.studentLessons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Lesson {

    private int id;
private String name;
private String description;
private Student student;


}
