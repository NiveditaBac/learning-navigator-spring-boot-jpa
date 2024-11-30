package learning_navigator_springboot_springdatajpa.controller;

import java.util.List;

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

import learning_navigator_springboot_springdatajpa.dto.ExamDto;
import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.service.IExamService;

@RestController
@RequestMapping(ExamController.EXAM_API_ENDPOINT)
public class ExamController {

    public final static String EXAM_API_ENDPOINT = "/exams";
    public final static String EXAM_API = "/{examId}";  
    public final static String EXAM_STUDENT_API = "/student/{studentRegistrationId}";
    public final static String EXAM_DELETE_FROM_STUDENT_API = "/student/{studentRegistrationId}/exam/{examId}";
    public final static String EXAM_API_ALL_STUDENTS = "/{examId}/student";

    
    private final IExamService examService;

    public ExamController(IExamService examService) {
        this.examService = examService;
    }

    //Add/Create Exam and Save in Students REST API
    @PostMapping(EXAM_STUDENT_API)
    public ResponseEntity<ExamDto> registerExam(@PathVariable Long studentRegistrationId, @RequestBody ExamDto examDto){
        return new ResponseEntity<>(examService.registerExam(studentRegistrationId, examDto), HttpStatus.CREATED);
    }

    //Get Exam By ID REST API
    @GetMapping(EXAM_API)
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long examId){
        ExamDto examDto = examService.getExamById(examId);
        return ResponseEntity.ok(examDto);
    }

    //Get All Exams REST API
    @GetMapping()
    public ResponseEntity<List<ExamDto>> getAllExams(){

        List<ExamDto> examDtos = examService.getAllExams();
        if(examDtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(examDtos, HttpStatus.OK); 
    }

    //Update Subject REST API
    @PutMapping(EXAM_API)
    public ResponseEntity<ExamDto> updateExam(@PathVariable Long examId, @RequestBody ExamDto examDto){  

        return ResponseEntity.ok(examService.updateExam(examId, examDto));

    }   

    //Delete Student By ID REST API
    @DeleteMapping(EXAM_API)
    public ResponseEntity<String> deleteExam(@PathVariable Long examId){
        examService.deleteExamById(examId);
        return ResponseEntity.ok("Exam deleted successfully");
    }


    //Get all Exams By Student ID REST API
    @GetMapping(EXAM_STUDENT_API)
    public ResponseEntity<List<ExamDto>> getAllExamsByStudentId(@PathVariable Long studentRegistrationId){
        List<ExamDto> examDtos = examService.getAllExamsByStudentId(studentRegistrationId);
        return new ResponseEntity<>(examDtos, HttpStatus.OK);
    }


    //Get all Students By Exam ID REST API
    @GetMapping(EXAM_API_ALL_STUDENTS)
    public ResponseEntity<List<StudentDto>> getAllStudentsByExamId(@PathVariable Long examId){
        List<StudentDto> studentDtos = examService.getAllStudentsByExamId(examId);
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    //Delete Exam From Student REST API
    @DeleteMapping(EXAM_DELETE_FROM_STUDENT_API)
    public ResponseEntity<HttpStatus> deleteExamFromStudent(@PathVariable Long studentRegistrationId, @PathVariable Long examId) {
        examService.deleteExamFromStudent(studentRegistrationId, examId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
