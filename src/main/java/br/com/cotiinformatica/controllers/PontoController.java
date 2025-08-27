package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.domain.dtos.PontoResponse;
import br.com.cotiinformatica.domain.dtos.UltimoPontoResponse;
import br.com.cotiinformatica.domain.exceptions.CargoNaoAutorizadoException;
import br.com.cotiinformatica.domain.interfaces.PontoService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/pontos")
public class PontoController {

	@Autowired PontoService pontoService;
	@Autowired JwtTokenComponent jwtTokenComponent;
	
	@PostMapping("/iniciar")
	public ResponseEntity<PontoResponse> iniciarServico(@RequestParam Double latitude,
			@RequestParam Double longitude, HttpServletRequest http) {
		
		return ResponseEntity.ok(pontoService.iniciarServico
				(latitude,longitude, getUsuarioId(http)));
	}
	
	@PutMapping("/pausar/{id}")
	public ResponseEntity<PontoResponse> pausarServico(@PathVariable UUID id,
			@RequestParam Double latitude,
			@RequestParam Double longitude, HttpServletRequest http) {
		
		return ResponseEntity.ok(pontoService.pausaServico
				(id,latitude,longitude,  getUsuarioId(http)));
	}
	
	@PutMapping("/retornar/{id}")
	public ResponseEntity<PontoResponse> retornarServico(@PathVariable UUID id,
			@RequestParam Double latitude,
			@RequestParam Double longitude, HttpServletRequest http) {
		
		return ResponseEntity.ok(pontoService.retornoServico
				(id, latitude,longitude, getUsuarioId(http)));
	}
	
	@PutMapping("/terminar/{id}")
	public ResponseEntity<PontoResponse> terminarExpediente(@PathVariable UUID id, 
			@RequestParam Double latitude,
			@RequestParam Double longitude,HttpServletRequest http) {
		
		return ResponseEntity.ok(pontoService.terminoExpediente
				(id, latitude,longitude , getUsuarioId(http)));
	}
	
	@GetMapping("/abertos")
	public ResponseEntity<UltimoPontoResponse> buscarServicoEmAberto(HttpServletRequest http) {
		
		return ResponseEntity.ok(pontoService.buscarPorId(getUsuarioId(http)));
	}
	
	@GetMapping("/ultimo")
	public ResponseEntity<List<PontoResponse>> buscarUltimoPonto(HttpServletRequest http) {
		
		var cargo = getUsuarioCargo(http);
		if(!"Gestor".equalsIgnoreCase(cargo)) {
			throw new CargoNaoAutorizadoException();
		}
		
		return ResponseEntity.ok(pontoService.buscarUltimoPonto(getUsuarioId(http)));
	}
	
	public UUID getUsuarioId(HttpServletRequest http) {
		var token = http.getHeader("Authorization")
				.replace("Bearer", "");
		return jwtTokenComponent.getIdFromToken(token);
	}
	
	public String getUsuarioCargo(HttpServletRequest http) {
		var token = http.getHeader("Authorization")
				.replace("Bearer", "");
		return jwtTokenComponent.getCargoFromToken(token);
	}
	
}
