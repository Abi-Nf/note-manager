package com.note.manager.build.controller;

import com.note.manager.build.model.Class;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClassController {

    @GetMapping
    List<Class> findAll(){}

    @GetMapping
    Class findByName(){}

    @PostMapping
    Class saveClass(){}

    @PutMapping
    Class updateClass(){}

    @DeleteMapping
    Class deleteClass(){}
}
