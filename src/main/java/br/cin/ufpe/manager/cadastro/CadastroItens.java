package br.cin.ufpe.manager.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.exception.ItemNaoEncontradoException;
import br.cin.ufpe.manager.persistence.RepositorioManager;
import br.cin.ufpe.manager.persistence.arquivo.RepositorioManagerArquivo;
import br.cin.ufpe.manager.persistence.bd.RepositorioManagerBD;
import br.cin.ufpe.manager.persistence.lista.RepositorioManagerLista;
import br.cin.ufpe.manager.persistence.vetor.RepositorioManagerVetor;
import br.cin.ufpe.manager.util.FabricaTipoRepositorio;

public class CadastroItens {
	private RepositorioManager repositorio;
	
	public CadastroItens(){
		
	}
	
	public CadastroItens(TipoRepositorio tipo){
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
	
	public void inserirNovoItem(ItemMidia i){
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioItensArquivo().inserir(i);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioItensBD().inserir(i);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioItensLista().inserir(i);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioItensVetor().inserir(i);
		}
	}
	
	public void atualizarItem(ItemMidia i) {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioItensArquivo().atualizar(i);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioItensBD().atualizar(i);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioItensLista().atualizar(i);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioItensVetor().atualizar(i);
		}		
	}
	
	public void removerItem(ItemMidia i) throws ItemNaoEncontradoException {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioItensArquivo().remover(i);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioItensBD().remover(i);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioItensLista().remover(i);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioItensVetor().remover(i);
		}				
	}

	public List<ItemMidia> listar(){
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioItensArquivo().listar();
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioItensBD().listar();
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioItensLista().listar();
		} else if(repositorio instanceof RepositorioManagerVetor) {
			ItemMidia[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioItensVetor().listar();
			List<ItemMidia> lista = new ArrayList<ItemMidia>();
			for (int i = 0; i < vetor.length; i++) {
				lista.add(vetor[i]);
			}
			return lista;
		}
		return null;
	}

	public ItemMidia buscarItem(Long id) {
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioItensArquivo().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioItensBD().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioItensLista().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			ItemMidia[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioItensVetor().listar();
			for (int i = 0; i < vetor.length; i++) {
				if(vetor[i]!=null && vetor[i].getId().equals(id)){
					return vetor[i];
				}
			}
		}
		return null;
	}

}
