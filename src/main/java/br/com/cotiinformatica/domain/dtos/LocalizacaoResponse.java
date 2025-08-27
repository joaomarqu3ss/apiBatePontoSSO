package br.com.cotiinformatica.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LocalizacaoResponse {

	private UUID id;
	private String nomePonto;
	private Double latitude;
	private Double longitude;
	private LocalDateTime timestamp;
}
