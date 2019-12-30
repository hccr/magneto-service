package cl.hccr.service.magneto.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DNA array lenght must be greater than 3 and lower or equal than 64")
public class DnaSizeException extends RuntimeException  {
    private static final long serialVersionUID = -8858546877017771471L;
}
