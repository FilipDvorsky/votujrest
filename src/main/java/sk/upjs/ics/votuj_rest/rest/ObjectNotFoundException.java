package sk.upjs.ics.votuj_rest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2435008073282668843L;

	
	public ObjectNotFoundException() {
		super();
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}

}
