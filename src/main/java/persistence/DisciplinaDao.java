package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Disciplina;

public class DisciplinaDao {
	
	private EntityManager entityManager;
	
	public DisciplinaDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Disciplina adicionaDisciplina(Disciplina disciplina) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(disciplina);
		  entityManager.getTransaction().commit();
		  return disciplina;
	}
	
	public void excluiDisciplina(Integer id){
		  entityManager.getTransaction().begin();
		  Disciplina disciplina = consultaDisciplina(id);
		  entityManager.remove(disciplina);
		  entityManager.getTransaction().commit();
		}
	
	public Disciplina consultaDisciplina(Integer id) {
		return entityManager.find(Disciplina.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> consultaDisciplinas() {
		return entityManager.createQuery("From Disciplina").getResultList();
	}
		

}
