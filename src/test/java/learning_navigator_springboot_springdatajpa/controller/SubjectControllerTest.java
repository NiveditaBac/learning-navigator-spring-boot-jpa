package learning_navigator_springboot_springdatajpa.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import learning_navigator_springboot_springdatajpa.dto.SubjectDto;
import learning_navigator_springboot_springdatajpa.service.ISubjectService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(SubjectController.class)
@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISubjectService subjectService;

    private SubjectDto subjectDto;

    @BeforeEach
    void setUp(){
        subjectDto = new SubjectDto();
        subjectDto.setSubjectId(1L);
        subjectDto.setSubjectName("Java");

    }
    
    @Test
    @DisplayName("Get Subject by given Id")
    public void testSubjectById() throws Exception{

        Mockito.when(subjectService.getSubjectById(1L)).thenReturn(subjectDto);

        mockMvc.perform(get("/subjects/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.subjectId").value(1L))
               .andExpect(jsonPath("$.subjectName").value("Java"));

        Mockito.verify(subjectService, Mockito.times(1)).getSubjectById(1L);
    }
}