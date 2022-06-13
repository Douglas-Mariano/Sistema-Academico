package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private static EntityManagerFactory 
	entityManagerFactory = Persistence
	.createEntityManagerFactory("bancoDeDadosPU");
	
	private static EntityManager entityManager = 
	entityManagerFactory.createEntityManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAluno = new JButton("Aluno");
		btnAluno.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaAluno frame = new TelaAluno(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
		}
		});
		btnAluno.setBounds(10, 108, 190, 65);
		contentPane.add(btnAluno);
		
		JButton btnCurso = new JButton("Curso");
		btnCurso.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCurso.setBounds(10, 32, 190, 65);
		btnCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCurso frame = new TelaCurso(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		contentPane.add(btnCurso);
		
		JButton btnDisciplina = new JButton("Disciplina");
		btnDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDisciplina.setBounds(210, 32, 190, 65);
		btnDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaDisciplina frame = new TelaDisciplina(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		contentPane.add(btnDisciplina);
		
		JButton btnProfessor = new JButton("Professor");
		btnProfessor.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaProfessor frame = new TelaProfessor(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		btnProfessor.setBounds(210, 108, 190, 65);
		contentPane.add(btnProfessor);
		
		JButton btnAvaliacao = new JButton("Avaliações");
		btnAvaliacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaAvaliacao frame = new TelaAvaliacao(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		btnAvaliacao.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAvaliacao.setBounds(10, 183, 190, 65);
		contentPane.add(btnAvaliacao);
		
		JButton btnTurma = new JButton("Turma");
		btnTurma.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaTurma frame = new TelaTurma(entityManager);
					frame.setVisible(true);
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		btnTurma.setBounds(210, 183, 190, 65);
		contentPane.add(btnTurma);
	}
}
