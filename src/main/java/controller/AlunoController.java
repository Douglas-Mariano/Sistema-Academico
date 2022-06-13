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

import model.Aluno;
import model.Curso;
import model.Professor;
import model.SituacaoAluno;
import model.TitulacaoMaxima;
import persistence.AlunoDao;
import persistence.CursoDao;
import persistence.ProfessorDao;

public class AlunoController implements ActionListener{
	
	private JPanel contentPane;
	private JTextField tfPessoaNome;
	private JTextField tfPessoaEndereco;
	private JTextField tfPessoaTelefone;
	private JComboBox<String> cmbAlunoSituacao;
	private JComboBox<Curso> cmbAlunoCursos;
	private JFormattedTextField tfPosicao;
	private JTextArea taLista;
	
	private AlunoDao alunoDao;
	private CursoDao cursoDao;
	private List<Curso> cursos;
	
	public AlunoController(JTextField tfPessoaNome, JTextField tfPessoaEndereco,
			JTextField tfPessoaTelefone, JComboBox<String> cmbAlunoSituacao,
			JComboBox<Curso> cmbAlunoCursos, JFormattedTextField tfPosicao,
			JTextArea taLista, EntityManager em) {
		super();
		this.tfPessoaNome = tfPessoaNome;
		this.tfPessoaEndereco = tfPessoaEndereco;
		this.tfPessoaTelefone = tfPessoaTelefone;
		this.cmbAlunoSituacao = cmbAlunoSituacao;
		this.cmbAlunoCursos = cmbAlunoCursos;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;
		
		alunoDao = new AlunoDao(em);
		cursoDao = new CursoDao(em);
		
		cursos = cursoDao.consultaCursos();
		DefaultComboBoxModel<Curso> defalDefaultComboBoxModel =
				new DefaultComboBoxModel(cursos.toArray());
		cmbAlunoCursos.setModel(defalDefaultComboBoxModel);
		
		DefaultComboBoxModel<String> defalDefaultComboModelSituacao =
				new DefaultComboBoxModel(SituacaoAluno.values());
		cmbAlunoSituacao.setModel(defalDefaultComboModelSituacao);
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			
			Curso curso = (Curso)cmbAlunoCursos.getSelectedItem();
		
			Aluno aluno = new Aluno(
					tfPessoaNome.getText(),
					tfPessoaEndereco.getText(),
					tfPessoaTelefone.getText(),
					((SituacaoAluno)cmbAlunoSituacao.getSelectedItem()).name(),
					curso);			
			adicionar(aluno);
			
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

		List<Aluno> alunos = alunoDao.consultaAlunos();
		StringBuffer buffer = new StringBuffer();
		for (Aluno aluno : alunos) {
			buffer.append(aluno.toList());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Aluno aluno = alunoDao.consultaAluno(posicao);
		tfPessoaNome.setText(aluno.getNome().toString());
		tfPessoaEndereco.setText(aluno.getEndereco().toString());
		tfPessoaTelefone.setText(aluno.getTelefone().toString());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		alunoDao.excluiAluno(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Aluno aluno) {
		
		Aluno retorno = alunoDao.adicionaAluno(aluno);
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
