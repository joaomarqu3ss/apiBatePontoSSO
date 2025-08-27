package br.com.cotiinformatica.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.domain.dtos.PerfilResponse;
import br.com.cotiinformatica.domain.interfaces.PerfilService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/perfil")
public class PerfilController {
	
	@Autowired PerfilService perfilService;
	@Autowired JwtTokenComponent jwtTokenComponent;

	@PostMapping("/atualizarFoto")
	public ResponseEntity<PerfilResponse> cadastrarFoto(@RequestParam MultipartFile file, HttpServletRequest http) {
		
		return ResponseEntity.ok(perfilService.uploadFoto(file, getUsuarioId(http)));
	}
	
	public UUID getUsuarioId(HttpServletRequest http) {
		var token = http.getHeader("Authorization")
				.replace("Bearer", "");
		return jwtTokenComponent.getIdFromToken(token);
	}
	
	@GetMapping("/obtendoFotoUsuario")
	@ResponseBody
	public ResponseEntity<byte[]> obterFoto(HttpServletRequest http) {
		return ResponseEntity.ok(perfilService.getFoto(getUsuarioId(http)));
	}
}
