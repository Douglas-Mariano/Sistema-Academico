package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Aluno;

public class AlunoDao {
	
	private EntityManager entityManager;
	
	public AlunoDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Aluno adicionaAluno(Aluno aluno) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(aluno);
		  entityManager.getTransaction().commit();
		  return aluno;
	}
	
	public void excluiAluno(Integer id){
		  entityManager.getTransaction().begin();
		  Aluno aluno = consultaAluno(id);
		  entityManager.remove(aluno);
		  entityManager.getTransaction().commit();
		}
	
	public Aluno consultaAluno(Integer id) {
		return entityManager.find(Aluno.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> consultaAlunos() {
		return entityManager.createQuery("From Aluno").getResultList();
	}

}
