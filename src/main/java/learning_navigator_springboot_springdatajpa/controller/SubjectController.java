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

import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.dto.SubjectDto;
import learning_navigator_springboot_springdatajpa.service.ISubjectService;

@RestController
@RequestMapping(SubjectController.SUBJECT_API_ENDPOINT)
public class SubjectController {

    public final static String SUBJECT_API_ENDPOINT = "/subjects";
    public final static String SUBJECT_API = "/{subjectId}";
    public final static String SUBJECT_API_ALL_STUDENTS = "/{subjectId}/student";
    public final static String SUBJECT_ADD_API = "/student/{studentRegistrationId}";
    public final static String SUBJECT_DELETE_FROM_STUDENT_API = "/student/{studentRegistrationId}/subject/{subjectId}";
    public final static String STUDENT_API = "/student/{studentRegistrationId}";

    @Autowired
    private ISubjectService subjectService;
    
    //Add/Create Subject and Save in Students REST API
    @PostMapping(SUBJECT_ADD_API)
    public ResponseEntity<SubjectDto> addSubject(@PathVariable Long studentRegistrationId, @RequestBody SubjectDto subjectDto){
        return new ResponseEntity<>(subjectService.addSubject(studentRegistrationId, subjectDto), HttpStatus.CREATED);
    }

    //Get Subject By ID REST API
    @GetMapping(SUBJECT_API)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long subjectId){
        SubjectDto subjectDto = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subjectDto);
    }

    //Get All Subjects REST API
    @GetMapping()
    public ResponseEntity<List<SubjectDto>> getAllSubjects(){

        List<SubjectDto> subjectDtos = subjectService.getAllSubjects();
        if(subjectDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjectDtos, HttpStatus.OK); 
    }

    //Update Subject REST API
    @PutMapping(SUBJECT_API)
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDto subjectDto){  

        return ResponseEntity.ok(subjectService.updateSubject(subjectId, subjectDto));

    }   

    //Delete Student By ID REST API
    @DeleteMapping(SUBJECT_API)
    public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubjectById(subjectId);
        return ResponseEntity.ok("Subject deleted successfully");
    }

    //Get all Subjects By Student ID REST API
    @GetMapping(STUDENT_API)
    public ResponseEntity<List<SubjectDto>> getAllSubjectsByStudentId(@PathVariable Long studentRegistrationId){
        List<SubjectDto> subjectDtos = subjectService.getAllSubjectsByStudentId(studentRegistrationId);
        return new ResponseEntity<>(subjectDtos, HttpStatus.OK);
    }

    //Get all Students By Subject ID REST API
    @GetMapping(SUBJECT_API_ALL_STUDENTS)
    public ResponseEntity<List<StudentDto>> getAllStudentsBySubjectId(@PathVariable Long subjectId){
        List<StudentDto> studentDtos = subjectService.getAllStudentsBySubjectId(subjectId);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    //Delete Subject From Student REST API
    @DeleteMapping(SUBJECT_DELETE_FROM_STUDENT_API)
    public ResponseEntity<HttpStatus> deleteSubjectFromStudent(@PathVariable Long studentRegistrationId, @PathVariable Long subjectId) {
        subjectService.deleteSubjectFromStudent(studentRegistrationId, subjectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

