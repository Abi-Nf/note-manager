package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import com.note.manager.build.services.StudentService;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findById(Long id);
    void save(Student student);
    void update(Student student);

    void delete(Long id);
}
