package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "disciplinas")
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String codigo;
	@Column
	private String descricao;
	@JoinColumn(name = "id_curso", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Curso curso;
	
	public Disciplina() {
		super();
	}

	public Disciplina(String codigo, String descricao, Curso curso) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.curso = curso;
	}

	public String toList() {
		return "Disciplina: " + descricao + " - Código: " + codigo +
				" - Curso: " + curso;
	}

	@Override
	public String toString() {
		return descricao;
	}
	

}
