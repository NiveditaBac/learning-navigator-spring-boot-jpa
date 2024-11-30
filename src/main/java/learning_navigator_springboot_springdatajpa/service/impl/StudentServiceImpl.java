package learning_navigator_springboot_springdatajpa.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.entity.Student;
import learning_navigator_springboot_springdatajpa.exception.StudentNotFoundException;
import learning_navigator_springboot_springdatajpa.mapper.StudentMapper;
import learning_navigator_springboot_springdatajpa.repository.IStudentRepository;
import learning_navigator_springboot_springdatajpa.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public StudentDto registerStudent(StudentDto studentDto) {
  
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);

    }

    @Override
    public StudentDto getStudentByRegistrationId(Long studentRegistrationId) {
        Student student = studentRepository
                .findById(studentRegistrationId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
       List<Student> students = studentRepository.findAll();
       return students.stream()
                      .map((student) -> StudentMapper.mapToStudentDto(student))
                      .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentRegistrationId, StudentDto studentDto) {
        Student student = studentRepository
                .findById(studentRegistrationId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID : " + studentRegistrationId));
        student.setStudentName(studentDto.getStudentName());
        Student updateStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);
    }
    
    @Override
    public void deleteStudentById(Long studentRegistrationId) {
        studentRepository.findById(studentRegistrationId)
                         .orElseThrow(() -> new StudentNotFoundException(
                         "Student not found with ID : " + studentRegistrationId));
        studentRepository.deleteById(studentRegistrationId);

    }
}   
