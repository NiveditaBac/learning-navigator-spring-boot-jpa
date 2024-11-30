package learning_navigator_springboot_springdatajpa.mapper;

import learning_navigator_springboot_springdatajpa.dto.SubjectDto;
import learning_navigator_springboot_springdatajpa.entity.Subject;

public class SubjectMapper {

    public static Subject mapToSubject(SubjectDto subjectDto){

        Subject subject = new Subject(
            subjectDto.getSubjectId(),
            subjectDto.getSubjectName(),
            subjectDto.getStudents()
        );
    
        return subject;
    }

    public static SubjectDto mapToSubjectDto(Subject subject){

        SubjectDto subjectDto = new SubjectDto(
            subject.getSubjectId(),
            subject.getSubjectName(),
            subject.getStudents()
        );
        return subjectDto;
        
    }
}
