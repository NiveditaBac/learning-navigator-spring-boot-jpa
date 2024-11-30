package learning_navigator_springboot_springdatajpa.service;

import java.util.List;

import learning_navigator_springboot_springdatajpa.dto.StudentDto;

public interface IStudentService {

    StudentDto registerStudent(StudentDto studentDto);

    StudentDto getStudentByRegistrationId(Long studentRegistrationId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentRegistrationId, StudentDto studentDto);

    void deleteStudentById(Long studentRegistrationId);
}
