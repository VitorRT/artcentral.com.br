package br.com.artcentral.mvc.backoffice.artimagereference.dto;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import br.com.artcentral.mvc.backoffice.artimagereference.entity.ArtImageReferenceUploadType;
import lombok.Data;

@Data
public class ArtImageReferencePayload {
	private ArtImageReferenceUploadType uploadType;
	private ArtEntity artParentid;
	
	/** Atributos opcionais */
	private byte[] imageBytes;
	private String imageLink;

	public ArtImageReferencePayload() {
		
	}
}
