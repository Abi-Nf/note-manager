package com.note.manager.build.services;

import com.note.manager.build.model.Student;
import com.note.manager.build.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


@Service
public class StudentService {
    StudentRepository repository;

    public StudentService(Connection connection){
        this.repository = new StudentRepository(connection);
    }

    public Optional<Student> saveStudent(Student student){
        try (PreparedStatement statement = repository.saveStudent(student)) {
            int countSaved =  statement.executeUpdate();
            if(countSaved > 0){
                return Optional.of(student);
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return Optional.empty();
    }

}