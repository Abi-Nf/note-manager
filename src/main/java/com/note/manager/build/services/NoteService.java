package com.note.manager.build.services;

import com.note.manager.build.model.Note;
import com.note.manager.build.model.Student;
import com.note.manager.build.model.Subject;
import com.note.manager.build.repository.NoteRepository;
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
public class NoteService {
    NoteRepository repository;

    public NoteService(Connection connection){
        this.repository = new NoteRepository(connection);
    }

    public Optional<Note> SaveNote(Note note) {
        try {
            PreparedStatement statement = this.repository.saveNote(note);
            int result = statement.executeUpdate();
            if(result > 0){
                return Optional.of(note);
            }
        } catch (SQLException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Optional<HashMap<Integer,Double>> UpdateNoteById(int id, double value){
        try {
            HashMap<Integer,Double> note = new HashMap<>();

            PreparedStatement statement = repository.UpdateNoteById(id, value);
            boolean isUpdated = statement.executeQuery().rowUpdated();

            if(isUpdated){
                note.put(id, value);
                return Optional.of(note);
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return Optional.empty();
    }

    public String DeleteNoteById(int id) {
        try {
            boolean result;
            try (PreparedStatement statement = this.repository.DeleteById(id)) {
                result = statement.executeQuery().rowDeleted();
            }
            if(result){
                return String.format("Note on id: %s deleted",id);
            }
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return String.format("Note on id: %s not deleted",id);
    }


    public List<Note> findBySubject(String subject) {
        try {
            List<Note> noteList = new ArrayList<>();
            PreparedStatement query = repository.findBySubject(subject);
            return getNotes(noteList, query);
        }catch (SQLException error){
            System.out.println(error.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Note> findByStudentName(String student){
        try {
            List<Note> noteList = new ArrayList<>();
            PreparedStatement query = repository.findByStudentName(student);
            return getNotes(noteList,query);
        }catch (SQLException error){
            System.out.println(error.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Note> findAll() {
        try {
            List<Note> noteList = new ArrayList<>();
            PreparedStatement query = repository.findAll();
            return getNotes(noteList, query);
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
        return new ArrayList<>();
    }

    private List<Note> getNotes(List<Note> noteList, PreparedStatement query) throws SQLException {
        ResultSet result = query.executeQuery();

        while (result.next()){
            Subject subject = new Subject(
                    result.getLong("subid"),
                    result.getString("name"),
                    result.getString("description")
            );

            Student student = new Student(
                    result.getLong("sid"),
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getString("ref"),
                    result.getString("email"),
                    result.getString("phone"),
                    result.getDate("birthdate").toLocalDate(),
                    result.getTimestamp("creation_date").toLocalDateTime(),
                    result.getLong("classId"),
                    result.getLong("GroupId")
            );

            Note note = new Note(
                    result.getInt("id"),
                    result.getDouble("value"),
                    subject,
                    student,
                    result.getTimestamp("evaluationDate").toLocalDateTime(),
                    result.getDouble("hasBonus")
            );
            noteList.add(note);
        }

        return noteList;
    }
}
