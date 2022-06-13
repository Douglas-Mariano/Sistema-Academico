package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Curso;

public class CursoDao {
	
	private EntityManager entityManager;
	
	public CursoDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Curso adicionaCurso(Curso curso) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(curso);
		  entityManager.getTransaction().commit();
		  return curso;
	}
	
	public void excluiCurso(Integer id){
		  entityManager.getTransaction().begin();
		  Curso curso = consultaCurso(id);
		  entityManager.remove(curso);
		  entityManager.getTransaction().commit();
		}
	
	public Curso consultaCurso(Integer id) {
		return entityManager.find(Curso.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> consultaCursos() {
		return entityManager.createQuery("From Curso").getResultList();
	}
		

}
