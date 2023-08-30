package com.note.manager.build.services;

import com.note.manager.build.Utils.StudentParser;
import com.note.manager.build.model.Student;
import com.note.manager.build.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    StudentRepository repository;

    public StudentService(Connection connection){
        this.repository = new StudentRepository(connection);
    }

    public List<Student> findAll(){
        List<Student> studentList = new ArrayList<>();
        try(PreparedStatement statement = repository.findAll()) {
            ResultSet result = statement.executeQuery();
            while (result.next()){
                studentList.add(
                        StudentParser.parseStudentResultSet(result)
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    public Optional<Student> findByRef(String ref){
        try(PreparedStatement statement = repository.findByRef(ref)) {
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return Optional.of(
                        StudentParser.parseStudentResultSet(result)
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
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

    public Optional<Student> updateByRef(String ref, Student student){
        try(PreparedStatement statement = repository.updateStudentByRef(ref,student)) {
            int rowUpdated = statement.executeUpdate();
            if(rowUpdated > 0) return Optional.of(student);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Student> deleteByRef(String ref){
        try{
            PreparedStatement statementGet = repository.findByRef(ref);
            ResultSet result = statementGet.executeQuery();
            PreparedStatement statement = repository.deleteByRef(ref);
            int rowDeleted = statement.executeUpdate();
            if(rowDeleted > 0) return Optional.of(
                    StudentParser.parseStudentResultSet(result)
            );
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}