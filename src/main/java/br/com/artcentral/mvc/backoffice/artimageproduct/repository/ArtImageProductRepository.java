package br.com.artcentral.mvc.backoffice.artimageproduct.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.artcentral.mvc.backoffice.artimageproduct.entity.ArtImageProductEntity;

public interface ArtImageProductRepository extends JpaRepository<ArtImageProductEntity, UUID>{

}
