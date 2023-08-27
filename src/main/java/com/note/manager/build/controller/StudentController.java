package com.note.manager.build.controller;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Student;
import com.note.manager.build.services.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(Connection connection){
        this.studentService = new StudentService(connection);
    }

    @GetMapping("/{std}")
    public List<Student> findStudentByRef(
            @PathVariable(required = false) String std
    ){
        return null;
    }

    @PostMapping
    public Object addStudent(
            @RequestBody Student student,
            HttpServletResponse response
    ){
        Optional<Student> result = this.studentService.saveStudent(student);
        if (result.isPresent()) return result;

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "New student not saved, missing values",
                student
        );
    }

    @PutMapping
    public Student updateStudentByRef(){
        return null;
    }

    @DeleteMapping
    public void deleteStudentByRef(){}

}
