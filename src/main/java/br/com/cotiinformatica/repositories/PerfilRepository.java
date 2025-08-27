package br.com.cotiinformatica.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
	
	@Query("""
			SELECT p FROM Perfil p 
			WHERE p.usuarioId = :usuarioId 
			ORDER BY p.timestamp DESC
			""")
	List<Perfil> findUltimaFotoByUsuario(@Param("usuarioId") UUID usuarioId, Pageable pageable);
	
}
