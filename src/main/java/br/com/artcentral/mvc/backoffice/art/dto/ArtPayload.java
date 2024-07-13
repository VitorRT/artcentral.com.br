package br.com.artcentral.mvc.backoffice.art.dto;

import java.time.LocalDate;

import br.com.artcentral.mvc.backoffice.artimageproduct.dto.ArtImageProductPayload;
import br.com.artcentral.mvc.backoffice.artimagereference.dto.ArtImageReferencePayload;
import lombok.Data;

@Data
public class ArtPayload {
	private String artName;
	private Boolean haveSchedule;
	
	
	/** Atributos Opcionais */
	private LocalDate startScheduleDate;
	private LocalDate endScheduleDate;
	private ArtImageReferencePayload artImageRef;
	private ArtImageProductPayload artImageProd;
	
	public ArtPayload() {
		
	}
}
