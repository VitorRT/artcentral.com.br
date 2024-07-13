package br.com.artcentral.mvc.backoffice.art.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;

public class ArtUtil {
	public static Map<UUID, List<ArtEntity>> filterPerUser(List<ArtEntity> artsWithScheduleDate) {
		Map<UUID, List<ArtEntity>> filtrado = new HashMap<>();
		artsWithScheduleDate.forEach(art -> {
			UUID userId = art.getUserAccountParent().getUserAccountId();

			if (filtrado.containsKey(userId)) {
				filtrado.get(userId).add(art);
			} else {
				List<ArtEntity> userArts = new ArrayList<>();
				userArts.add(art);
				filtrado.put(userId, userArts);
			}
		});
		return filtrado;
	}
}
