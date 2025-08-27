package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.entities.Localizacao;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, UUID> { 

}
