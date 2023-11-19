package spring.crud.restful.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import spring.crud.restful.exception.NotFoundMemberException;
import spring.crud.restful.exception.MessageError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.text.ParseException;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ExceptionsHandler {

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<MessageError> handleException(Exception ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        messageError.setMessage(ex.getMessage());
        messageError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<MessageError> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation failed:");

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorMessage.append("\n");
            errorMessage.append("Property Path: ").append(violation.getPropertyPath()).append(", \n");
            errorMessage.append("Message Template: ").append(violation.getMessageTemplate());
        }
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        messageError.setMessage(errorMessage.toString());
        messageError.setStatus(HttpStatus.BAD_REQUEST.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(messageError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageError> handleIllegalArgumentException(IllegalArgumentException ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        messageError.setMessage(ex.getMessage());
        messageError.setStatus(HttpStatus.BAD_REQUEST.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(messageError);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<MessageError> handleParseExceptionException(ParseException ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        messageError.setMessage(ex.getMessage());
        messageError.setStatus(HttpStatus.BAD_REQUEST.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(messageError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageError> handleExceptionException(Exception ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        messageError.setMessage(ex.getMessage());
        messageError.setStatus(HttpStatus.NOT_FOUND.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(messageError);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageError> handleRuntimeExceptionException(RuntimeException ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        messageError.setStatus(HttpStatus.NOT_FOUND.value());
        messageError.setMessage(ex.getMessage());
        messageError.setTimestamp(LocalDateTime.now());
        if(ex.getMessage().contains("java.util.Date")){
            messageError.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            messageError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            messageError.setMessage("An error occurred: Invalid date format. Please provide the date in yyyy-MM-dd format.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(messageError);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(messageError);
    }

    @ExceptionHandler(NotFoundMemberException.class)
    public ResponseEntity<MessageError> handleNotFoundMemberException(NotFoundMemberException ex) {
        MessageError messageError = new MessageError();
        messageError.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        messageError.setMessage(ex.getMessage());
        messageError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        messageError.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messageError);
    }

}
