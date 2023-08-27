package com.note.manager.build.controller;

import com.note.manager.build.model.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {
    @GetMapping
    List<Subject> findAll(){
        return null;
    }

    @GetMapping
    Subject findByName(){
        return null;
    }

    @PostMapping
    Subject saveGroup(){}

    @PutMapping
    Subject updateGroup(){}

    @DeleteMapping
    Subject deleteGroup(){}
}
