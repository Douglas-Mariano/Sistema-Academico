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
@Table(name = "turmas")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Integer ano;
	@Column
	private Integer semestre;
	@Column
	private Integer dia_semana;
	@Column
	private String horario;
	@JoinColumn(name = "id_disciplina", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Disciplina disciplina;
	@JoinColumn(name = "id_professor", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Professor professor;
	
	public Turma() {
		super();
	}
	
	public Turma(Integer ano, Integer semestre, Integer dia_semana, String horario,
			Disciplina disciplina, Professor professor) {
		super();
		this.ano = ano;
		this.semestre = semestre;
		this.dia_semana = dia_semana;
		this.horario = horario;
		this.disciplina = disciplina;
		this.professor = professor;
	}

	public String toList() {
		return "Turma: " + id + " - Ano: " + ano + " - Semestre: " + semestre + "º" +
				" - Dia da Semana: " + dia_semana + "ª" + " - Horário da Aula: " + horario + "hrs" +
				"\nDisciplina: " + disciplina + "\nProfessor: " + professor;
	}

	@Override
	public String toString() {
		return "Turma: " + id + " - Disciplina: " + disciplina;
	}
	
}
