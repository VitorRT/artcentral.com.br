package br.com.artcentral.mvc.backoffice.useraccount.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import br.com.artcentral.mvc.backoffice.useraccount.constants.UserAccountRole;
import br.com.artcentral.mvc.backoffice.useraccount.dto.UserAccountPayload;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ac_user_accounts")
@Data
public class UserAccountEntity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "user_account_id")
	private UUID userAccountId;
	
	@Column(name = "user_account_tag", unique = true)
	private String userAccountTag;
	
	@Column(name = "user_account_name", length = 40, nullable = false)
	private String userAccountName;
	
	@Column(name = "user_account_email", length = 90, unique = true, nullable = false)
	private String userAccountEmail;
	
	@Column(name = "user_account_password", length = 190, nullable = false)
	private String userAccountPassword;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_account_role", nullable = false)
	private UserAccountRole userAccountRole;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Column(name = "user_account_is_not_blocked", nullable = false)
	private Boolean userAccountIsNotBlocked;
	
	@Column(name = "user_account_is_not_suspended", nullable = false)
	private Boolean userAccountIsNotSuspended;
	
	@Column(name = "accept_receive_emails",  nullable = false)
	private Boolean acceptReceiveEmails;

	
	// relacionamentos 
	@OneToMany(mappedBy = "userAccountParent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ArtEntity> arts;
	
	public UserAccountEntity() {
			
	}
	
	public UserAccountEntity(UserAccountPayload payload) {
		start();
		this.userAccountEmail = payload.getUserAccountEmail();
		this.userAccountName = payload.getUserAccountName();
		this.acceptReceiveEmails = payload.getAcceptReceiveEmails();
	}
	
	private void start() {
		this.createdAt = LocalDateTime.now();
		this.userAccountRole = UserAccountRole.CUSTOMER;
		this.userAccountIsNotBlocked = true;
		this.userAccountIsNotSuspended = true;
	}
}
