package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Disciplina;
import model.Professor;
import model.Turma;
import persistence.DisciplinaDao;
import persistence.ProfessorDao;
import persistence.TurmaDao;

public class TurmaController implements ActionListener{
	
	private JTextField tfTurmaAno;
	private JTextField tfTurmaSemestre;
	private JTextField tfTurmaDiaSemana;
	private JTextField tfTurmaHorario;
	private JFormattedTextField tfPosicao;
	private JComboBox<Disciplina> cmbTurmaDiscip;
	private JComboBox<Professor> cmbTurmaProf;
	private JTextArea taLista;
	
	private TurmaDao turmaDao;
	private DisciplinaDao disciplinaDao;
	private List<Disciplina> disciplinas;
	private ProfessorDao professorDao;
	private List<Professor> professores;
	
	
	public TurmaController(JTextField tfTurmaAno, JTextField tfTurmaSemestre,
			JTextField tfTurmaDiaSemana, JTextField tfTurmaHorario,
			JComboBox<Disciplina> cmbTurmaDiscip, JComboBox<Professor> cmbTurmaProf,
			JFormattedTextField tfPosicao, JTextArea taLista, EntityManager em) {
		super();
		this.tfTurmaAno = tfTurmaAno;
		this.tfTurmaSemestre = tfTurmaSemestre;
		this.tfTurmaDiaSemana = tfTurmaDiaSemana;
		this.tfTurmaHorario = tfTurmaHorario;
		this.cmbTurmaDiscip = cmbTurmaDiscip;
		this.cmbTurmaProf = cmbTurmaProf;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;

		turmaDao = new TurmaDao(em);
		disciplinaDao = new DisciplinaDao(em);
		professorDao = new ProfessorDao(em);
		
		disciplinas = disciplinaDao.consultaDisciplinas();
		DefaultComboBoxModel<Disciplina> defalDefaultComboModelDisciplina =
				new DefaultComboBoxModel(disciplinas.toArray());
		cmbTurmaDiscip.setModel(defalDefaultComboModelDisciplina);
		
		professores = professorDao.consultaProfessores();
		DefaultComboBoxModel<Professor> defalDefaultComboModelProfessor =
				new DefaultComboBoxModel(professores.toArray());
		cmbTurmaProf.setModel(defalDefaultComboModelProfessor);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			
			Disciplina disciplina = (Disciplina)cmbTurmaDiscip.getSelectedItem();
			Professor professor = (Professor)cmbTurmaProf.getSelectedItem();
		
			Turma turma = new Turma(
					Integer.valueOf(tfTurmaAno.getText()),
					Integer.valueOf(tfTurmaSemestre.getText()),
					Integer.valueOf(tfTurmaDiaSemana.getText()),
					tfTurmaHorario.getText(),
					disciplina, professor);			
			adicionar(turma);
			
		}
		if (cmd.equals("Excluir")) {
			int posicao = Integer.parseInt(tfPosicao.getText());
			excluir(posicao);
		}
		if (cmd.equals("Consultar")) {
			int posicao = Integer.parseInt(tfPosicao.getText());
			consultar(posicao);
		}
		if (cmd.equals("Listar")) {
			listar();
		}
	}

	private void listar() {

		List<Turma> turmas = turmaDao.consultaTurmas();
		StringBuffer buffer = new StringBuffer();
		for (Turma turma : turmas) {
			buffer.append(turma.toList());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Turma turma = turmaDao.consultaTurma(posicao);
		tfTurmaAno.setText(turma.getAno().toString());
		tfTurmaSemestre.setText(turma.getSemestre().toString());
		tfTurmaDiaSemana.setText(turma.getDia_semana().toString());
		tfTurmaHorario.setText(turma.getHorario());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		turmaDao.excluiTurma(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Turma turma) {
		
		Turma retorno = turmaDao.adicionaTurma(turma);
		if(retorno != null) {
			JOptionPane.showMessageDialog(null, retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, retorno, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		tfTurmaAno.setText("");
		tfTurmaSemestre.setText("");
		tfTurmaDiaSemana.setText("");
		tfTurmaHorario.setText("");
		listar();
	}

}
