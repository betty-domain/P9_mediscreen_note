package com.mediscreen.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.when;
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

        ObjectMapper objectMapper = new ObjectMapper();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/patHistory/add").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(note));

        mockMvc.perform(builder).
                andExpect(status().isOk());
    }
}
