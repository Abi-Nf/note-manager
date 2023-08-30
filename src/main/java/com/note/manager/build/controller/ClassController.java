package com.note.manager.build.controller;

import com.note.manager.build.Utils.ClassUpdater;
import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.model.Class;
import com.note.manager.build.services.ClassService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.Optional;

@RestController
@RequestMapping("/class")
public class ClassController {
    ClassService service;

    public ClassController(Connection connection){
        this.service = new ClassService(connection);
    }

    @GetMapping
    Object findAll(){
        return service.findAll();
    }

    @GetMapping("/{name}")
    Object findByName(
            @PathVariable String name,
            HttpServletResponse response
    ){
        if(name.length() > 0){
            Optional<Class> classOptional = service.findByName(name);
            if(classOptional.isEmpty()) response.setStatus(HttpStatus.NO_CONTENT.value());
            return classOptional;
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return null;
    }

    @PostMapping
    Object saveClass(Class aClass){
        return service.saveClass(aClass);
    }

    @PutMapping
    Object updateClass(
        @RequestBody ClassUpdater updater,
        HttpServletResponse response
    ){
        if(!(
                updater.getOldName().length() > 0
                &&
                updater.getNewName().length() > 0
        )){
            response.setStatus(
                    HttpStatus.BAD_REQUEST.value()
            );
        }
        return service.updateName(
                updater.getOldName(),
                updater.getNewName()
        );
    }

    @DeleteMapping
    Object deleteClass(
            @RequestBody Class aClass,
            HttpServletResponse response
    ){
        Object result = service.deleteByName(aClass.getName());
        if(result instanceof ErrorMessage){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return result;
    }
}
