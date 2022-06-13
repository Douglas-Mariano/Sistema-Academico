package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Pessoa {
	
	@Column
	private String situacao;
	@JoinColumn(name = "id_curso", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Curso curso;

	public Aluno() {
		super();
	}
	
	public Aluno(String nome, String endereco,
			String telefone, String situacao, Curso curso) {
		super(nome, endereco, telefone);
		this.situacao = situacao;
		this.curso = curso;
	}

	public String toList() {
		return "Aluno: " + getNome() + " - Endereço: " + getEndereco() + " - Telefone: " + getTelefone() +
				"\nSituação: " + situacao + "\nCurso=" + curso;
	}

	@Override
	public String toString() {
		return getNome();
	}
	

}
