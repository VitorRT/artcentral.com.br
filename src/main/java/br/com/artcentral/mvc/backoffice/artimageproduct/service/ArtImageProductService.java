package br.com.artcentral.mvc.backoffice.artimageproduct.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.artcentral.mvc.backoffice.artimageproduct.dto.ArtImageProductPayload;
import br.com.artcentral.mvc.backoffice.artimageproduct.entity.ArtImageProductEntity;
import br.com.artcentral.mvc.backoffice.artimageproduct.entity.ArtImageProductResponse;
import br.com.artcentral.mvc.backoffice.artimageproduct.repository.ArtImageProductRepository;
import br.com.artcentral.mvc.system.generics.AbstractGenericCrudService;

@Service
public class ArtImageProductService extends AbstractGenericCrudService<ArtImageProductResponse, List<ArtImageProductResponse>, ArtImageProductEntity, ArtImageProductRepository>{
	public ArtImageProductService() {
		m_executaAposCriacao = true;
		m_executaAposComParametro = true;
		m_errorMessageEntityNotFound = "Imagem da arte não encontrada.";
	}
	
	@Override
	protected ArtImageProductEntity createEntityFromPayload(Object payload) {
		ArtImageProductPayload prodPayload = (ArtImageProductPayload) payload;
		ArtImageProductEntity entity = new ArtImageProductEntity();
		entity.setImageBytes(prodPayload.getImageBytes());
		entity.setArtParent(prodPayload.getArtParent());
		return entity;
	}

	@Override
	protected void updateEntityFromPayload(ArtImageProductEntity entity, Object payload) {
		ArtImageProductPayload prodPayload = (ArtImageProductPayload) payload;
		entity.setImageBytes(prodPayload.getImageBytes());
		entity.setImageLink("/api/v1/art/image-product/" + entity.getArtImageProductId());
	}

	@Override
	protected ArtImageProductResponse convertToDto(ArtImageProductEntity entity) {
		return new ArtImageProductResponse(entity);
	}

	@Override
	protected List<ArtImageProductResponse> convertToListDto(List<ArtImageProductEntity> entities) {
		return Arrays.asList((ArtImageProductResponse[]) entities.stream().map(ArtImageProductResponse::new).toArray());
	}
	
	/**
	 * Método que executará apos um insert de uma imagem produto de uma arte no sistema.
	 * A intenção desse método é montar o link da imagem de acordo com seu id
	 * 
	 * @param entity
	 * */
	@Override
	protected void doAfterCreateEntity(ArtImageProductEntity entity) {
		entity.setImageLink("/api/v1/art/image-product/" + entity.getArtImageProductId());
	}
	
	/** métodos específicos */
	public byte[] getArtImageProdutBytes(UUID prodId) {
		return super.findEntityById(prodId).getImageBytes();
	}
	
}
