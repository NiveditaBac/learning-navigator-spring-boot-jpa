package learning_navigator_springboot_springdatajpa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.dto.SubjectDto;
import learning_navigator_springboot_springdatajpa.entity.Student;
import learning_navigator_springboot_springdatajpa.entity.Subject;
import learning_navigator_springboot_springdatajpa.exception.StudentNotFoundException;
import learning_navigator_springboot_springdatajpa.exception.SubjectNotFoundException;
import learning_navigator_springboot_springdatajpa.mapper.StudentMapper;
import learning_navigator_springboot_springdatajpa.mapper.SubjectMapper;
import learning_navigator_springboot_springdatajpa.repository.IStudentRepository;
import learning_navigator_springboot_springdatajpa.repository.ISubjectRepository;
import learning_navigator_springboot_springdatajpa.service.ISubjectService;


@Service
public class SubjectServiceImpl implements ISubjectService{


    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public SubjectDto addSubject(Long studentRegistrationId, SubjectDto subjectDto) {

        Student student =  studentRepository.findById(studentRegistrationId).orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));

        String subjectName = subjectDto.getSubjectName();

        if(subjectRepository.existsBySubjectName(subjectName)){
            Subject existingSubject = subjectRepository.findBySubjectName(subjectName).orElseThrow(() -> new SubjectNotFoundException("Subject not found with Name : " + subjectName));
            student.addSubject(existingSubject);
            studentRepository.save(student);
            return SubjectMapper.mapToSubjectDto(existingSubject);
        }

        Subject newSubject = subjectRepository.save(SubjectMapper.mapToSubject(subjectDto));
        student.addSubject(newSubject);
        studentRepository.save(student);
        return SubjectMapper.mapToSubjectDto(newSubject);
    }

    @Override
    public SubjectDto getSubjectById(Long subjectId) {
        Subject subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID : " + subjectId));
        return SubjectMapper.mapToSubjectDto(subject);
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
       List<Subject> subjects = subjectRepository.findAll();
       return subjects.stream()
                      .map((subject) -> SubjectMapper.mapToSubjectDto(subject))
                      .collect(Collectors.toList());
    }

    @Override
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {
        Subject subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID : " + subjectId));
        subject.setSubjectName(subjectDto.getSubjectName());
        Subject updateSubject = subjectRepository.save(subject);
        return SubjectMapper.mapToSubjectDto(updateSubject);
    }

    @Override
    public void deleteSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                         .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID : " + subjectId));
        List<Student> students = studentRepository.findAll();
        for(Student student : students){
            if(student.getSubjects().contains(subject)){
                student.getSubjects().remove(subject);
                studentRepository.save(student);
            }
        }
        subjectRepository.delete(subject);
    }

    @Override
    public List<SubjectDto> getAllSubjectsByStudentId(Long studentRegistrationId) {
        if(!studentRepository.existsById(studentRegistrationId)){
            throw new StudentNotFoundException("Student not found with ID : " + studentRegistrationId);
        }
        Student student = studentRepository.findById(studentRegistrationId)
                                            .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        Set<Subject> subjectSet = student.getSubjects();
        List<Subject> subjectList = new ArrayList<>(subjectSet);
        return subjectList.stream().map(subject -> SubjectMapper.mapToSubjectDto(subject)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudentsBySubjectId(Long subjectId) {
        if(!subjectRepository.existsById(subjectId)){
            throw new SubjectNotFoundException("Subject not found with ID : " + subjectId);
        }
        Subject subject = subjectRepository.findById(subjectId)
                                           .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID : " + subjectId));
        Set<Student> studentSet = subject.getStudents();
        List<Student> studentList = new ArrayList<>(studentSet);
        return studentList.stream().map(student -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
    }

    @Override
    public void deleteSubjectFromStudent(Long studentRegistrationId, Long subjectId) {
        Student student = studentRepository.findById(studentRegistrationId)
                         .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        student.removeSubject(subjectId);
        studentRepository.save(student);
    }

}
