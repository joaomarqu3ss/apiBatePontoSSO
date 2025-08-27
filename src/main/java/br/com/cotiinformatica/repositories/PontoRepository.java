package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Ponto;

@Repository
public interface PontoRepository extends JpaRepository<Ponto, UUID> {

	 Ponto findByIdAndUsuarioId(UUID id, UUID usuarioId);
	 
	 
	@Query("""
		       SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
		       FROM Ponto p
		       WHERE p.usuarioId = :usuarioId
		         AND p.inicioExpediente IS NOT NULL
		         AND p.terminoExpediente IS NULL
		       """)
		boolean existsRegistroAberto(@Param("usuarioId") UUID usuarioId);
		
		@Query("""
		       SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END
		       FROM Ponto p
		       WHERE p.usuarioId = :usuarioId
		         AND p.inicioExpediente IS NOT NULL
		         AND p.pausaServico IS NULL
		       """)
		boolean falsoIntervalo(UUID usuarioId);
		
		@Query("""
		       SELECT p
		       FROM Ponto p
		       WHERE p.usuarioId = :usuarioId
		         AND p.inicioExpediente IS NOT NULL
		         AND p.terminoExpediente IS NULL
		       """)
		Ponto registroAberto(UUID usuarioId);
}
