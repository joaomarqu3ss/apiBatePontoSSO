package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class CargoNaoAutorizadoException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Ação não permitida para o cargo do usuário." ;
	}
}
