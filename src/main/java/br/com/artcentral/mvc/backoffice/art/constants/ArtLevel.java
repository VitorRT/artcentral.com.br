package br.com.artcentral.mvc.backoffice.art.constants;

public enum ArtLevel {
	PUBLIC("public"),
	PRIVATE("private"),
	NOT_LISTED("not_listed");
	
	private String levelName;
	
	ArtLevel(String artLevel) {
		this.levelName = artLevel;
	}
	
	public String getArtLevelName() {
		return this.levelName;
	}
}
