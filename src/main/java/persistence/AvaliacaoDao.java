package persistence;

import java.util.List;

import javax.persistence.EntityManager;

import model.Avaliacao;

public class AvaliacaoDao {
	
	private EntityManager entityManager;
	
	public AvaliacaoDao (EntityManager em) {
		this.entityManager = em;
	}
	
	public Avaliacao adicionaAvaliacao(Avaliacao avaliacao) {
		  entityManager.getTransaction().begin();
		  entityManager.persist(avaliacao);
		  entityManager.getTransaction().commit();
		  return avaliacao;
	}
	
	public void excluiAvaliacao(Integer id){
		  entityManager.getTransaction().begin();
		  Avaliacao avaliacao = consultaAvaliacao(id);
		  entityManager.remove(avaliacao);
		  entityManager.getTransaction().commit();
		}
	
	public Avaliacao consultaAvaliacao(Integer id) {
		return entityManager.find(Avaliacao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Avaliacao> consultaAvaliacoes() {
		return entityManager.createQuery("From Avaliacao").getResultList();
	}
		
}
