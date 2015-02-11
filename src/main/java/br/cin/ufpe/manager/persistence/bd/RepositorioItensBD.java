package br.cin.ufpe.manager.persistence.bd;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.interfaces.IRepositorio;
import br.cin.ufpe.manager.util.FabricaEntityManager;

public class RepositorioItensBD implements IRepositorio<ItemMidia> {
	
	private EntityManager em;
	
	public RepositorioItensBD(){
		
	}
	
	public RepositorioItensBD(String pu){
		this.em = FabricaEntityManager.getEntityManager(pu);
	}

	@SuppressWarnings("unchecked")
	public List<ItemMidia> listar() {
		return em.createQuery("SELECT i FROM ItemMidia i").getResultList();
	}

	public void inserir(ItemMidia i) {
		Session session = (Session) em.getDelegate();
		session.persist(i);
		session.flush();
	}

	public void remover(ItemMidia i) {
		Session session = (Session) em.getDelegate();
		ItemMidia item = buscarPorId(i.getId());
		session.delete(item);
		session.flush();
	}

	public void atualizar(ItemMidia i) {
		Session session = (Session) em.getDelegate();
		session.merge(i);
		session.flush();
	}

	public ItemMidia buscarPorId(Long id) {
		List<ItemMidia> lista = listar();
		for (ItemMidia itemMidia : lista) {
			if(itemMidia.getId().equals(id)){
				return itemMidia;
			}
		}
		return null;
	}

}
