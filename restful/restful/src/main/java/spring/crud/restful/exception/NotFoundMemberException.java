package spring.crud.restful.exception;

public class NotFoundMemberException extends Exception{
    public NotFoundMemberException(String message) {
        super(message);
    }
}
