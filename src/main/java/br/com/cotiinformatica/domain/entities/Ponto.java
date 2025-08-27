package br.com.cotiinformatica.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.domain.enums.Points;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Ponto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private Points pontos;
	private LocalDateTime inicioExpediente;
	private LocalDateTime pausaServico;
	private LocalDateTime retornoServico;
	private LocalDateTime terminoExpediente;
	private UUID usuarioId;
	
	@OneToMany(mappedBy = "ponto")
    private List<Localizacao> localizacoes = new ArrayList<>();
	

	
}
