package br.com.artcentral.mvc.backoffice.art.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import br.com.artcentral.mvc.backoffice.art.repository.ArtRepository;
import br.com.artcentral.mvc.backoffice.art.service.ArtService;
import br.com.artcentral.mvc.backoffice.art.util.ArtUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ArtTask {
	@Autowired
	private ArtRepository repo;
	@Autowired
	private ArtService service;

	@Scheduled(cron = "0 0 2 * * ?")
	public void checkScheduleDateArts() {
		new Thread(check).start();
	}

	private Runnable check = new Runnable() {
		public void run() {
			List<ArtEntity> artsWithScheduleDate = repo.findArtsWithScheduleDate();
			if(artsWithScheduleDate.isEmpty()) {
				log.info("[ArtTask] - Não existe nenhuma arte no sistema com datas de prazo.");
				return;
			}
			Map<UUID, List<ArtEntity>> artsFiltradas = ArtUtil.filterPerUser(artsWithScheduleDate);
			
			log.info("[ArtTask] - Ínicio da tarefa de checagem de datas limites das artes do sistema, as {}", LocalDateTime.now());
			
			artsFiltradas.forEach((userAccountId, art) -> service.checaArtesComDataPrazo(userAccountId, artsWithScheduleDate));
			
			log.info("[ArtTask] - Término da tarefa de checagem de datas limites das artes do sistema, as {}", LocalDateTime.now());
		}
	};
}
