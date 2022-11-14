package br.com.wlucas.controleestoque.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe que mapeia uma resposta a uma exceção.
 * 
 * @author Weverton Trindade, 14/11/2022
 *
 */
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -8403004552608817446L;

	private Date timestamp;
	private String mensagem;
	private String detalhes;

	public ExceptionResponse(Date timestamp, String mensagem, String detalhes) {
		super();
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
