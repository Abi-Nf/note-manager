package com.note.manager.build.controller;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Subject;
import com.note.manager.build.services.SubjectService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    SubjectService service;

    public SubjectController(Connection connection){
        this.service = new SubjectService(connection);
    }

    @GetMapping
    List<Subject> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{name}")
    Optional<Subject> findByName(
        @PathVariable String name,
        HttpServletResponse response
    ){
        Optional<Subject> subject = this.service.findByName(name);
        if(subject.isEmpty()) response.setStatus(
                HttpStatus.NO_CONTENT.value()
        );
        return subject;
    }

    @PostMapping
    Object saveSubject(
        @RequestBody Subject subject,
        HttpServletResponse response
    ){
        Object result = this.service.saveSubject(subject);
        if(result instanceof ErrorMessage) response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );
        return result;
    }
    @PutMapping("/{name}")
    Object updateSubject(
            @PathVariable String name,
            @RequestBody Subject subject,
            HttpServletResponse response
    ){
        Object result = this.service.updateByName(
                name,
                subject
        );
        if(result instanceof ErrorMessage) response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );
        return result;
    }

    @DeleteMapping("/{name}")
    Object deleteSubjectByName(
            @PathVariable String name,
            HttpServletResponse response
    ){
        Object deletion = this.service.deleteByName(name);
        if(deletion instanceof ErrorMessage) response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );
        return deletion;
    }
}
