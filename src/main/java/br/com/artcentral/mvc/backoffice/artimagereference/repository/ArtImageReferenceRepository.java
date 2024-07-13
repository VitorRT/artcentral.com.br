package br.com.artcentral.mvc.backoffice.artimagereference.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.artcentral.mvc.backoffice.artimagereference.entity.ArtImageReferenceEntity;

public interface ArtImageReferenceRepository extends JpaRepository<ArtImageReferenceEntity, UUID>{
	@Query("SELECT ref FROM ArtImageReferenceEntity ref INNER JOIN ArtEntity art ON art.artId = :artId")
	List<ArtImageReferenceEntity> findAllArtImageReferencesByArtId(@Param(value = "artId") UUID artId);
}
