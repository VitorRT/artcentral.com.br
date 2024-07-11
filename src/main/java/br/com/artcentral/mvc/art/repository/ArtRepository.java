package br.com.artcentral.mvc.art.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.artcentral.mvc.art.entity.ArtEntity;

public interface ArtRepository extends JpaRepository<ArtEntity, UUID> {
	@Query("SELECT a FROM ArtEntity a WHERE a.haveSchedule = true")
	List<ArtEntity> findArtsWithScheduleDate();
}
