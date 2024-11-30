package learning_navigator_springboot_springdatajpa.mapper;

import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.entity.Student;

public class StudentMapper {

    public static Student mapToStudent(StudentDto studentDto){

        Student student = new Student(
           studentDto.getStudentRegistrationId(),
           studentDto.getStudentName(),
           studentDto.getSubjects(),
           studentDto.getExams()
        );
    
        return student;
    }

    public static StudentDto mapToStudentDto(Student student){

        StudentDto studentDto = new StudentDto(
            student.getStudentRegistrationId(),
            student.getStudentName(),
            student.getSubjects(),
            student.getExams()
        );
        return studentDto;
        
    }
}
