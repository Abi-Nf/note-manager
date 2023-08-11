package com.note.manager.build.repository;

import com.note.manager.build.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentRowMapper()::processRow);
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT * FROM student WHERE id = " + id;
        return (Student) jdbcTemplate.query(sql, new StudentRowMapper()::processRow);
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO student (firstname, lastname, ref, email, phone, birthdate, creation_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getRef(),
                student.getEmail(), student.getPhone(), student.getBirthdate(), student.getCreationDate());
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE student SET firstname = ?, lastname = ?, ref = ?, email = ?, phone = ?, birthdate = ?, " +
                "creation_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getRef(),
                student.getEmail(), student.getPhone(), student.getBirthdate(), student.getCreationDate(), student.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
