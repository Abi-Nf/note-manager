package com.note.manager.build.services;

import com.note.manager.build.model.Group;
import com.note.manager.build.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() throws SQLException {
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
        try(ResultSet resultSet = groupRepository.findByName(name).executeQuery()){
            if (resultSet.next()) {
                Group group = new Group();
                group.setName(resultSet.getString("name"));
                // Set other properties of group from resultSet here
                return Optional.of(group);
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return Optional.empty();
    }

    public Group saveGroup(Group group) throws SQLException {
        int rowsAffected = groupRepository.saveGroup(group).executeUpdate();
        if (rowsAffected > 0) {
            return group;
        } else {
            throw new SQLException("Failed to save group");
        }
    }

    public Group updateGroupByName(String oldName, String newName) throws SQLException {
        int rowsAffected = groupRepository.updateGroupByName(oldName, newName).executeUpdate();
        if (rowsAffected > 0) {
            Group group = new Group();
            group.setName(newName);
            return group;
        } else {
            throw new SQLException("Failed to update group");
        }
    }

    public void deleteGroupByName(String name) throws SQLException {
        int rowsAffected = groupRepository.deleteGroupByName(name).executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("Failed to delete group");
        }
    }
}
