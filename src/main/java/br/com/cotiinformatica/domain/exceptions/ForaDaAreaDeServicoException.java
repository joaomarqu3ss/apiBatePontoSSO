package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ForaDaAreaDeServicoException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Você está fora da área de serviço!";
	}
}
