package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ServicoEmAbertoNotFoundException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Você não possui nenhum serviço em aberto.";
	}
}
