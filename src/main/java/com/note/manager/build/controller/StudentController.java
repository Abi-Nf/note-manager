package com.note.manager.build.controller;

import com.note.manager.build.model.Student;
import com.note.manager.build.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping("/{std}")
    public List<Student> findStudentByRef(
            @PathVariable(required = false) String std
    ){
        return null;
    }

    @PostMapping
    public void addStudent(){}

    @PutMapping
    public void updateStudent(){}

    @DeleteMapping
    public void deleteStudentByRef(){}

}
