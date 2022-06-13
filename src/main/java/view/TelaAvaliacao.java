package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.NumberFormat;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controller.AvaliacaoController;
import model.Aluno;
import model.Turma;

public class TelaAvaliacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Turma> cmbAvTurma;
	private JComboBox<Aluno> cmbAvAluno;
	private JTextField tfAv1;
	private JTextField tfAv2;
	private JTextField tfAvFinal;
	private JTextField tfAvFrequencia;
	private EntityManager entityManager;


	/**
	 * Create the frame.
	 */
	public TelaAvaliacao(EntityManager entityManager) {
		setTitle("Avaliação");
		this.entityManager = entityManager;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblAvTurma = new JLabel("Turma:");
		lblAvTurma.setBounds(10, 31, 68, 13);
		contentPane.add(lblAvTurma);
		
		cmbAvTurma = new JComboBox<Turma>();
		cmbAvTurma.setBounds(88, 27, 401, 20);
		contentPane.add(cmbAvTurma);
		
		JLabel lblAvAluno = new JLabel("Aluno:");
		lblAvAluno.setBounds(10, 65, 68, 13);
		contentPane.add(lblAvAluno);
		
		cmbAvAluno = new JComboBox<Aluno>();
		cmbAvAluno.setBounds(88, 61, 401, 20);
		contentPane.add(cmbAvAluno);
		
		JLabel lblNota1 = new JLabel("1ª Nota:");
		lblNota1.setBounds(10, 110, 68, 13);
		contentPane.add(lblNota1);
		
		tfAv1 = new JTextField();
		tfAv1.setBounds(88, 107, 96, 19);
		contentPane.add(tfAv1);
		tfAv1.setColumns(10);
		
		JLabel lblNota2 = new JLabel("2ª Nota:");
		lblNota2.setBounds(10, 148, 68, 13);
		contentPane.add(lblNota2);
		
		tfAv2 = new JTextField();
		tfAv2.setBounds(88, 145, 96, 19);
		contentPane.add(tfAv2);
		tfAv2.setColumns(10);
		
		JLabel lblNotaFinal = new JLabel("Nota Final:");
		lblNotaFinal.setBounds(10, 188, 68, 13);
		contentPane.add(lblNotaFinal);
		
		tfAvFinal = new JTextField();
		tfAvFinal.setBounds(88, 185, 96, 19);
		contentPane.add(tfAvFinal);
		tfAvFinal.setColumns(10);
		
		JLabel lblAvFrequencia = new JLabel("Frequência:");
		lblAvFrequencia.setBounds(10, 229, 68, 13);
		contentPane.add(lblAvFrequencia);
		
		tfAvFrequencia = new JTextField();
		tfAvFrequencia.setColumns(10);
		tfAvFrequencia.setBounds(88, 226, 96, 19);
		contentPane.add(tfAvFrequencia);
		

		
		NumberFormat numberFormat = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(numberFormat);
		formatter.setMinimum(0);
		formatter.setMaximum(99);
		formatter.setAllowsInvalid(false);
		
		JFormattedTextField tfPosicao = new JFormattedTextField(formatter);
		tfPosicao.setBounds(414, 226, 75, 20);
		contentPane.add(tfPosicao);
		
		JLabel lblPosicao = new JLabel("Posi\u00E7\u00E3o");
		lblPosicao.setBounds(336, 229, 45, 13);
		contentPane.add(lblPosicao);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(10, 310, 100, 25);
		contentPane.add(btnAdicionar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(140, 310, 100, 25);
		contentPane.add(btnExcluir);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(266, 310, 100, 25);
		contentPane.add(btnConsultar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(389, 310, 100, 25);
		contentPane.add(btnListar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 350, 487, 350);
		contentPane.add(scrollPane);
		
		JTextArea taLista = new JTextArea();
		taLista.setBounds(10, 350, 487, 350);
		contentPane.add(taLista);
		
		AvaliacaoController avaliacaoController = new AvaliacaoController(cmbAvTurma, cmbAvAluno,
				tfAv1, tfAv2, tfAvFinal, tfAvFrequencia,
				tfPosicao, taLista, entityManager);
		
		btnAdicionar.addActionListener(avaliacaoController);
		btnConsultar.addActionListener(avaliacaoController);
		btnExcluir.addActionListener(avaliacaoController);
		btnListar.addActionListener(avaliacaoController);
		
	}
}
