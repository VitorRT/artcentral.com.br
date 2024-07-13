package br.com.artcentral.mvc.backoffice.artimageproduct.dto;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import lombok.Data;

@Data
public class ArtImageProductPayload {
	private byte[] imageBytes;
	private ArtEntity artParent;
}
