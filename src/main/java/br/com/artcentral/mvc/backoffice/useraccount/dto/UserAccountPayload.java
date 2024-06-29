package br.com.artcentral.mvc.backoffice.useraccount.dto;

import br.com.artcentral.mvc.system.generics.ValidatesNull;
import lombok.Data;

@Data
public class UserAccountPayload {
	/**
	 * Parâmetros Obrigatórios
	 * */
	
	@ValidatesNull
	private String userAccountTag;
	@ValidatesNull
	private String userAccountEmail;
	@ValidatesNull
	private String userAccountName;
	@ValidatesNull
	private String userAccountPassword;
	@ValidatesNull
	private Boolean acceptReceiveEmails;
	@ValidatesNull
	private Boolean acceptTermsOfUse;
	
	public UserAccountPayload() {
		
	}
}
