package com.note.manager.build.Utils;

import com.note.manager.build.model.Class;
import com.note.manager.build.model.Group;
import com.note.manager.build.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentParser {
    public static Student parseStudentResultSet(ResultSet result) throws SQLException {
        Student student = new Student();
        student.setId(result.getLong("id"));
        student.setFirstName(result.getString("firstname"));
        student.setLastName(result.getString("lastname"));
        student.setRef(result.getString("ref"));
        student.setEmail(result.getString("email"));
        student.setPhone(result.getString("phone"));
        student.setBirthdate(
                result.getDate("birthdate").toLocalDate()
        );
        student.setCreationDate(
                result.getTimestamp("creation_date").toLocalDateTime()
        );
        student.setClassId(
                result.getLong("classid")
        );
        student.setGroupId(
                result.getLong("groupid")
        );
        Class aClass = new Class(
                result.getLong("classid"),
                result.getString("classname")
        );
        Group group = new Group(
                result.getLong("groupid"),
                result.getString("groupname")
        );
        student.setAClass(aClass);
        student.setGroup(group);
        return student;
    }
}
