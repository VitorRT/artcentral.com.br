package br.com.artcentral.mvc.backoffice.artimageproduct.entity;

import java.util.UUID;

import lombok.Data;

@Data
public class ArtImageProductResponse {
	private UUID artImageProductId;
	private String imageLink;
	
	public ArtImageProductResponse(ArtImageProductEntity entity) {
		this.artImageProductId = entity.getArtImageProductId();
		this.imageLink = entity.getImageLink();
	}
}
