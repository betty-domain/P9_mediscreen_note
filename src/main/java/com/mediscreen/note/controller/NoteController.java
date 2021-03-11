package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public Note addNote(@RequestParam Integer patientId, @RequestParam String note)
    {
        Note noteToCreate = new Note(patientId,note, LocalDate.now());
        return noteService.addNote(noteToCreate);
    }

}
