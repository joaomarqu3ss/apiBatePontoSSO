package br.com.cotiinformatica.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Localizacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nomePonto;
	private Double latitude;
	private Double longitude;
	private LocalDateTime timestamp;
	
	private Boolean dentroDoRaio;
	
	@ManyToOne
    @JoinColumn(name = "ponto_id")
    private Ponto ponto;
	
}
