package br.com.cotiinformatica.domain.exceptions;

@SuppressWarnings("serial")
public class ServicoNaoEncontradoException extends RuntimeException {
	
	@Override
	public String getMessage() {
		return "Serviço não encontrado. Por favor inicie o expediente.";
	}
}
