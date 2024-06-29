package br.com.artcentral.mvc.backoffice.useraccount.dto;

import java.util.UUID;

import br.com.artcentral.mvc.backoffice.useraccount.constants.UserAccountRole;
import br.com.artcentral.mvc.backoffice.useraccount.entity.UserAccountEntity;
import lombok.Data;

@Data
public class UserAccountResponse {
	private UUID userAccountId;
	private String userAccountTag;
	private String userAccountName;
	private UserAccountRole role;
	 
	public UserAccountResponse(UserAccountEntity e) {
		this.userAccountId = e.getUserAccountId();
		this.userAccountTag = e.getUserAccountTag();
		this.userAccountName = e.getUserAccountName();
		this.role = e.getUserAccountRole();
	}
}
