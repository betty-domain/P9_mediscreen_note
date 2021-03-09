package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public Note addNote(@RequestBody Note note)
    {
        return noteService.addNote(note);
    }

}
