package br.com.cotiinformatica.domain.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.dtos.LocalizacaoResponse;
import br.com.cotiinformatica.domain.dtos.PontoResponse;
import br.com.cotiinformatica.domain.dtos.UltimoPontoResponse;
import br.com.cotiinformatica.domain.entities.Localizacao;
import br.com.cotiinformatica.domain.entities.Ponto;
import br.com.cotiinformatica.domain.enums.Points;
import br.com.cotiinformatica.domain.exceptions.ForaDaAreaDeServicoException;
import br.com.cotiinformatica.domain.exceptions.ServicoEmAbertoNotFoundException;
import br.com.cotiinformatica.domain.exceptions.ServicoEmAbertoException;
import br.com.cotiinformatica.domain.exceptions.ServicoForaDeIntervaloException;
import br.com.cotiinformatica.domain.exceptions.ServicoNaoEncontradoException;
import br.com.cotiinformatica.domain.interfaces.PontoService;
import br.com.cotiinformatica.repositories.LocalizacaoRepository;
import br.com.cotiinformatica.repositories.PontoRepository;

@Service
public class PontoServiceImpl implements PontoService {

	@Autowired PontoRepository pontoRepository;
	@Autowired LocalizacaoRepository localizacaoRepository;
	
	@Override
	public PontoResponse iniciarServico(Double latitude, Double longitude,UUID usuarioId) {
		
		if(pontoRepository.existsRegistroAberto(usuarioId)) {
			throw new ServicoEmAbertoException();
		}
		
		double referenciaLat = -22.809346571846664;
		double referenciaLon = -43.41841085248241;
		double raio = 200;
		
		double distancia = calcularDistancia(latitude, longitude, referenciaLat, referenciaLon);
		if(distancia > raio) {
			throw new ForaDaAreaDeServicoException();
		}
		
		var ponto = new Ponto();
		ponto.setPontos(Points.ENTRADA);
		ponto.setInicioExpediente(LocalDateTime.now());
		ponto.setUsuarioId(usuarioId);
		
		pontoRepository.save(ponto);
		var loc = new Localizacao();
		loc.setPonto(ponto);
		loc.setNomePonto("ENTRADA");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setTimestamp(LocalDateTime.now());
		loc.setDentroDoRaio(true);
		
		localizacaoRepository.save(loc);
		
		ponto.getLocalizacoes().add(loc);
			
		return toResponse(ponto);
	}

	@Override
	public PontoResponse pausaServico(UUID id,Double latitude, Double longitude, UUID usuarioId) {
		
		var ponto = pontoRepository.findByIdAndUsuarioId(id, usuarioId);
		if(ponto == null) {
			throw new ServicoNaoEncontradoException();
		}
		
		double referenciaLat = -22.809346571846664;
		double referenciaLon = -43.41841085248241;
		double raio = 200;
		
		double distancia = calcularDistancia(latitude, longitude, referenciaLat, referenciaLon);
		if(distancia > raio) {
			throw new ForaDaAreaDeServicoException();
		}
		
		ponto.setPontos(Points.ENTRADA_ALMOCO);
		ponto.setPausaServico(LocalDateTime.now());
		ponto.setUsuarioId(usuarioId);
		
		pontoRepository.save(ponto);
		
		var loc = new Localizacao();
		loc.setPonto(ponto);
		loc.setNomePonto("PAUSA");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setTimestamp(LocalDateTime.now());
		loc.setDentroDoRaio(true);
		
		localizacaoRepository.save(loc);

		ponto.getLocalizacoes().add(loc);
		
		return toResponse(ponto);
	}

	@Override
	public PontoResponse retornoServico(UUID id,Double latitude, Double longitude, UUID usuarioId) {
		
		var ponto = pontoRepository.findByIdAndUsuarioId(id, usuarioId);
		if(ponto == null) {
			throw new ServicoNaoEncontradoException();
			                                        
		}
		
		if(pontoRepository.falsoIntervalo(usuarioId)) {
			throw new ServicoForaDeIntervaloException();
		}
		
		double referenciaLat = -22.809346571846664;
		double referenciaLon = -43.41841085248241;
		double raio = 200;
		
		double distancia = calcularDistancia(latitude, longitude, referenciaLat, referenciaLon);
		if(distancia > raio) {
			throw new ForaDaAreaDeServicoException();
		}
		
		ponto.setPontos(Points.SAIDA_ALMOCO);
		ponto.setRetornoServico(LocalDateTime.now());
		ponto.setUsuarioId(usuarioId);
		
		pontoRepository.save(ponto);
		
		var loc = new Localizacao();
		loc.setPonto(ponto);
		loc.setNomePonto("RETORNO");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setTimestamp(LocalDateTime.now());
		loc.setDentroDoRaio(true);
		
		localizacaoRepository.save(loc);
		
		ponto.getLocalizacoes().add(loc);		
		
		return toResponse(ponto);
	}

	@Override
	public PontoResponse terminoExpediente(UUID id,Double latitude, Double longitude, UUID usuarioId) {
		
		var ponto = pontoRepository.findByIdAndUsuarioId(id, usuarioId);
		if(ponto == null) {
			throw new ServicoNaoEncontradoException();
		}
		
		double referenciaLat = -22.809346571846664;
		double referenciaLon = -43.41841085248241;
		double raio = 200;
		
		double distancia = calcularDistancia(latitude, longitude, referenciaLat, referenciaLon);
		if(distancia > raio) {
			throw new ForaDaAreaDeServicoException();
		}
		
		ponto.setPontos(Points.SAIDA);
		ponto.setTerminoExpediente(LocalDateTime.now());
		ponto.setUsuarioId(usuarioId);
		
		pontoRepository.save(ponto);
		
		var loc = new Localizacao();
		loc.setPonto(ponto);
		loc.setNomePonto("SAIDA");
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setTimestamp(LocalDateTime.now());
		loc.setDentroDoRaio(true);
		
		localizacaoRepository.save(loc);
		
		ponto.getLocalizacoes().add(loc);		
		
		return toResponse(ponto);
	}
	
	@Override
	public UltimoPontoResponse buscarPorId(UUID usuarioId) {
		
		var ponto = pontoRepository.registroAberto(usuarioId);
		
		if(ponto == null) {
			throw new ServicoEmAbertoNotFoundException();
		}
		
		var response = new UltimoPontoResponse();
		response.setId(ponto.getId());
		
		return response;
	}
		
	
	@Override
	public List<PontoResponse> buscarUltimoPonto(UUID usuarioId) {
		
		var pontos = pontoRepository.findAll();
		
		if(pontos == null) {
			return null;
		}
		return pontos.stream()
				.sorted((p1, p2) -> p2.getInicioExpediente().compareTo(p1.getInicioExpediente()))
				.map(this::toResponse)
				.toList();
	} 
	
	private PontoResponse toResponse(Ponto ponto) {
		
		var localizacoes = ponto.getLocalizacoes().stream()
	            .map(loc -> new LocalizacaoResponse(
	                    loc.getId(),
	                    loc.getNomePonto(),
	                    loc.getLatitude(),
	                    loc.getLongitude(),
	                    loc.getTimestamp()
	            )).sorted((l1, l2) -> l2.getTimestamp().compareTo(l1.getTimestamp()))
	            .toList();
		 
		 return new PontoResponse(
		            ponto.getId(),
		            ponto.getPontos().name(),
		            ponto.getInicioExpediente(),
		            ponto.getPausaServico(),
		            ponto.getRetornoServico(),
		            ponto.getTerminoExpediente(),
		            ponto.getUsuarioId(),
		            localizacoes
		    );
		
	}
	
	private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
	    final int R = 6371000; 
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) *
	               Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLon/2) * Math.sin(dLon/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    return R * c;
	}

}
