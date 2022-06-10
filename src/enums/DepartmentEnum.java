package br.com.foursys.fourcamp.fourstore.enums;

public enum DepartmentEnum {
	
	CLO("Vestuário"),
	SHO("Calçaados"),
	PER("Perfumaria"),
	ACC("Acessorios");

	private String description2;
	
	DepartmentEnum(String description2) {
		
		this.description2 = description2;
		
	}

	public String getDescription2() {
		return description2;
	}

}
