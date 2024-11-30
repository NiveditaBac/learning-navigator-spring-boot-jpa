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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import learning_navigator_springboot_springdatajpa.dto.ExamDto;
import learning_navigator_springboot_springdatajpa.service.IExamService;


@WebMvcTest(ExamController.class)
@ExtendWith(MockitoExtension.class)
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IExamService examService;

    private ExamDto examDto;

    @BeforeEach
    void setUp(){
        examDto = new ExamDto();
        examDto.setExamId(1L);
        examDto.setSubjectName("Java");

    }
    
    @Test
    @DisplayName("Get Exam by given Id")
    public void testExamById() throws Exception{

        Mockito.when(examService.getExamById(1L)).thenReturn(examDto);

        mockMvc.perform(get("/exams/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.examId").value(1L))
               .andExpect(jsonPath("$.subjectName").value("Java"));

        Mockito.verify(examService, Mockito.times(1)).getExamById(1L);
    }
}
