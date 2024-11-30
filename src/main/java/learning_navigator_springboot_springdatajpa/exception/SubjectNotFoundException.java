package learning_navigator_springboot_springdatajpa.exception;

public class SubjectNotFoundException extends RuntimeException{

    public SubjectNotFoundException(){}

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
