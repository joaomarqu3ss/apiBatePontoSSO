package br.com.cotiinformatica.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.dtos.PontoResponse;
import br.com.cotiinformatica.domain.dtos.UltimoPontoResponse;

public interface PontoService {

	PontoResponse iniciarServico(Double latitude, Double longitude,UUID usuarioId);
	PontoResponse pausaServico(UUID id,Double latitude, Double longitude , UUID usuarioId);
	PontoResponse retornoServico(UUID id, Double latitude, Double longitude, UUID usuarioId);
	PontoResponse terminoExpediente(UUID id, Double latitude, Double longitude, UUID usuarioId);
	UltimoPontoResponse buscarPorId(UUID usuarioId);
	List<PontoResponse> buscarUltimoPonto(UUID usuarioId);
}
