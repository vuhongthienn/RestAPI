package spring.crud.restful.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class MessageError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
