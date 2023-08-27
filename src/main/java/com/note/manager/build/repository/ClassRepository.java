package com.note.manager.build.repository;

import com.note.manager.build.model.Class;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ClassRepository {
    Connection connection;

    public ClassRepository(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = "select * from class";
        return this.connection.prepareStatement(sql);
    }

    public PreparedStatement findByName(String name) throws SQLException {
        String sql = """
        select * from class where name ilike ?;
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');

        return statement;
    }

    public PreparedStatement saveClass(Class aClass) throws SQLException {
        String sql = """
        insert into class
        (name)
        values (?)
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,aClass.getName());
        return statement;
    }

    public PreparedStatement updateClassNameByOldName(
            String oldName,
            String newName
    ) throws SQLException {
        String sql = """
        update class set name = ? where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,newName);
        statement.setString(2,'%'+oldName+'%');
        
        return statement;
    }

    public PreparedStatement deleteByName(String name) throws SQLException {
        String sql = """
        delete from class where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');

        return statement;
    }
}