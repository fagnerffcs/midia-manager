package br.cin.ufpe.manager.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.cin.ufpe.manager.entity.Pessoa;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.exception.PessoaNaoEncontradaException;
import br.cin.ufpe.manager.persistence.RepositorioManager;
import br.cin.ufpe.manager.persistence.arquivo.RepositorioManagerArquivo;
import br.cin.ufpe.manager.persistence.bd.RepositorioManagerBD;
import br.cin.ufpe.manager.persistence.lista.RepositorioManagerLista;
import br.cin.ufpe.manager.persistence.vetor.RepositorioManagerVetor;
import br.cin.ufpe.manager.util.FabricaTipoRepositorio;

/***
 * 
 * @author Fagner Fernandes
 * Classe de negocio responsavel por manipular as operacoes de CRUD da classe Pessoa
 *
 */
public class CadastroPessoa {
	
	private RepositorioManager repositorio;
	
	public CadastroPessoa(){
		
	}
	
	public CadastroPessoa(TipoRepositorio tipo){
		carregarRepositorio(tipo);
	}
	
	private void carregarRepositorio(TipoRepositorio tipoRepositorio) {
		FabricaTipoRepositorio fabrica = new FabricaTipoRepositorio();
		this.setRepositorio(fabrica.getRepositorio(tipoRepositorio));		
	}

	public RepositorioManager getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(RepositorioManager repositorio) {
		this.repositorio = repositorio;
	}
	
	public void atualizarPessoa(Pessoa p) {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioPessoasArquivo().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioPessoasLista().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().atualizar(p);
		}		
	}
	
	public Pessoa buscarPessoa(Long id){
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioPessoasArquivo().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioPessoasLista().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			Pessoa[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().listar();
			int indice = ((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().getIndice();
			for (int i = 0; i < indice; i++) {
				if(vetor[i].getId().equals(id)){
					return vetor[i];
				}
			}
		}
		return null;		
	}
	
	public void inserirNovaPessoa(Pessoa p){
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioPessoasArquivo().inserir(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().inserir(p);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioPessoasLista().inserir(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().inserir(p);
		}
	}

	public List<Pessoa> listar(){
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioPessoasArquivo().listar();
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().listar();
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioPessoasLista().listar();
		} else if(repositorio instanceof RepositorioManagerVetor) {
			Pessoa[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().listar();
			List<Pessoa> lista = new ArrayList<Pessoa>();
			for (int i = 0; i < vetor.length; i++) {
				lista.add(vetor[i]);
			}
			return lista;
		}
		return null;
	}
	
	public void removerPessoa(Pessoa p) throws PessoaNaoEncontradaException {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioPessoasArquivo().remover(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().remover(p);;
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioPessoasLista().remover(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioPessoasVetor().remover(p);
		}				
	}
	
	public List<Pessoa> listarUsuarios(){
		return ((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().listarUsuarios();
	}

	public List<Pessoa> listarAdministradores(){
		return ((RepositorioManagerBD) this.repositorio).getRepositorioPessoasBD().listarAdministradores();
	}

}
