package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentRowMapper {
    public List<Student> processRow(ResultSet rs) throws SQLException {
        List<Student> students = new ArrayList<>();

        while (rs.next()){
            Long id = (long) rs.getInt("id");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String ref = rs.getString("ref");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
            LocalDateTime creationDate = rs.getTimestamp("creationDate").toLocalDateTime();

            students.add(
                new Student(id,firstName,lastName,ref,email,phone,birthdate,creationDate)
            );
        }

        return students;
    }
}
