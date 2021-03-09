package com.mediscreen.note.service;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTests {

    @MockBean
    NoteRepository noteRepositoryMock;

    @Autowired
    NoteService noteService;

    @Test
    void addNote_ok()
    {
        Note note = new Note(1,"ma prise de note de médecin", LocalDate.now());
        when(noteRepositoryMock.save(note)).thenReturn(note);

        assertThat(noteService.addNote(note)).isNotNull();
    }


}
