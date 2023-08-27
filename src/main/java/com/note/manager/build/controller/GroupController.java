package com.note.manager.build.controller;

import com.note.manager.build.model.Group;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
public class GroupController {
    @GetMapping
    List<Group> findAll(){
        return null;
    }

    Group findByName(){
        return null;
    }

    @PostMapping
    Group saveGroup(){
        return null;
    }

    @PutMapping
    Group updateGroup(){
        return null;
    }

    @DeleteMapping
    Group deleteGroup(){
        return null;
    }
}