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
		List<Pessoa> lista = listar();
		for (Pessoa pessoa : lista) {
			if(pessoa.getId().equals(id)){
				return pessoa;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarUsuarios() {
		return em.createQuery("SELECT u FROM Usuario u").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> listarAdministradores() {
		return em.createQuery("SELECT a FROM Administrador a").getResultList();
	}	

}
