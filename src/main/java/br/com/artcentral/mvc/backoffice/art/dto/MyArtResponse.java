package br.com.artcentral.mvc.backoffice.art.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import br.com.artcentral.mvc.backoffice.art.constants.ArtLevel;
import br.com.artcentral.mvc.backoffice.art.constants.ArtStatus;
import br.com.artcentral.mvc.backoffice.art.entity.ArtEntity;
import br.com.artcentral.mvc.system.utils.DateUtils;

public class MyArtResponse {
	private UUID artId;
	private String artName;
	private ArtStatus artStatus;
	private ArtLevel artLevel;
	private String createdAt;
	
	public MyArtResponse() {
		
	}
	
	public MyArtResponse(ArtEntity en) {
		this.artId = en.getArtId();
		this.artName = en.getArtName();
		this.artStatus = en.getArtStatus();
		this.artLevel = en.getArtLevel();
		this.createdAt = formatDate(en.getCreatedAt());
	}
	
	private String formatDate(LocalDateTime dateTime) {
		return DateUtils.formatDateAndTime(dateTime.toLocalDate(), dateTime.toLocalTime());
	}
}
	