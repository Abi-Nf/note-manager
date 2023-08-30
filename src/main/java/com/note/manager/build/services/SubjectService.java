package com.note.manager.build.services;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Subject;
import com.note.manager.build.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(Connection connection) {
        this.subjectRepository = new SubjectRepository(connection);
    }

    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList<>();
        try(PreparedStatement statement = subjectRepository.findAll()) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("id"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("description"));
                subjects.add(subject);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    public Optional<Subject> findByName(String name) {
        try(PreparedStatement statement = subjectRepository.findByName(name)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getLong("id"));
                subject.setName(resultSet.getString("name"));
                subject.setDescription(resultSet.getString("description"));
                return Optional.of(subject);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Object saveSubject(Subject subject) {
        try(PreparedStatement statement = subjectRepository.saveSubject(subject)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) return subject;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Failed to save subject",
                subject
        );
    }

    public Object updateByName(String name, Subject subject) {
        try(PreparedStatement statement = subjectRepository.updateByName(name, subject)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) return subject;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                String.format("Failed to update subject with name: %s",name),
                subject
        );
    }

    public Object deleteByName(String name) {
        HashMap<String, String> deletion = new HashMap<>();
        deletion.put("name",name);
        try(PreparedStatement statement = subjectRepository.deleteByName(name)) {
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return deletion;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ErrorMessage(
                400,
                "Failed to delete subject",
                deletion
        );
    }
}
