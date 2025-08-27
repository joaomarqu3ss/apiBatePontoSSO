package br.com.cotiinformatica.domain.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.cotiinformatica.domain.dtos.PerfilResponse;
import br.com.cotiinformatica.domain.entities.Perfil;
import br.com.cotiinformatica.domain.interfaces.PerfilService;
import br.com.cotiinformatica.repositories.PerfilRepository;

@Service
public class PerfilServiceImpl implements PerfilService {

	private static String caminhoImagem = "C:/Users/jpedr/Desktop/imgsAppBatePonto/";
	
	@Autowired PerfilRepository perfilRepository;
	
	@Override
	public PerfilResponse uploadFoto(MultipartFile file, UUID usuarioId) {
		
		try {
			
			var perfil = new Perfil();
			
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path caminhoArquivo = Paths.get(caminhoImagem + usuarioId.toString() + file.getOriginalFilename());
				Files.write(caminhoArquivo, bytes);
				
				perfil.setNomeImagem(file.getOriginalFilename());
				perfil.setUsuarioId(usuarioId);
				perfil.setTimestamp(LocalDateTime.now());
				
				perfilRepository.save(perfil);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		var response = new PerfilResponse();
		
		response.setStatus(HttpStatus.OK.value());
		response.setMensagem("Perfil atualizado com sucesso.");
		
		return response;
	}
	
	@Override
	public byte[] getFoto(UUID usuarioId) {
		
		try {
			var perfil = perfilRepository.findUltimaFotoByUsuario(usuarioId, PageRequest.of(0,1))
					.stream()
					.findFirst()
					.orElse(null);
			
			if(perfil != null) {
				Path caminhoArquivo = Paths.get(caminhoImagem + usuarioId.toString() + perfil.getNomeImagem());
				return Files.readAllBytes(caminhoArquivo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
