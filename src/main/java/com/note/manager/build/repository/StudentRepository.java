package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class StudentRepository {
    Connection connection;

    public StudentRepository(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement saveStudent(Student student) throws SQLException {
        String sql = """
        INSERT INTO student
        (firstname, lastname, ref, email, phone, birthdate, creation_date, classid, groupid)
        VALUES
        (?,?,?,?,?,?,?,?,?)
        """;
        return this.connection.prepareStatement(sql);
    }
    public PreparedStatement updateStudentById(long id, Student student) throws SQLException {
        String sql = """
        
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,student.getFirstName());
        statement.setString(2,student.getLastName());
        statement.setString(3,student.getRef());
        statement.setString(4,student.getEmail());
        statement.setString(5,student.getPhone());
        statement.setDate(6, Date.valueOf(student.getBirthdate()));
        statement.setTimestamp(7, Timestamp.valueOf(student.getCreationDate()));
        statement.setLong(8,student.getClassId());
        statement.setLong(9,student.getGroupId());
        return statement;
    }
//    void delete();
//    Student findById(Long id);
//    Student findByRef(String reference);
//    List<Student> findAll();
//    List<Student> findByNote(Double note);
//    List<Student> findBetweenNote(Double start,Double end);
//    List<Student> findByFirstnameOrLastname(String query);
}
