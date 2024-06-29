package br.com.artcentral.mvc.backoffice.useraccount.dto;

import java.util.UUID;

import br.com.artcentral.mvc.backoffice.useraccount.entity.UserAccountEntity;
import lombok.Data;

@Data
public class UserAccountListResponse {
	private UUID userAccountId;
	private String userAccountTag;
	
	public UserAccountListResponse(UserAccountEntity e) {
		this.userAccountId = e.getUserAccountId();
		this.userAccountTag = e.getUserAccountTag();
	}
}
