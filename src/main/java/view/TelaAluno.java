package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.NumberFormat;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import controller.AlunoController;
import controller.ProfessorController;
import model.Curso;

import javax.swing.JComboBox;

public class TelaAluno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPessoaNome;
	private JTextField tfPessoaEndereco;
	private JTextField tfPessoaTelefone;
	private JComboBox<String> cmbAlunoSituacao;
	private JComboBox<Curso> cmbAlunoCursos;
	private EntityManager entityManager;

	/**
	 * Create the frame.
	 */
	public TelaAluno(EntityManager entityManager) {
		setTitle("ALUNO");
		this.entityManager = entityManager;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPessoaNome = new JLabel("Nome:");
		lblPessoaNome.setBounds(10, 22, 68, 13);
		contentPane.add(lblPessoaNome);
		
		tfPessoaNome = new JTextField();
		tfPessoaNome.setColumns(10);
		tfPessoaNome.setBounds(88, 19, 401, 20);
		contentPane.add(tfPessoaNome);
		
		JLabel lblPessoaEndereco = new JLabel("Endere\u00E7o:");
		lblPessoaEndereco.setBounds(10, 52, 68, 13);
		contentPane.add(lblPessoaEndereco);
		
		tfPessoaEndereco = new JTextField();
		tfPessoaEndereco.setBounds(88, 49, 401, 20);
		contentPane.add(tfPessoaEndereco);
		tfPessoaEndereco.setColumns(10);
		
		JLabel lblPessoaTelefone = new JLabel("Telefone:");
		lblPessoaTelefone.setBounds(10, 84, 68, 13);
		contentPane.add(lblPessoaTelefone);
		
		tfPessoaTelefone = new JTextField();
		tfPessoaTelefone.setColumns(10);
		tfPessoaTelefone.setBounds(88, 81, 401, 20);
		contentPane.add(tfPessoaTelefone);
		
		JLabel lblAlunoSituacao = new JLabel("Situação:");
		lblAlunoSituacao.setBounds(10, 144, 68, 13);
		contentPane.add(lblAlunoSituacao);
		
		cmbAlunoSituacao = new JComboBox<String>();
		cmbAlunoSituacao.setBounds(88, 140, 232, 20);
		contentPane.add(cmbAlunoSituacao);
		
		JLabel lblAlunoCurso = new JLabel("Curso:");
		lblAlunoCurso.setBounds(10, 115, 68, 13);
		contentPane.add(lblAlunoCurso);
		
		cmbAlunoCursos = new JComboBox<Curso>();
		cmbAlunoCursos.setBounds(88, 111, 401, 20);
		contentPane.add(cmbAlunoCursos);
		
		
		NumberFormat numberFormat = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(numberFormat);
		formatter.setMinimum(0);
		formatter.setMaximum(99);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField tfPosicao = new JFormattedTextField(formatter);
		tfPosicao.setBounds(414, 141, 75, 20);
		contentPane.add(tfPosicao);
		
		JLabel lblPosicao = new JLabel("Posi\u00E7\u00E3o");
		lblPosicao.setBounds(336, 144, 45, 13);
		contentPane.add(lblPosicao);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 187, 100, 25);
		contentPane.add(btnAdicionar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(140, 187, 100, 25);
		contentPane.add(btnExcluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(266, 187, 100, 25);
		contentPane.add(btnConsultar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(389, 187, 100, 25);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 235, 487, 465);
		contentPane.add(scrollPane);
		
		JTextArea taLista = new JTextArea();
		taLista.setBounds(10, 235, 487, 465);
		contentPane.add(taLista);
		
		AlunoController alunoController = new AlunoController(tfPessoaNome,
				tfPessoaEndereco, tfPessoaTelefone, cmbAlunoSituacao, cmbAlunoCursos,
				tfPosicao, taLista, entityManager);
		
		
		btnAdicionar.addActionListener(alunoController);
		btnConsultar.addActionListener(alunoController);
		btnExcluir.addActionListener(alunoController);
		btnListar.addActionListener(alunoController);

	}
}
