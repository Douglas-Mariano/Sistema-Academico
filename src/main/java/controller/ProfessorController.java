package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Curso;
import model.Professor;
import model.TitulacaoMaxima;
import persistence.CursoDao;
import persistence.ProfessorDao;

public class ProfessorController implements ActionListener{
	
	private JPanel contentPane;
	private JTextField tfPessoaNome;
	private JTextField tfPessoaEndereco;
	private JTextField tfPessoaTelefone;
	private JComboBox<String> cmbProfTitulacao;
	private JComboBox<Curso> cmbProfCursos;
	private JFormattedTextField tfPosicao;
	private JTextArea taLista;
	
	private ProfessorDao professorDao;
	private CursoDao cursoDao;
	private List<Curso> cursos;
	
	public ProfessorController(JTextField tfPessoaNome, JTextField tfPessoaEndereco,
			JTextField tfPessoaTelefone, JComboBox<String> cmbProfTitulacao,
			JComboBox<Curso> cmbProfCursos, JFormattedTextField tfPosicao,
			JTextArea taLista, EntityManager em) {
		super();
		this.tfPessoaNome = tfPessoaNome;
		this.tfPessoaEndereco = tfPessoaEndereco;
		this.tfPessoaTelefone = tfPessoaTelefone;
		this.cmbProfTitulacao = cmbProfTitulacao;
		this.cmbProfCursos = cmbProfCursos;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;
		
		professorDao = new ProfessorDao(em);
		cursoDao = new CursoDao(em);
		
		cursos = cursoDao.consultaCursos();
		DefaultComboBoxModel<Curso> defalDefaultComboBoxModel =
				new DefaultComboBoxModel(cursos.toArray());
		cmbProfCursos.setModel(defalDefaultComboBoxModel);
		
		DefaultComboBoxModel<String> defalDefaultComboModelTitulacao =
				new DefaultComboBoxModel(TitulacaoMaxima.values());
		cmbProfTitulacao.setModel(defalDefaultComboModelTitulacao);
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			
			Curso curso = (Curso)cmbProfCursos.getSelectedItem();
		
			Professor professor = new Professor(
					tfPessoaNome.getText(),
					tfPessoaEndereco.getText(),
					tfPessoaTelefone.getText(),
					((TitulacaoMaxima)cmbProfTitulacao.getSelectedItem()).name(),
					curso);			
			adicionar(professor);
			
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

		List<Professor> professores = professorDao.consultaProfessores();
		StringBuffer buffer = new StringBuffer();
		for (Professor professor : professores) {
			buffer.append(professor.toList());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Professor professor = professorDao.consultaProfessor(posicao);
		tfPessoaNome.setText(professor.getNome().toString());
		tfPessoaEndereco.setText(professor.getEndereco().toString());
		tfPessoaTelefone.setText(professor.getTelefone().toString());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		professorDao.excluiProfessor(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Professor professor) {
		
		Professor retorno = professorDao.adicionaProfessor(professor);
		if(retorno != null) {
			JOptionPane.showMessageDialog(null, retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, retorno, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		tfPessoaNome.setText("");
		tfPessoaEndereco.setText("");
		tfPessoaTelefone.setText("");
		listar();
	}


}
