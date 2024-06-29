package br.com.artcentral.mvc.backoffice.useraccount.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.artcentral.mvc.backoffice.useraccount.constants.UserAccountRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ac_user_accounts")
@Data
public class UserAccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID userAccountId;
	
	@Column(name = "user_account_tag", unique = true)
	private String userAccountTag;
	
	@Column(name = "user_account_name", length = 40)
	private String userAccountName;
	
	@Column(name = "user_account_email", length = 90)
	private String userAccountEmail;
	
	@Column(name = "user_account_password", length = 190)
	private String userAccountPassword;
	
	@Enumerated(EnumType.STRING)
	private UserAccountRole userAccountRole;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "user_account_is_not_blocked")
	private Boolean userAccountIsNotBlocked;
	
	@Column(name = "user_account_is_not_suspended")
	private Boolean userAccountIsNotSuspended;

	public UserAccountEntity() {
		start();
	}
	
	private void start() {
		this.createdAt = LocalDateTime.now();
		this.userAccountRole = UserAccountRole.CUSTOMER;
		this.userAccountIsNotBlocked = true;
		this.userAccountIsNotSuspended = true;
	}
}
