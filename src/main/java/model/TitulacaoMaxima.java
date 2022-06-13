package model;

public enum TitulacaoMaxima {
	GRADUADO("Graduado"),
	MESTRE("Mestre"),
	DOUTOR("Doutor");
	
	private String descricao2;
	
	TitulacaoMaxima(String descricao2) {
		this.descricao2 = descricao2;
	}
	
	@Override
	public String toString() {
		return this.descricao2;
	}
	
}