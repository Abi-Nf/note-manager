package com.note.manager.build.controller;

import com.note.manager.build.config.ConfigurationProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @GetMapping("/{studentStd}")
    public void findMark(
            @PathVariable(required = false) String studentStd
    ){
    }

    @PostMapping("/")
    public void addMark(){}

    @PutMapping("/")
    public void updateMark(){}

    @DeleteMapping("/")
    public void deleteMark(){}
}
