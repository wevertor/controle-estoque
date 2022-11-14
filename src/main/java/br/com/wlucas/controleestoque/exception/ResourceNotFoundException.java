package br.com.wlucas.controleestoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe criada para tratar exceções que ocorrem ao não encontrar o resultado
 * de uma busca.
 * 
 * @author Weverton Trindade, 13/11/2022
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3326775989913271084L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}