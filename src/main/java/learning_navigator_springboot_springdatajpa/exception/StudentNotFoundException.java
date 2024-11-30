package learning_navigator_springboot_springdatajpa.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(){}

    public StudentNotFoundException(String message) {
        super(message);
    }

}
