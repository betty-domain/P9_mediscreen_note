package com.mediscreen.note.controller;

import com.mediscreen.note.exceptions.NoteNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public Note addNote(@RequestParam Integer patientId, @RequestParam String note) {
        Note noteToCreate = new Note(patientId, note, LocalDate.now());
        return noteService.addNote(noteToCreate);
    }

    @GetMapping("/patHistory")
    public List<Note> getNotesForPatient(@RequestParam Integer patientId) {
        return noteService.getNotesForPatient(patientId);
    }

    @GetMapping("/patHistory/get")
    public Note getNote(@RequestParam String noteId) throws NoteNotFoundException {
        return noteService.getNoteById(noteId);
    }

    @PutMapping("/patHistory/update")
    public Note updateNote(@RequestParam String noteId, @RequestParam Integer patientId, @RequestParam String note) throws NoteNotFoundException {
        Note noteToUpdate = new Note(noteId,patientId,note);
        return noteService.updateNote(noteToUpdate);
    }

}
