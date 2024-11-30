package learning_navigator_springboot_springdatajpa.exception;

public class ExamNotFoundException extends RuntimeException{

    public ExamNotFoundException(){}

    public ExamNotFoundException(String message) {
        super(message);
    }
}
