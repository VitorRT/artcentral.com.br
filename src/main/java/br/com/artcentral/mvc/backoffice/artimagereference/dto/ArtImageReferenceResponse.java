package br.com.artcentral.mvc.backoffice.artimagereference.dto;

import java.util.UUID;

import br.com.artcentral.mvc.backoffice.artimagereference.entity.ArtImageReferenceEntity;
import lombok.Data;

@Data
public class ArtImageReferenceResponse {
	private UUID artImageRefId;
	private String imageLink;
	
	public ArtImageReferenceResponse(ArtImageReferenceEntity entity) {
		this.artImageRefId = entity.getArtImageReferenceId();
		this.imageLink = entity.getImageLink();
	}
}
