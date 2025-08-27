package br.com.cotiinformatica.domain.interfaces;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.domain.dtos.PerfilResponse;

public interface PerfilService {

	PerfilResponse uploadFoto(MultipartFile file, UUID usuarioId);
	byte[] getFoto(UUID usuarioId);
	
}
