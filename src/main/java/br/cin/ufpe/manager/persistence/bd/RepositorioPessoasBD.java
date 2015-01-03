package br.cin.ufpe.manager.persistence.bd;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.cin.ufpe.manager.entity.Pessoa;
import br.cin.ufpe.manager.interfaces.IRepositorio;
import br.cin.ufpe.manager.util.FabricaEntityManager;

public class RepositorioPessoasBD implements IRepositorio<Pessoa> {
	
	private EntityManager em;
	
	public RepositorioPessoasBD(){
		
	}
	
	public RepositorioPessoasBD(String pu){
		this.em = FabricaEntityManager.getEntityManager(pu);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() {
		return em.createQuery("SELECT p FROM Pessoa p").getResultList();
	}

	public void inserir(Pessoa p) {
		Session session = (Session) em.getDelegate();
		session.persist(p);
		session.flush();		
	}

	public void remover(Pessoa p) {
		Pessoa pessoa = buscarPorId(p.getId());
		Session session = (Session) em.getDelegate();
		session.delete(pessoa);
		session.flush();		
	}

	public void atualizar(Pessoa p) {
		Session session = (Session) em.getDelegate();
		session.merge(p);
		session.flush();
	}

	public Pessoa buscarPorId(Long id) {
		Pessoa pessoa = em.find(Pessoa.class, id);
		return pessoa;
	}

}
