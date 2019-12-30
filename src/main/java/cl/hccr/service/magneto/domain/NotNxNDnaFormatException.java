package cl.hccr.service.magneto.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DNA array must be NxN")
public class NotNxNDnaFormatException extends RuntimeException {
    private static final long serialVersionUID = -5280828423933129162L;
}
