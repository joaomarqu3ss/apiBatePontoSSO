package br.com.cotiinformatica.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PontoResponse {

	private UUID id;
	private String ponto;
	private LocalDateTime InicioExpediente;
	private LocalDateTime pausaServico;
	private LocalDateTime retornoServico;
	private LocalDateTime terminoExpediente;
	private UUID usuarioId;
	
	private List<LocalizacaoResponse> localizacoes;
	
}
