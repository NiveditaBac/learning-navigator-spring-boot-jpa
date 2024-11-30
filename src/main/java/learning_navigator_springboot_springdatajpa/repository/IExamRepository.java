package learning_navigator_springboot_springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning_navigator_springboot_springdatajpa.entity.Exam;

@Repository
public interface IExamRepository extends JpaRepository<Exam, Long>{

}
