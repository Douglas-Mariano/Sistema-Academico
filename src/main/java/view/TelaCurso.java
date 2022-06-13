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

import controller.CursoController;

public class TelaCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCursoCodigo;
	private JTextField tfCursoDescricao;
	private EntityManager entityManager;

	/**
	 * Create the frame.
	 */
	public TelaCurso(EntityManager entityManager) {
		setTitle("CURSO");
		this.entityManager = entityManager;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCursoCodigo = new JLabel("Codigo:");
		lblCursoCodigo.setBounds(10, 22, 68, 13);
		contentPane.add(lblCursoCodigo);
		
		tfCursoCodigo = new JTextField();
		tfCursoCodigo.setColumns(10);
		tfCursoCodigo.setBounds(88, 19, 401, 20);
		contentPane.add(tfCursoCodigo);
		
		JLabel lblCursoDescricao = new JLabel("Descrição:");
		lblCursoDescricao.setBounds(10, 52, 68, 13);
		contentPane.add(lblCursoDescricao);
		
		tfCursoDescricao = new JTextField();
		tfCursoDescricao.setBounds(88, 49, 401, 20);
		contentPane.add(tfCursoDescricao);
		tfCursoDescricao.setColumns(10);
		
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
		
		CursoController cursoController = new CursoController(tfCursoCodigo,
				tfCursoDescricao, tfPosicao, taLista, entityManager);
		
		btnAdicionar.addActionListener(cursoController);
		btnConsultar.addActionListener(cursoController);
		btnExcluir.addActionListener(cursoController);
		btnListar.addActionListener(cursoController);

	}

}
