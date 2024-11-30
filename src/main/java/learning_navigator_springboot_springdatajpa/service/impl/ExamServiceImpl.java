package learning_navigator_springboot_springdatajpa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning_navigator_springboot_springdatajpa.dto.ExamDto;
import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.entity.Exam;
import learning_navigator_springboot_springdatajpa.entity.Student;
import learning_navigator_springboot_springdatajpa.entity.Subject;
import learning_navigator_springboot_springdatajpa.exception.DuplicateEntryExamException;
import learning_navigator_springboot_springdatajpa.exception.ExamNotFoundException;
import learning_navigator_springboot_springdatajpa.exception.StudentNotFoundException;
import learning_navigator_springboot_springdatajpa.exception.SubjectNotEnrolledException;
import learning_navigator_springboot_springdatajpa.mapper.ExamMapper;
import learning_navigator_springboot_springdatajpa.mapper.StudentMapper;
import learning_navigator_springboot_springdatajpa.repository.IExamRepository;
import learning_navigator_springboot_springdatajpa.repository.IStudentRepository;
import learning_navigator_springboot_springdatajpa.service.IExamService;


@Service
public class ExamServiceImpl implements IExamService{

    @Autowired
    private IExamRepository examRepository;

    @Autowired
    private IStudentRepository studentRepository;

    public boolean isSubjectEnrolledByStudent(Student student, String subjectName){

        Set<Subject> enrolledSubjects = student.getSubjects();
        for(Subject subject : enrolledSubjects){
            if(subject.getSubjectName().equals(subjectName)){
                return true;
            }
        }
        return false;
    }

    public boolean isRegisteredExam(Student student, String subjectName){

        Set<Exam> registeredExams = student.getExams();
        for(Exam exam : registeredExams){
            if(exam.getSubjectName().equals(subjectName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public ExamDto registerExam(Long studentRegistrationId, ExamDto examDto) {

        String subjectName = examDto.getSubjectName();

        Student student =  studentRepository.findById(studentRegistrationId).orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));

          //Check if Subject is present in Student
        if(!isSubjectEnrolledByStudent(student, subjectName)){
            throw new SubjectNotEnrolledException("Before registering for exam, please enroll for the subject name : " + subjectName);
        }

        //Check if student is already registered for exam
        if(isRegisteredExam(student, subjectName)){
            throw new DuplicateEntryExamException("Student is already registered for the Exam of Eubject : " + subjectName);
        }

        //If given exam object is already present
        if(examDto.getExamId() != null && examDto.getExamId() > 0){
            Long examId = examDto.getExamId();
            Exam existingExam = examRepository.findById(examId)
                                              .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID : " + examId));
            student.addExam(existingExam);
            studentRepository.save(student);
            return ExamMapper.mapToExamDto(existingExam);
        }

        //If given exam is not present
        Exam newExam = examRepository.save(ExamMapper.mapToExam(examDto));
        student.addExam(newExam);
        studentRepository.save(student);
        return ExamMapper.mapToExamDto(newExam);
    }

    @Override
    public ExamDto getExamById(Long examId) {
       Exam exam = examRepository
                .findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID : " + examId));
        return ExamMapper.mapToExamDto(exam);
    }

    @Override
    public List<ExamDto> getAllExams() {
        List<Exam> exams = examRepository.findAll();
       return exams.stream()
                      .map((exam) -> ExamMapper.mapToExamDto(exam))
                      .collect(Collectors.toList());
    }

    @Override
    public ExamDto updateExam(Long examId, ExamDto examDto) {
        Exam exam = examRepository
        .findById(examId)
        .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID : " + examId));
        exam.setSubjectName(examDto.getSubjectName());
        Exam updateExam = examRepository.save(exam);
        return ExamMapper.mapToExamDto(updateExam);
    }

    @Override
    public void deleteExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
                         .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID : " + examId));
        List<Student> students = studentRepository.findAll();
        for(Student student : students){
            if(student.getExams().contains(exam)){
                student.getExams().remove(exam);
            }
            studentRepository.save(student);
        }
        examRepository.deleteById(examId);
    }

    @Override
    public List<ExamDto> getAllExamsByStudentId(Long studentRegistrationId) {
        if(!studentRepository.existsById(studentRegistrationId)){
            throw new StudentNotFoundException("Student not found with ID : " + studentRegistrationId);
        }
        Student student = studentRepository.findById(studentRegistrationId)
                                            .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        Set<Exam> examSet = student.getExams();
        List<Exam> examList = new ArrayList<>(examSet);
        return examList.stream().map(exam -> ExamMapper.mapToExamDto(exam)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudentsByExamId(Long examId) {
         if(!examRepository.existsById(examId)){
            throw new ExamNotFoundException("Exam not found with ID : " + examId);
        }
        Exam exam = examRepository.findById(examId)
                                            .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID : " + examId));
        Set<Student> studentSet = exam.getStudents();
        List<Student> studentList = new ArrayList<>(studentSet);
        return studentList.stream().map(student -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public void deleteExamFromStudent(Long studentRegistrationId, Long examId) {
        Student student = studentRepository.findById(studentRegistrationId)
                         .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        student.removeExam(examId);
        studentRepository.save(student);
    }

}
