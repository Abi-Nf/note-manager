package com.note.manager.build.controller;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.Utils.GroupUpdater;
import com.note.manager.build.model.Group;
import com.note.manager.build.services.GroupService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    GroupService service;

    public GroupController(Connection connection){
        this.service = new GroupService(connection);
    }

    @GetMapping
    List<Group> findAll(){
        return this.service.findAll();
    }

    @GetMapping("/{name}")
    Optional<Group> findByName(
            @PathVariable String name,
            HttpServletResponse response
    ){
        Optional<Group> result = this.service.findByName(name);
        if(result.isEmpty()) response.setStatus(
                HttpStatus.NO_CONTENT.value()
        );
        return result;
    }

    @PostMapping
    Object saveGroup(
            @RequestBody Group group,
            HttpServletResponse response
    ){
        Object result = this.service.saveGroup(group);
        if(result instanceof ErrorMessage) response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );
        return result;
    }

    @PutMapping
    Object updateGroup(
            @RequestBody GroupUpdater updater,
            HttpServletResponse response
    ){
        Optional<HashMap<String,String>> result = this
                .service
                .updateGroupByName(
                        updater.getOldName(),
                        updater.getNewName()
                );

        if(result.isEmpty()) response.setStatus(
                HttpStatus.NO_CONTENT.value()
        );
        return result;
    }

    @DeleteMapping("/{name}")
    Object deleteGroupByRef(
            @PathVariable String name,
            HttpServletResponse response
    ){
        Object result = this.service.deleteGroupByName(name);
        if(result instanceof ErrorMessage) response.setStatus(
                HttpStatus.BAD_REQUEST.value()
        );
        return result;
    }
}