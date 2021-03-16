package com.mediscreen.note.integration;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import com.mediscreen.note.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest
public class NoteServiceIT {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository noteRepository;

    Note note1;
    Note note2;

    @BeforeEach
    void insertTestData()
    {
        note1 = noteRepository.save(new Note(10,"Note d'un patient n°1", LocalDate.of(2010,5,10)));
        note2 = noteRepository.save(new Note(20,"Note d'un patient n°2", LocalDate.of(2001,10,25)));
    }

    @Test
    void addNote()
    {
        Note note = new Note(1,"ma note d'historique de patient", LocalDate.now());
        assertThat(noteService.addNote(note)).isNotNull();
    }

    @Test
    void getNotesForPatient()
    {
        assertThat(noteService.getNotesForPatient(10).size()).isEqualTo(1);
    }

    //TODO add test for update and getOneNoteById
}
