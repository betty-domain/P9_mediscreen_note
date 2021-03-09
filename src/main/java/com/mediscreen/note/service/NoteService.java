package com.mediscreen.note.service;

import com.mediscreen.note.exceptions.NoteException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private static final Logger logger = LogManager.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public Note addNote(Note note) throws NoteException {
        logger.debug("Call to noteService.addNote");
        return noteRepository.save(note);
    }

}
