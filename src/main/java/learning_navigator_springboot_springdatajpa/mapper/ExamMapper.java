package learning_navigator_springboot_springdatajpa.mapper;

import learning_navigator_springboot_springdatajpa.dto.ExamDto;
import learning_navigator_springboot_springdatajpa.entity.Exam;

public class ExamMapper {

    public static Exam mapToExam(ExamDto examDto){

        Exam exam = new Exam(
            examDto.getExamId(), 
            examDto.getSubjectName(), 
            examDto.getStudents()
        );
    
        return exam;
    }

    public static ExamDto mapToExamDto(Exam exam){

        ExamDto examDto = new ExamDto(
            exam.getExamId(), 
            exam.getSubjectName(), 
            exam.getStudents()
        );
        return examDto;
        
    }
}
