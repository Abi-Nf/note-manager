package com.note.manager.build.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Student implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String ref;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private LocalDateTime creationDate;
    private Long classId;
    private Long GroupId;
}
