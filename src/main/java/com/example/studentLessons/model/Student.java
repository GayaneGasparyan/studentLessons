package com.example.studentLessons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
}
