package learning_navigator_springboot_springdatajpa.exception;

public class SubjectNotEnrolledException extends RuntimeException {

    public SubjectNotEnrolledException(){}

    public SubjectNotEnrolledException(String message) {
        super(message);
    }
}
