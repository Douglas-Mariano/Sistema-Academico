package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Turma;

public class TurmaDao {
	
	private EntityManager entityManager;
	
	public TurmaDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Turma adicionaTurma(Turma turma) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(turma);
		  entityManager.getTransaction().commit();
		  return turma;
	}
	
	public void excluiTurma(Integer id){
		  entityManager.getTransaction().begin();
		  Turma turma = consultaTurma(id);
		  entityManager.remove(turma);
		  entityManager.getTransaction().commit();
		}
	
	public Turma consultaTurma(Integer id) {
		return entityManager.find(Turma.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Turma> consultaTurmas() {
		return entityManager.createQuery("From Turma").getResultList();
	}
		

}
