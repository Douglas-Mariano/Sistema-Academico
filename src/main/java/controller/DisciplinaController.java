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

import model.Curso;
import model.Disciplina;
import persistence.CursoDao;
import persistence.DisciplinaDao;

public class DisciplinaController implements ActionListener{
	
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaDescricao;
	private JFormattedTextField tfPosicao;
	private JComboBox<Curso> cmbDisciplinaCursos;
	private JTextArea taLista;
	
	private DisciplinaDao disciplinaDao;
	private CursoDao cursoDao;
	private List<Curso> cursos;
	
	public DisciplinaController(JTextField tfDisciplinaCodigo,
			JTextField tfDisciplinaDescricao, JComboBox<Curso> cmbDisciplinaCursos,
			JFormattedTextField tfPosicao, JTextArea taLista, EntityManager em) {
		super();
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaDescricao = tfDisciplinaDescricao;
		this.cmbDisciplinaCursos = cmbDisciplinaCursos;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;
		
		disciplinaDao = new DisciplinaDao(em);
		cursoDao = new CursoDao(em);
		
		cursos = cursoDao.consultaCursos();
		DefaultComboBoxModel<Curso> defalDefaultComboBoxModel =
				new DefaultComboBoxModel(cursos.toArray());
		cmbDisciplinaCursos.setModel(defalDefaultComboBoxModel);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
			
			Curso curso = (Curso)cmbDisciplinaCursos.getSelectedItem();
		
			Disciplina disciplina = new Disciplina(
					tfDisciplinaCodigo.getText(),
					tfDisciplinaDescricao.getText(), curso);			
			adicionar(disciplina);
			
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

		List<Disciplina> disciplinas = disciplinaDao.consultaDisciplinas();
		StringBuffer buffer = new StringBuffer();
		for (Disciplina disciplina : disciplinas) {
			buffer.append(disciplina.toList());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Disciplina disciplina = disciplinaDao.consultaDisciplina(posicao);
		tfDisciplinaCodigo.setText(disciplina.getCodigo().toString());
		tfDisciplinaDescricao.setText(disciplina.getDescricao());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		disciplinaDao.excluiDisciplina(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Disciplina disciplina) {
		
		Disciplina retorno = disciplinaDao.adicionaDisciplina(disciplina);
		if(retorno != null) {
			JOptionPane.showMessageDialog(null, retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, retorno, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		tfDisciplinaCodigo.setText("");
		tfDisciplinaDescricao.setText("");
		listar();
	}

}
