package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@Table(name = "avaliacoes")
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JoinColumn(name = "id_turma", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Turma turma;
	@JoinColumn(name = "id_aluno", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Aluno aluno;
	@Column
	private Float nota_1;
	@Column
	private Float nota_2;
	@Column
	private Float nota_prova_final;
	@Column
	private Float frequencia;
	
	//metodos construtores
	
	public Avaliacao() {
		super();
	}
	
	public Avaliacao(Turma turma, Aluno aluno, int nota_1, int nota_2, int nota_prova_final, int frequencia) {
		super();
		this.turma = turma;
		this.aluno = aluno;
		this.nota_1 = (float) nota_1;
		this.nota_2 = (float) nota_2;
		this.nota_prova_final = (float) nota_prova_final;
		this.frequencia = (float)frequencia;
	}

	@Override
	public String toString() {
		return "Avaliação - Aluno: " + aluno + " - Turma: " + turma + 
				"\n1ª Nota: " + nota_1 + " - 2ª Nota: " + nota_2
				+ " - Nota Final: " + nota_prova_final + " - Frequência: " + frequencia + "%";
	}
	
}