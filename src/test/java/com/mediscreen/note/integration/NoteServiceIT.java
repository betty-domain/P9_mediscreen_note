package com.mediscreen.note.integration;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoteServiceIT {

    @Autowired
    NoteService noteService;

    @Test
    void addNote()
    {
        Note note = new Note(1,"ma note d'historique de patient", LocalDate.now());
        assertThat(noteService.addNote(note)).isNotNull();
    }
}
