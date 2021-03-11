package com.mediscreen.note.controller;

import com.mediscreen.note.model.Note;
import com.mediscreen.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteServiceMock;

    @Test
    void addNote() throws Exception
    {
        Note note = new Note(1,"ma prise de note", LocalDate.now());

        when(noteServiceMock.addNote(note)).thenReturn(note);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/patHistory/add").
                contentType(MediaType.APPLICATION_JSON).param("patientId", note.getPatientId().toString())
                .param("note",note.getNote());

        mockMvc.perform(builder).
                andExpect(status().isOk());
    }

    @Test
    void listNoteForPatient() throws Exception
    {
        Integer patientId=1;
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(patientId,"maNote",LocalDate.now()));
        noteList.add(new Note(patientId,"Ma 2ème note", LocalDate.now().minusDays(1)));
        when(noteServiceMock.getNotesForPatient(patientId)).thenReturn(noteList);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/patHistory").
                contentType(MediaType.APPLICATION_JSON).param("patientId", patientId.toString());

        mockMvc.perform(builder).
                andExpect(status().isOk()).
                andExpect(jsonPath("$").isArray());
    }
}
