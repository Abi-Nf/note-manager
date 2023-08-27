package com.note.manager.build.repository;

import com.note.manager.build.model.Subject;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SubjectRepository {
    Connection connection;

    public SubjectRepository(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = """
        select * from subject;
        """;
        return this.connection.prepareStatement(sql);
    }

    public PreparedStatement findByName(String name) throws SQLException {
        String sql = """
        select * from subject
        where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');
        return statement;
    }

    public PreparedStatement saveSubject(Subject subject) throws SQLException {
        String sql = """
        insert into subject
        (name, description)
        VALUES (?,?)
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,subject.getName());
        statement.setString(2,subject.getDescription());

        return statement;
    }

    public PreparedStatement updateByName(
            String name,
            Subject subject
    ) throws SQLException {
        String sql = """
        update subject
        set name = ?,
        description = ?
        where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,subject.getName());
        statement.setString(2,subject.getDescription());
        statement.setString(3,'%'+name+'%');

        return statement;
    }

    public PreparedStatement deleteByName(String name) throws SQLException {
        String sql = """
        delete from subject where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');

        return statement;
    }

}
