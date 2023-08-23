package com.note.manager.build.repository;

import com.note.manager.build.model.Note;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
public class NoteRepository {
    Connection connection;

    public NoteRepository(Connection connection){
        this.connection = connection;
    }

    public PreparedStatement findBySubject(String subject) throws SQLException {
        String sql = """
            SELECT * FROM note
            INNER JOIN student s on s.id = note.studentid
            Inner Join subject sub on sub.id = note.subjectid
            WHERE sub.name ilike ?
            """;
        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1,"%" + subject + "%");

        return query;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = """
            SELECT *, sub.id as subid, s.id as sid FROM note
            INNER JOIN student s on s.id = note.studentid
            Inner Join subject sub on sub.id = note.subjectid
            """;
        return connection.prepareStatement(sql);
    }

    public PreparedStatement findByStudentName(String student) throws SQLException {
        String sql = """
            SELECT *, sub.id as subid, s.id as sid FROM note
            INNER JOIN student s on s.id = note.studentid
            Inner Join subject sub on sub.id = note.subjectid
            Where s.firstname ilike ? or s.lastname ilike ?
            """;

        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1,"%"+student+"%");
        query.setString(2,"%"+student+"%");

        return query;
    }


    public PreparedStatement saveNote(Note note) throws SQLException {
        String sql = """
        insert into note
        (value, subjectid, studentid, evaluationdate, hasbonus)
        values (?,?,?,?,?);
        """;
        PreparedStatement query = connection.prepareStatement(sql);

        query.setDouble(1,note.getValue());
        query.setLong(2,note.getSubjectId());
        query.setLong(3,note.getStudentId());
        query.setTimestamp(4, Timestamp.valueOf(note.getEvaluationDate()));
        query.setDouble(5,note.getHasBonus());

        return query;
    }

    public PreparedStatement DeleteById(int id) throws SQLException {
        String sql = """
        delete from note where note.id = ?
        """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);

        return statement;
    }

    public PreparedStatement UpdateNoteById(int id, double value) throws SQLException {
        String sql = """
        UPDATE note set value = ? where id = ?
        """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,id);
        statement.setDouble(2,value);

        return statement;
    }
}
