package br.com.artcentral.mvc.artimagereference.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.artcentral.mvc.art.entity.ArtEntity;

@Entity
@Table(name = "ac_art_image_references")
public class ArtImageReferenceEntity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "art_image_reference_id")
	private UUID artImageReferenceId;
	private byte[] imageBytes;
	private String imageLink;
	
	/** Relacionamentos */
	@ManyToOne
	@JoinColumn(name = "")
	private ArtEntity artParent;
}
