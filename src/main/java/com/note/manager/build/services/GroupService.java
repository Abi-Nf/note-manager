package com.note.manager.build.services;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Group;
import com.note.manager.build.repository.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        try(PreparedStatement statement = groupRepository.findAll()) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setName(resultSet.getString("name"));
                group.setId(resultSet.getLong("id"));

                groups.add(group);
            }
        }catch (SQLException error){
            System.err.println(error.getMessage());
        }
        return groups;
    }

    public Optional<Group> findByName(String name) {
        try(PreparedStatement statement = groupRepository.findByName(name)){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getLong("id"));
                group.setName(resultSet.getString("name"));
                return Optional.of(group);
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return Optional.empty();
    }

    public Object saveGroup(Group group) {
        try(PreparedStatement statement = groupRepository.saveGroup(group)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return group;
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Error on insert group",
                group
        );
    }

    public Optional<HashMap<String,String>> updateGroupByName(String oldName, String newName) {
        try(PreparedStatement statement = groupRepository.updateGroupByName(oldName, newName)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                HashMap<String,String> group = new HashMap<>();
                group.put("name",newName);
                return Optional.of(group);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Object deleteGroupByName(String name) {
        try(PreparedStatement statement = groupRepository.deleteGroupByName(name)) {
            int rowsAffected = statement .executeUpdate();
            if (rowsAffected > 0) {
                HashMap<String,String> group = new HashMap<>();
                group.put("name",name);
                return Optional.of(group);
            }
        }catch (SQLException error){
            throw new RuntimeException(error);
        }
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Error on delete group",
                name
        );
    }
}
