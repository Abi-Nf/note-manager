package com.note.manager.build.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Student{
    private long id;
    private String firstName;
    private String lastName;
    private String ref;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private LocalDateTime creationDate;
    private long classId;
    private long GroupId;
}
