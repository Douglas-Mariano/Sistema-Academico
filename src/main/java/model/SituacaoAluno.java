package model;

public enum SituacaoAluno {
	AGUARDANDO_RENOVACAO("Aguardando Renovação"),
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	private String descricao1;

	SituacaoAluno(String descricao1) {
		this.descricao1 = descricao1;
	}

	@Override
	public String toString() {
		return this.descricao1;
	}

}