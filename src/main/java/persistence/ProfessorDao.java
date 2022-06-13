package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Professor;

public class ProfessorDao {
	
	private EntityManager entityManager;
	
	public ProfessorDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Professor adicionaProfessor(Professor professor) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(professor);
		  entityManager.getTransaction().commit();
		  return professor;
	}
	
	public void excluiProfessor(Integer id){
		  entityManager.getTransaction().begin();
		  Professor professor = consultaProfessor(id);
		  entityManager.remove(professor);
		  entityManager.getTransaction().commit();
		}
	
	public Professor consultaProfessor(Integer id) {
		return entityManager.find(Professor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> consultaProfessores() {
		return entityManager.createQuery("From Professor").getResultList();
	}

}
