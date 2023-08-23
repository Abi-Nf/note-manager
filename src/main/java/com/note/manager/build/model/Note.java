package com.note.manager.build.model;

import lombok.*;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    private int id;
    private double value;
    private long subjectId;
    private long studentId;
    private LocalDateTime evaluationDate;
    private double hasBonus;

    private Subject subject;
    private Student student;

    @ConstructorProperties({"id","value","subject","student","evaluationDate","hasBonus"})
    public Note(int id, double value, Subject subject, Student student, LocalDateTime evaluationDate, double hasBonus) {
        this.id = id;
        this.value = value;
        this.subject = subject;
        this.student = student;
        this.evaluationDate = evaluationDate;
        this.hasBonus = hasBonus;
    }
}
