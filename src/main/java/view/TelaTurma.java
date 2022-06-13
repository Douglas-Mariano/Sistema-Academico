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

import controller.TurmaController;
import model.Disciplina;
import model.Professor;

public class TelaTurma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfTurmaAno;
	private JTextField tfTurmaSemestre;
	private JTextField tfTurmaDiaSemana;
	private JTextField tfTurmaHorario;
	private JComboBox<Disciplina> cmbTurmaDiscip;
	private JComboBox<Professor> cmbTurmaProf;
	private EntityManager entityManager;

	/**
	 * Create the frame.
	 */
	public TelaTurma(EntityManager entityManager) {
		setTitle("Turma");
		this.entityManager = entityManager;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 520, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTurmaAno = new JLabel("Ano:");
		lblTurmaAno.setBounds(10, 22, 68, 13);
		contentPane.add(lblTurmaAno);
		
		tfTurmaAno = new JTextField();
		tfTurmaAno.setColumns(10);
		tfTurmaAno.setBounds(88, 19, 401, 20);
		contentPane.add(tfTurmaAno);
		
		JLabel lblTurmaSemestre = new JLabel("Semestre:");
		lblTurmaSemestre.setBounds(10, 52, 68, 13);
		contentPane.add(lblTurmaSemestre);
		
		tfTurmaSemestre = new JTextField();
		tfTurmaSemestre.setBounds(88, 49, 401, 20);
		contentPane.add(tfTurmaSemestre);
		tfTurmaSemestre.setColumns(10);
		
		JLabel lblTurmaDiaSemana = new JLabel("Dia Semana:");
		lblTurmaDiaSemana.setBounds(10, 82, 68, 13);
		contentPane.add(lblTurmaDiaSemana);
		
		tfTurmaDiaSemana = new JTextField();
		tfTurmaDiaSemana.setColumns(10);
		tfTurmaDiaSemana.setBounds(88, 79, 401, 20);
		contentPane.add(tfTurmaDiaSemana);
		
		JLabel lblTurmaHorario = new JLabel("Hor\u00E1rio:");
		lblTurmaHorario.setBounds(10, 114, 68, 13);
		contentPane.add(lblTurmaHorario);
		
		tfTurmaHorario = new JTextField();
		tfTurmaHorario.setColumns(10);
		tfTurmaHorario.setBounds(88, 111, 401, 20);
		contentPane.add(tfTurmaHorario);
		
		JLabel lblTurmaDisciplina = new JLabel("Disciplina:");
		lblTurmaDisciplina.setBounds(10, 147, 68, 13);
		contentPane.add(lblTurmaDisciplina);
		
		cmbTurmaDiscip = new JComboBox<Disciplina>();
		cmbTurmaDiscip.setBounds(88, 143, 401, 20);
		contentPane.add(cmbTurmaDiscip);
		
		JLabel lblPrRequisitos = new JLabel("Professor:");
		lblPrRequisitos.setBounds(10, 181, 68, 13);
		contentPane.add(lblPrRequisitos);
		
		cmbTurmaProf = new JComboBox<Professor>();
		cmbTurmaProf.setBounds(88, 177, 401, 20);
		contentPane.add(cmbTurmaProf);
		
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
		
		TurmaController turmaController = new TurmaController(tfTurmaAno, tfTurmaSemestre,
				tfTurmaDiaSemana, tfTurmaHorario, cmbTurmaDiscip, cmbTurmaProf,
				tfPosicao, taLista, entityManager);
		
		btnAdicionar.addActionListener(turmaController);
		btnConsultar.addActionListener(turmaController);
		btnExcluir.addActionListener(turmaController);
		btnListar.addActionListener(turmaController);

	}
}
