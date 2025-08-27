package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ServicoEmAbertoException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Serviço em aberto. Por favor, finalize o serviço antes de iniciar um novo.";
	}
}
