package br.com.artcentral.mvc.art.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ArtPayload {
	private String artName;
	private Boolean haveSchedule;
	
	/** Atributos Opcionais */
	private LocalDate startScheduleDate;
	private LocalDate endScheduleDate;
	
	public ArtPayload() {
		
	}
}
