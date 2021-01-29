package ee.bcs.valiit.tasks;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BankException.class)
    public ResponseEntity<ErrorResponse> handleError (BankException e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError (Exception e){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

}
