package br.com.wlucas.controleestoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe criada para tratar exceções que ocorrem ao tentar persistir um objeto nulo.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

	private static final long serialVersionUID = -3326775989913271084L;

	public RequiredObjectIsNullException() {
		super("Não é permitido persistir um objeto nulo.");
	}
	
	public RequiredObjectIsNullException(String message) {
		super(message);
	}
}
