package learning_navigator_springboot_springdatajpa.dto;

import java.util.HashSet;
import java.util.Set;

import learning_navigator_springboot_springdatajpa.entity.Exam;
import learning_navigator_springboot_springdatajpa.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    
    private Long studentRegistrationId;
    private String studentName;
    private Set<Subject> subjects = new HashSet<>();
    private Set<Exam> exams = new HashSet<>();  

}
