package br.com.artcentral.mvc.backoffice.art.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.artcentral.mvc.backoffice.art.constants.ArtStatus;
import br.com.artcentral.mvc.backoffice.art.dto.ArtPayload;
import br.com.artcentral.mvc.backoffice.art.dto.MyArtResponse;
import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import br.com.artcentral.mvc.backoffice.art.repository.ArtRepository;
import br.com.artcentral.mvc.backoffice.artimageproduct.dto.ArtImageProductPayload;
import br.com.artcentral.mvc.backoffice.artimageproduct.service.ArtImageProductService;
import br.com.artcentral.mvc.backoffice.artimagereference.dto.ArtImageReferencePayload;
import br.com.artcentral.mvc.backoffice.artimagereference.service.ArtImageReferenceService;
import br.com.artcentral.mvc.system.generics.AbstractGenericCrudService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArtService extends AbstractGenericCrudService<MyArtResponse, List<MyArtResponse>, ArtEntity, ArtRepository>{
	@Autowired
	private ArtImageReferenceService imageRefService;
	@Autowired
	private ArtImageProductService   imageProdService;
	
	public ArtService() {
		m_validaValoresNulos = true;
		m_executaAposCriacao = true;
		m_executaAposComParametro = true;
		m_errorMessageEntityNotFound = "Arte não encontrada.";
	}
	
	/** Serviços genéricos de crud */
	
	@Override
	protected ArtEntity createEntityFromPayload(Object payload) {
		return new ArtEntity((ArtPayload) payload);
	}

	@Override
	protected void updateEntityFromPayload(ArtEntity entity, Object payload) {
		ArtPayload aPayload = (ArtPayload) payload;
		entity.setArtName(aPayload.getArtName());
		entity.setHaveSchedule(aPayload.getHaveSchedule());
		if(aPayload.getHaveSchedule()) {
			entity.setStartScheduleDate(aPayload.getStartScheduleDate());
			entity.setEndScheduleDate(aPayload.getEndScheduleDate());
		}
	}

	@Override
	protected MyArtResponse convertToDto(ArtEntity entity) {
		return new MyArtResponse(entity);
	}

	@Override
	protected List<MyArtResponse> convertToListDto(List<ArtEntity> entities) {
		return Arrays.asList((MyArtResponse[]) entities.stream().map(MyArtResponse::new).toArray());
	}
	
	/**
	 * Método que executará após um insert de uma arte no sistema.
	 * A intenção desse método é inserir outras entidades filhas da entidade arte.
	 * 
	 * @param entity
	 * */
	@Override
	protected void doAfterCreateEntity(ArtEntity entity) {
		ArtPayload aPayload = (ArtPayload) m_payload;
		
		/** Salvando imagens de referência. */
		if(aPayload.getArtImageRef() != null) {			
			ArtImageReferencePayload refPayload = aPayload.getArtImageRef();
			refPayload.setArtParentid(entity);
			imageRefService.doCreate(refPayload);
		}
		
		/** Salvando imagem produto. */
		if(aPayload.getArtImageProd() != null) {
			ArtImageProductPayload prodPayload = aPayload.getArtImageProd();
			prodPayload.setArtParent(entity);
			imageProdService.doCreate(prodPayload);
		}
	}

	/** Serviços específicos */
	
	/**
	 * Realiza uma checagem na data de prazo em artes que possuem prazos
	 * */
	public void checaArtesComDataPrazo(UUID userAccountId, List<ArtEntity> arts) {
		new Thread() {
			@Override
			public void run() {
				LocalDate currentDate = LocalDate.now();
				
				arts.forEach(art -> {
					// dados
					LocalDate startDate = art.getStartScheduleDate();
					LocalDate endDate = art.getEndScheduleDate();
					// validar se a arte ja passou do prazo
					if(endDate.isAfter(currentDate)) {
						// enviar notificação privada e atualizar o status da arte para "Fora do Prazo"
						return;
					}
					
					if(endDate.equals(currentDate)) {
						// enviar notificação privada pedindo
						return;
					}
					
					long diasFaltando = ChronoUnit.DAYS.between(currentDate, endDate);
					// enviar notificação privada dizendo que faltam tantos dias para o prazo da arte.
				});
			}
		}.start();
	}
	
	public void atualizaStatus(ArtStatus statusPayload, UUID artUuid) {
		ArtEntity art = findEntityById(artUuid);
		ArtStatus artStatus = art.getArtStatus();
		
		if(artStatus.equals(ArtStatus.PROGRESS)) {
			if(!statusPayload.equals(ArtStatus.PROGRESS))
				throw new RuntimeException("Comece a fazer a arte primeiro.");
			art.setArtStatus(artStatus);
		}
		else 
			art.setArtStatus(artStatus);
		repository.save(art);
	}
}
