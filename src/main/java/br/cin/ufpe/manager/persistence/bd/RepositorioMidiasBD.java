package br.cin.ufpe.manager.persistence.bd;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.interfaces.IRepositorio;
import br.cin.ufpe.manager.util.FabricaEntityManager;

public class RepositorioMidiasBD implements IRepositorio<Midia> {
	
	private EntityManager em;
	
	public RepositorioMidiasBD() {
	}
	
	public RepositorioMidiasBD(String pu){
		this.em = FabricaEntityManager.getEntityManager(pu);
	}

	@SuppressWarnings("unchecked")
	public List<Midia> listar() {
		return em.createQuery("SELECT m FROM Midia m").getResultList();
	}

	public void inserir(Midia m) {
		Session session = (Session) em.getDelegate();
		session.persist(m);
		session.flush();
	}

	public void remover(Midia m) {
		Midia midia = buscarPorId(m.getId());
		Session session = (Session) em.getDelegate();
		session.delete(midia);
		session.flush();
	}

	public void atualizar(Midia m) {
		em.merge(m);
	}

	public Midia buscarPorId(Long id) {
		List<Midia> lista = listar();
		for (Midia m : lista) {
			if(m.getId().equals(id)){
				return m;
			}
		}
		return null;
	}

}
