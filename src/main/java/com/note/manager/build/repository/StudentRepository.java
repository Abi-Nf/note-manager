package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository {
    void saveStudent(Student student);
    void updateStudent(Long id, Student student);
    void delete();
    Student findById(Long id);
    Student findByRef(String reference);
    List<Student> findAll();
    List<Student> findByNote(Double note);
    List<Student> findBetweenNote(Double start,Double end);
    List<Student> findByFirstnameOrLastname(String query);
}
