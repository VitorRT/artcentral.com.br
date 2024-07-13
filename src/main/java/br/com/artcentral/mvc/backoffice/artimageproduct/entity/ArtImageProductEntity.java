package br.com.artcentral.mvc.backoffice.artimageproduct.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import lombok.Data;

@Entity
@Table(name = "ac_art_image_products")
@Data
public class ArtImageProductEntity {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "art_image_product_id")
	private UUID artImageProductId;
	
	@Column(name = "image_bytes", nullable = false)
	@Lob
	private byte[] imageBytes;
	
	@Column(name = "image_link")
	private String imageLink;
	
	/** Relacionamentos */
	@OneToOne
	@JoinColumn(name = "art_parent_fk")
	private ArtEntity artParent;
	
}
