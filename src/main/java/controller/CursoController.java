package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Curso;
import persistence.CursoDao;

public class CursoController implements ActionListener{
	
	private JTextField tfCursoCodigo;
	private JTextField tfCursoDescricao;
	private JFormattedTextField tfPosicao;
	private JTextArea taLista;
	
	private CursoDao cursoDao;
	
	public CursoController(JTextField tfCursoCodigo, JTextField tfCursoDescricao,
			JFormattedTextField tfPosicao, JTextArea taLista, EntityManager em) {
		super();
		this.tfCursoCodigo = tfCursoCodigo;
		this.tfCursoDescricao = tfCursoDescricao;
		this.tfPosicao = tfPosicao;
		this.taLista = taLista;
		
		cursoDao = new CursoDao(em);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Adicionar")) {
		
			Curso curso = new Curso(
					tfCursoCodigo.getText(),
					tfCursoDescricao.getText());			
			adicionar(curso);
			
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

		List<Curso> cursos = cursoDao.consultaCursos();
		StringBuffer buffer = new StringBuffer();
		for (Curso curso : cursos) {
			buffer.append(curso.toList());
			buffer.append("\n");
		}
		taLista.setText(buffer.toString());
	}

	private void consultar(int posicao) {
		Curso curso = cursoDao.consultaCurso(posicao);
		tfCursoCodigo.setText(curso.getCodigo().toString());
		tfCursoDescricao.setText(curso.getDescricao());
		tfPosicao.setText("0");
	}

	private void excluir(int posicao) {
		
		cursoDao.excluiCurso(posicao);
		tfPosicao.setText("0");
		listar();
	}

	private void adicionar(Curso curso) {
		
		Curso retorno = cursoDao.adicionaCurso(curso);
		if(retorno != null) {
			JOptionPane.showMessageDialog(null, retorno, "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, retorno, "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
		tfCursoCodigo.setText("");
		tfCursoDescricao.setText("");
		listar();
	}

}
