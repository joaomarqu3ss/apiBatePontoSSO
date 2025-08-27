package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ServicoForaDeIntervaloException extends RuntimeException {

	@Override
	public String getMessage() {
		return "Você não pode voltar do intervalo sem antes ter feito a pausa!";
	}
}
