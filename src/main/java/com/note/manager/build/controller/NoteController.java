package com.note.manager.build.controller;

import com.note.manager.build.Utils.ErrorMessage;
import com.note.manager.build.Utils.NoteUpdateTemplate;
import com.note.manager.build.model.Note;
import com.note.manager.build.services.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        value = "/note",
        consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.MULTIPART_FORM_DATA_VALUE,
                MediaType.APPLICATION_FORM_URLENCODED_VALUE
        }
)
public class NoteController {
    NoteService noteService;

    public NoteController(Connection connection){
        this.noteService = new NoteService(connection);
    }

    @GetMapping
    public List<Note> finNoteBySubject(
        @RequestParam(required = false) String subject,
        HttpServletResponse stat
    ){
        stat.setStatus(
            HttpStatus.ACCEPTED.value()
        );
        if(subject != null && subject.length() > 0){
            return noteService.findBySubject(subject);
        }else {
            return noteService.findAll();
        }
    }

    @PutMapping
    public Object updateNote(
            @RequestBody NoteUpdateTemplate noteUpdate,
            HttpServletResponse stat
    ){
        Optional<HashMap<Integer, Double>> note = noteService.UpdateNoteById(
                noteUpdate.getId(),
                noteUpdate.getValue()
        );
        if(note.isPresent()){
            return note;
        }else {
            stat.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ErrorMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    "id of the note and new value required",
                    noteUpdate
            );
        }
    }

    @PostMapping
    public Object saveNote(
        @RequestBody Note note,
        HttpServletResponse stat
    ) {
        Optional<Note> noteOptional = noteService.SaveNote(note);
        if(noteOptional.isPresent()){
            return noteOptional;
        }else {
            stat.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            return new ErrorMessage(
                    HttpStatus.NOT_ACCEPTABLE.value(),
                    "Error on saving note",
                    note
            );
        }
    }

    @DeleteMapping
    public Object deleteNoteById(
            @RequestBody(required = false) int id,
            HttpServletResponse stat
    ){
        System.out.println("request delete");
        System.out.println(id);

        String message = noteService.DeleteNoteById(id);
        if(message.matches("/not deleted/gim")){
            stat.setStatus(HttpStatus.NOT_MODIFIED.value());
        }

        return message;
    }
}
