package br.com.artcentral.mvc.backoffice.artimagereference.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.artcentral.mvc.backoffice.artimagereference.dto.ArtImageReferencePayload;
import br.com.artcentral.mvc.backoffice.artimagereference.dto.ArtImageReferenceResponse;
import br.com.artcentral.mvc.backoffice.artimagereference.entity.ArtImageReferenceEntity;
import br.com.artcentral.mvc.backoffice.artimagereference.entity.ArtImageReferenceUploadType;
import br.com.artcentral.mvc.backoffice.artimagereference.repository.ArtImageReferenceRepository;
import br.com.artcentral.mvc.system.generics.AbstractGenericCrudService;

@Service
public class ArtImageReferenceService extends AbstractGenericCrudService<ArtImageReferenceResponse, List<ArtImageReferenceResponse>, ArtImageReferenceEntity, ArtImageReferenceRepository> {
	public ArtImageReferenceService() {
		m_executaAposCriacao = true;
		m_executaAposComParametro = true;
		m_errorMessageEntityNotFound = "referência de imagem da arte não encontrada.";
	}
	
	@Override
	protected ArtImageReferenceEntity createEntityFromPayload(Object payload) {
		ArtImageReferencePayload refPayload = (ArtImageReferencePayload) payload;
		ArtImageReferenceEntity entity = new ArtImageReferenceEntity();
		validaEntitdade(refPayload, entity);
		entity.setUploadType(refPayload.getUploadType());
		entity.setArtParent(refPayload.getArtParentid());
		return entity;
	} 

	@Override
	protected void updateEntityFromPayload(ArtImageReferenceEntity entity, Object payload) {
		ArtImageReferencePayload refPayload = (ArtImageReferencePayload) payload;
		validaEntitdade(refPayload, entity);
		if(refPayload.getUploadType() == ArtImageReferenceUploadType.DEVICE_UPLOAD)
			entity.setImageLink("/api/v1/art/image-ref/" + entity.getArtImageReferenceId());
		entity.setUploadType(refPayload.getUploadType());
	}

	@Override
	protected ArtImageReferenceResponse convertToDto(ArtImageReferenceEntity entity) {
		return new ArtImageReferenceResponse(entity);
	}

	@Override
	protected List<ArtImageReferenceResponse> convertToListDto(List<ArtImageReferenceEntity> entities) {
		return Arrays.asList((ArtImageReferenceResponse[]) entities.stream().map(ArtImageReferenceResponse::new).toArray());
	}
	
	/**
	 * Método que irá executar após o insert de uma referencia de imagem. 
	 * A intenção desse método é montar um link para uma referencia caso tenha sido feito
	 * o upload pelo dispositivo do cliente.
	 * 
	 * @param entity
	 * */
	@Override
	protected void doAfterCreateEntity(ArtImageReferenceEntity entity) {
		if(entity.getUploadType() == ArtImageReferenceUploadType.DEVICE_UPLOAD) {
			entity.setImageLink("/api/v1/art/image-ref/" + entity.getArtImageReferenceId()); 
			repository.save(entity);
		}		
	}
	
	/** métodos específicos */
	/**
	 * Retorna os bytes da imagem referência de uma arte.
	 * */
	public byte[] getImageReferenceBytes(UUID refId) {
		return super.findEntityById(refId).getImageBytes();
	}
	
	public List<ArtImageReferenceResponse> getAllArtImageReferencesOfSpecifyArt(UUID artId) {
		return Arrays.asList((ArtImageReferenceResponse[]) repository.findAllArtImageReferencesByArtId(artId)
				.stream()
				.map(ArtImageReferenceResponse::new)
				.toArray());
	}
	
	/** métodos privados */
	private void validaEntitdade(ArtImageReferencePayload payload, ArtImageReferenceEntity entity) {
		if(payload.getUploadType() == ArtImageReferenceUploadType.DEVICE_UPLOAD && payload.getImageBytes() == null) 
			throw new RuntimeException("Por favor faça o upload da imagem de seus dispositivo!");
		else {
			entity.setImageBytes(payload.getImageBytes());
		}
		
		if(payload.getUploadType() == ArtImageReferenceUploadType.PINTEREST_API && payload.getImageLink() == null) 
			throw new RuntimeException("Por favor informe o link da imagem!");
		else {
			entity.setImageLink(payload.getImageLink());
			entity.setImageBytes(null);
		}
	}
}
