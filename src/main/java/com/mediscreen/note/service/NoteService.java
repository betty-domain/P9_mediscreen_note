package com.mediscreen.note.service;

import com.mediscreen.note.exceptions.NoteNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private static final Logger logger = LogManager.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public Note addNote(Note note) {
        logger.debug("Call to noteService.addNote");
        return noteRepository.save(note);
    }

    public List<Note> getNotesForPatient(Integer patientId) {
        logger.debug("Call to noteService.getNotesForPatient");
        return noteRepository.findByPatientId(patientId);
    }

    public Note getNoteById(String noteId) throws NoteNotFoundException {
        logger.debug("Call to noteService.getNoteById");
        return noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    public Note updateNote(Note note) throws NoteNotFoundException {
        logger.debug("Call to noteService.updateNote");
        Optional<Note> noteToUpdate = noteRepository.findById(note.getId());
        if (noteToUpdate.isPresent()) {
            note.setCreatedDate(noteToUpdate.get().getCreatedDate());
            return noteRepository.save(note);
        } else {
            logger.debug("updateNote : note doesn't exist");
            throw new NoteNotFoundException("Note not found");
        }
    }

}
