package learning_navigator_springboot_springdatajpa.exception;

public class DuplicateEntryExamException extends RuntimeException{
    
    public DuplicateEntryExamException(){}

    public DuplicateEntryExamException(String message) {
        super(message);
    }
}
