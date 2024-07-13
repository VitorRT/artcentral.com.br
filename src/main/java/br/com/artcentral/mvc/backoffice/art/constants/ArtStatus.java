package br.com.artcentral.mvc.backoffice.art.constants;

public enum ArtStatus {
	TODO("todo"),
	PROGRESS("progress"),
	DRAWNER("drawner"),
	FINISHED("fineshed");
	
	private String statusName;
	
	ArtStatus(String statusName) {
		this.statusName = statusName;
	}
	
	public String getArtStatusName() {
		return this.statusName;
	}
}
