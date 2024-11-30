package learning_navigator_springboot_springdatajpa.dto;

import java.util.HashSet;
import java.util.Set;

import learning_navigator_springboot_springdatajpa.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    
    private Long examId;
    private String subjectName;
    private Set<Student> students = new HashSet<>();

}
