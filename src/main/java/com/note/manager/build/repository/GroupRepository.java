package com.note.manager.build.repository;

import com.note.manager.build.model.Group;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class GroupRepository {
    Connection connection;

    public GroupRepository(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = """
        select  * from "group";
        """;
        return this.connection.prepareStatement(sql);
    }

    public PreparedStatement findByName(String name) throws SQLException {
        String sql = """
        select * from "group" where name ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');

        return statement;
    }

    public PreparedStatement saveGroup(Group group) throws SQLException {
        String sql = """
        insert into "group"
        (name)
        values (?)
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,group.getName());

        return statement;
    }

    public PreparedStatement updateGroupByName(
            String oldName,
            String newName
    ) throws SQLException {
        String sql = """
        update "group"
        set name = ?
        where name  ilike ?
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,newName);
        statement.setString(2,'%'+oldName+'%');
        return statement;
    }

    public PreparedStatement deleteGroupByName(String name) throws SQLException {
        String sql = """
        delete from "group" where name ilike ?;
        """;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1,'%'+name+'%');

        return statement;
    }
}
