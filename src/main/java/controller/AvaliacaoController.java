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

import model.Aluno;
import model.Avaliacao;
import model.Turma;
import persistence.AlunoDao;
import persistence.AvaliacaoDao;
import persistence.TurmaDao;

public class AvaliacaoController implements ActionListener{
	
	private JComboBox<Turma> cmbAvTurma;
	private JComboBox<Aluno> cmbAvAluno;
	private JTextField tfAv1;
	private JTextField tfAv2;
	private JTextField tfAvFinal;
	private JTextField tfAvFrequencia;
	private JFormattedTextField tfPosicao;
	private JTextArea taLista;
	
	private AvaliacaoDao avaliacaoDao;
	
	private TurmaDao turmaDao;
	private List<Turma> turmas;
	private AlunoDao alunoDao;
	private List<Aluno> alunos;
	
	
	public AvaliacaoController(JComboBox<Turma> cmbAvTurma, JComboBox<Aluno> cmbAvAluno, 
			JTextField tfAv1, JTextField tfAv2, JTextField tfAvFinal, JTextField tfAvFrequencia,
			JFormattedTextField tfPosicao, JTextArea taLista, EntityManager em) {
		super();
		this.cmbAvTurma = cmbAvTurma;
		this.cmbAvAluno = cmbAvAluno;
		this.tfAv1 = tfAv1;
		this.tfAv2 = tfAv2;
		this.tfAvFinal = tfAvFinal;
		this.tfAvFrequencia = tfAvFrequencia;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;

		avaliacaoDao = new AvaliacaoDao(em);
		turmaDao = new TurmaDao(em);
		alunoDao = new AlunoDao(em);
		
		turmas = turmaDao.consultaTurmas();
		DefaultComboBoxModel<Turma> defalDefaultComboModelTurma =
				new DefaultComboBoxModel(turmas.toArray());
		cmbAvTurma.setModel(defalDefaultComboModelTurma);
		
		alunos = alunoDao.consultaAlunos();		
		DefaultComboBoxModel<Aluno> defalDefaultComboModelAluno =
				new DefaultComboBoxModel(alunos.toArray());
		cmbAvAluno.setModel(defalDefaultComboModelAluno);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			
			Turma turma = (Turma)cmbAvTurma.getSelectedItem();
			Aluno aluno = (Aluno)cmbAvAluno.getSelectedItem();
		
			Avaliacao avaliacao = new Avaliacao(
					turma,
					aluno,
					Integer.valueOf(tfAv1.getText()),
					Integer.valueOf(tfAv2.getText()),
					Integer.valueOf(tfAvFinal.getText()),
					Integer.valueOf(tfAvFrequencia.getText()));			
			adicionar(avaliacao);
			
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

		List<Avaliacao> avaliacoes = avaliacaoDao.consultaAvaliacoes();
		StringBuffer buffer = new StringBuffer();
		for (Avaliacao avaliacao : avaliacoes) {
			buffer.append(avaliacao.toString());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Avaliacao avaliacao = avaliacaoDao.consultaAvaliacao(posicao);
		tfAv1.setText(avaliacao.getNota_1().toString());
		tfAv2.setText(avaliacao.getNota_2().toString());
		tfAvFinal.setText(avaliacao.getNota_prova_final().toString());
		tfAvFrequencia.setText(avaliacao.getFrequencia().toString());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		avaliacaoDao.excluiAvaliacao(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Avaliacao avaliacao) {
		
		Avaliacao retorno = avaliacaoDao.adicionaAvaliacao(avaliacao);
		if(retorno != null) {
			JOptionPane.showMessageDialog(null, retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, retorno, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		tfAv1.setText("");
		tfAv2.setText("");
		tfAvFinal.setText("");
		tfAvFrequencia.setText("");
		listar();
	}

}
