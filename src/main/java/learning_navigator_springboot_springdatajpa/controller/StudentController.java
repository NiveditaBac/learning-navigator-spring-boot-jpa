package learning_navigator_springboot_springdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.service.IStudentService;

@RestController
@RequestMapping(StudentController.STUDENT_API_ENDPOINT)
public class StudentController {

    public final static String STUDENT_API_ENDPOINT = "/students";
    public final static String STUDENT_API = "/{studentRegistrationId}";
    
    @Autowired
    private IStudentService studentService;


    //Register Student REST API
    @PostMapping
    public ResponseEntity<StudentDto> registerStudent(@RequestBody @Valid StudentDto studentDto){

        return new ResponseEntity<>(studentService.registerStudent(studentDto), HttpStatus.CREATED);
        
    }

    //Get Student By ID REST API
    @GetMapping(STUDENT_API)
    public ResponseEntity<StudentDto> getStudentByRegistrationId(@PathVariable Long studentRegistrationId){
        StudentDto studentDto = studentService.getStudentByRegistrationId(studentRegistrationId);
        return ResponseEntity.ok(studentDto);
    }

    //Get All Students REST API
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){

        List<StudentDto> studentDtos = studentService.getAllStudents();
        if(studentDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentDtos, HttpStatus.OK); 
    }

    //Update Student REST API
    @PutMapping(STUDENT_API)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentRegistrationId, @RequestBody StudentDto studentDto){  

        return ResponseEntity.ok(studentService.updateStudent(studentRegistrationId, studentDto));

    }   

    //Delete Student By ID REST API
    @DeleteMapping(STUDENT_API)
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentRegistrationId){
        studentService.deleteStudentById(studentRegistrationId);
        return ResponseEntity.ok("Student deleted successfully");
    }
}   