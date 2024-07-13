package br.com.artcentral.mvc.backoffice.artimagereference.entity;

import java.util.UUID;

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

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import lombok.Data;

@Entity
@Table(name = "ac_art_image_references")
@Data
public class ArtImageReferenceEntity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "art_image_reference_id")
	private UUID artImageReferenceId;
	
	@Column(name = "image_bytes")
	private byte[] imageBytes;
	
	@Column(name = "image_link", nullable = false)
	private String imageLink;
	
	@Column(name = "upload_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ArtImageReferenceUploadType uploadType;
	
	/** Relacionamentos */
	@ManyToOne
	@JoinColumn(name = "art_parent_fk")
	private ArtEntity artParent;
	
	public ArtImageReferenceEntity() {
		
	}
	
}
