package com.mediscreen.note.service;

import com.mediscreen.note.exceptions.NoteNotFoundException;
import com.mediscreen.note.model.Note;
import com.mediscreen.note.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void listNote_ok()
    {
        Integer patientId = 1;
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(patientId,"maNote",LocalDate.now()));
        noteList.add(new Note(patientId,"Ma 2ème note", LocalDate.now().minusDays(1)));
        when(noteRepositoryMock.findByPatientIdOrderByCreatedDateAsc(patientId)).thenReturn(noteList);

        assertThat(noteService.getNotesForPatient(patientId).size()).isEqualTo(2);
    }

    @Test
    void updateNote_OK()
    {
        Note note = new Note(1,"ma prise de note de médecin", LocalDate.now());
        when(noteRepositoryMock.findById(note.getId())).thenReturn(Optional.of(note));
        when(noteRepositoryMock.save(note)).thenReturn(note);

        assertThat(noteService.updateNote(note)).isNotNull();
    }

    @Test
    void updatePatient_NotExist()
    {
        Note note = new Note(1,"ma prise de note de médecin", LocalDate.now());
        when(noteRepositoryMock.findById(note.getId())).thenReturn(Optional.empty());
        when(noteRepositoryMock.save(note)).thenReturn(note);

        Exception exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.updateNote(note);
        });
        assertThat(exception.getMessage()).contains("Note not found");
    }

    @Test
    void getNote_OK()
    {
        Note note = new Note(1,"ma prise de note de médecin", LocalDate.now());
        when(noteRepositoryMock.findById(note.getId())).thenReturn(Optional.of(note));

        assertThat(noteService.getNoteById(note.getId())).isEqualTo(note);
    }

    @Test
    void getNote_NotFound()
    {
        Note note = new Note(1,"ma prise de note de médecin", LocalDate.now());
        when(noteRepositoryMock.findById(note.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.getNoteById(note.getId());
        });
        assertThat(exception.getMessage()).contains("Note not found");
    }
}
