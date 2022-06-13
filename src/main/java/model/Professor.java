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
@DiscriminatorValue("PROFESSOR")
public class Professor extends Pessoa {
	
	@Column (name= "titulacao")
	private String titulacaoMaxima;
	@JoinColumn(name = "id_curso", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Curso curso;

	public Professor() {
		super();
	}
	
	public Professor(String nome, String endereco,
			String telefone, String titulacaoMaxima, Curso curso) {
		super(nome, endereco, telefone);
		this.titulacaoMaxima = titulacaoMaxima;
		this.curso = curso;
	}

	public String toList() {
		return "Professor: " + getNome() + " - Endereço: " + getEndereco() + " - Telefone: " + getTelefone() +
				"\nTitulo: " + titulacaoMaxima + "\nCurso: " + curso;
	}

	@Override
	public String toString() {
		return getNome();
	}
	

}
