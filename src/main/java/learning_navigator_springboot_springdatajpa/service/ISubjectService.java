package learning_navigator_springboot_springdatajpa.service;

import java.util.List;

import learning_navigator_springboot_springdatajpa.dto.StudentDto;
import learning_navigator_springboot_springdatajpa.dto.SubjectDto;

public interface ISubjectService {

    SubjectDto addSubject(Long studentRegistrationId, SubjectDto subjectDto);

    SubjectDto getSubjectById(Long subjectId);

    List<SubjectDto> getAllSubjects();

    SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto);

    void deleteSubjectById(Long subjectId);

    List<SubjectDto> getAllSubjectsByStudentId(Long studentRegistrationId);

    List<StudentDto> getAllStudentsBySubjectId(Long subjectId);

    void deleteSubjectFromStudent(Long studentRegistrationId, Long subjectId);

}
