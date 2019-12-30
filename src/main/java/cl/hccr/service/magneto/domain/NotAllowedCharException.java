package cl.hccr.service.magneto.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DNA must contain only ACGT characters")
public class NotAllowedCharException extends RuntimeException {
    private static final long serialVersionUID = -1820612911637158286L;
}
