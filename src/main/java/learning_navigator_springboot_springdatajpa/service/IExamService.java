package learning_navigator_springboot_springdatajpa.service;

import java.util.List;


import learning_navigator_springboot_springdatajpa.dto.ExamDto;
import learning_navigator_springboot_springdatajpa.dto.StudentDto;

public interface IExamService {

    ExamDto registerExam(Long studentRegistrationId, ExamDto examDto);

    ExamDto getExamById(Long examId);

    List<ExamDto> getAllExams();

    ExamDto updateExam(Long examId, ExamDto examDto);

    void deleteExamById(Long examId);

    List<ExamDto> getAllExamsByStudentId(Long studentRegistrationId);

    List<StudentDto> getAllStudentsByExamId(Long examId);

    void deleteExamFromStudent(Long studentRegistrationId, Long examId);

}
