package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String codigo;
	@Column
	private String descricao;
	
	public Curso() {
		super();
	}

	public Curso(String codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}


	public String toList() {
		return "Curso: " + descricao + " - Código: " + codigo;
	}

	@Override
	public String toString() {
		return descricao;
	}
	

}
