package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class StudentRepository {
    Connection connection;

    public StudentRepository(Connection connection){
        this.connection = connection;
    }

    private PreparedStatement prepareStatement(Student student, String sql) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,student.getFirstName());
        statement.setString(2,student.getLastName());
        statement.setString(3,student.getRef());
        statement.setString(4,student.getEmail());
        statement.setString(5,student.getPhone());
        statement.setDate(6, Date.valueOf(student.getBirthdate()));
        return statement;
    }

    public PreparedStatement saveStudent(Student student) throws SQLException {
        String sql = """
        INSERT INTO student
        (firstname, lastname, ref, email, phone, birthdate, creation_date, classid, groupid)
        VALUES
        (?,?,?,?,?,?,?,?,?)
        """;
        PreparedStatement prepared = prepareStatement(student, sql);
        prepared.setTimestamp(7,Timestamp.valueOf(student.getCreationDate()));
        prepared.setLong(8,student.getClassId());
        prepared.setLong(9,student.getGroupId());

        return prepared;
    }

    public PreparedStatement updateStudentById(long id, Student student) throws SQLException {
        String sql = """
        update student
            set firstname = ?,
            lastname = ?,
            ref = ?,
            email = ?,
            phone = ?,
            birthdate = ?,
            classid = ?,
            groupid = ?
        where id = ?
        """;
        PreparedStatement statement = prepareStatement(student, sql);
        statement.setLong(7,student.getClassId());
        statement.setLong(8,student.getGroupId());
        statement.setLong(9,id);

        return statement;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = """
        select *, c.id as class_id, g.id as group_id from student
        inner join "group" g on g.id = student.groupid
        inner join class c on c.id = student.classid
        """;
        return this.connection.prepareStatement(sql);
    }

    public PreparedStatement findByRef(String ref) throws SQLException {
        String sql = """
        select *, c.id as class_id, g.id as group_id from student
        inner join "group" g on g.id = student.groupid
        inner join class c on c.id = student.classid
        where student.ref = ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,ref);

        return statement;
    }

    public PreparedStatement deleteByRef(String ref) throws SQLException {
        String sql = """
        delete from student where student.ref = ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,ref);

        return statement;
    }

}
