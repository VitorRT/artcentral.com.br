package br.com.artcentral.mvc.backoffice.useraccount.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountListResponse;
import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountPayload;
import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountResponse;
import br.com.artcentral.mvc.backoffice.useraccount.entity.UserAccountEntity;
import br.com.artcentral.mvc.backoffice.useraccount.repository.UserAccountRepository;
import br.com.artcentral.mvc.system.generics.AbstractGenericCrudService;

@Service
public class UserAccountService extends AbstractGenericCrudService<UserAccountResponse, List<UserAccountListResponse>, UserAccountEntity, UserAccountRepository>{

	public UserAccountService() {
		// configuração da classe genérica crud...
		m_validaValoresNulos = true;
		m_errorMessageEntityNotFound = "Conta de usuário não encontrada.";
	}
	
	@Override
	protected UserAccountEntity createEntityFromPayload(Object payload) {
		UserAccountPayload uPayload = (UserAccountPayload) payload;
		UserAccountEntity entity = new UserAccountEntity(uPayload);
		entity.setUserAccountTag(uPayload.getUserAccountTag());
		entity.setUserAccountPassword(uPayload.getUserAccountPassword());
		return entity;
	}

	@Override
	protected void updateEntityFromPayload(UserAccountEntity entity, Object payload) {
		// nada.
	}

	@Override
	protected UserAccountResponse convertToDto(UserAccountEntity entity) {
		return new UserAccountResponse(entity);
	}

	@Override
	protected List<UserAccountListResponse> convertToListDto(List<UserAccountEntity> entities) {
		return  Arrays.asList((UserAccountListResponse[]) entities.stream().map(UserAccountListResponse::new).toArray());
	}

}
