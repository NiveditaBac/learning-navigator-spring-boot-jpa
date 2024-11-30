package learning_navigator_springboot_springdatajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.service.IStudentService;

@WebMvcTest(StudentController.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    private StudentDto studentDto;

    @BeforeEach
    void setUp(){
        studentDto = new StudentDto();
        studentDto.setStudentRegistrationId(1L);
        studentDto.setStudentName("Alice");

    }
    
    @Test
    @DisplayName("Get Student by given Id")
    public void testStudentById() throws Exception{

        Mockito.when(studentService.getStudentByRegistrationId(1L)).thenReturn(studentDto);

        mockMvc.perform(get("/students/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.studentRegistrationId").value(1L))
               .andExpect(jsonPath("$.studentName").value("Alice"));

        Mockito.verify(studentService, Mockito.times(1)).getStudentByRegistrationId(1L);
    }

}
