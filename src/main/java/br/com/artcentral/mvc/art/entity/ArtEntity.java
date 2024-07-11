package br.com.artcentral.mvc.art.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.artcentral.mvc.art.constants.ArtLevel;
import br.com.artcentral.mvc.art.constants.ArtStatus;
import br.com.artcentral.mvc.art.dto.ArtPayload;
import br.com.artcentral.mvc.backoffice.useraccount.entity.UserAccountEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "ac_arts")
@Data
public class ArtEntity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "art_id")
	private UUID artId;
	
	@Column(name = "art_name", length = 80, nullable = false)
	private String artName;
	
	@Column(name = "art_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ArtStatus artStatus;
	
	@Column(name = "art_level", nullable = false)
	@Enumerated(EnumType.STRING)
	private ArtLevel artLevel; // nível da arte: Pública, Privada, Não listado, etc.
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(name = "have_schedule", nullable = false)
	private Boolean haveSchedule;
	
	@Column(name = "start_schedule_date")
	private LocalDate startScheduleDate; 
	
	@Column(name = "end_schedule_date")
	private LocalDate endScheduleDate;
	
	
	// relacionamento
	@ManyToOne
	@JoinColumn(name = "user_account_parent_fk")
	private UserAccountEntity userAccountParent;
	
	public ArtEntity() {
		
	}
	
	public ArtEntity(ArtPayload payload) {
		start();
		this.artName = payload.getArtName();
		this.haveSchedule = payload.getHaveSchedule();
		if(haveSchedule) {
			this.startScheduleDate = payload.getStartScheduleDate();
			this.endScheduleDate = payload.getEndScheduleDate();
		}
	}
	
	private void start() {
		this.artLevel = ArtLevel.PRIVATE;
		this.artStatus = ArtStatus.TODO;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}
