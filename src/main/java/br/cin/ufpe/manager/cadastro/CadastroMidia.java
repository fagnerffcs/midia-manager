package br.cin.ufpe.manager.cadastro;

import java.util.ArrayList;
import java.util.List;

import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.exception.MidiaNaoEncontradaException;
import br.cin.ufpe.manager.persistence.RepositorioManager;
import br.cin.ufpe.manager.persistence.arquivo.RepositorioManagerArquivo;
import br.cin.ufpe.manager.persistence.bd.RepositorioManagerBD;
import br.cin.ufpe.manager.persistence.lista.RepositorioManagerLista;
import br.cin.ufpe.manager.persistence.vetor.RepositorioManagerVetor;
import br.cin.ufpe.manager.util.FabricaTipoRepositorio;

public class CadastroMidia {
	private RepositorioManager repositorio;
	
	public CadastroMidia(){
		
	}
	
	public CadastroMidia(TipoRepositorio tipo){
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
	
	public void inserirNovaMidia(Midia p){
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioMidiasArquivo().inserir(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioMidiasBD().inserir(p);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioMidiasLista().inserir(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioMidiasVetor().inserir(p);
		}
	}
	
	public void atualizarMidia(Midia p) {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioMidiasArquivo().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioMidiasBD().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioMidiasLista().atualizar(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioMidiasVetor().atualizar(p);
		}		
	}
	
	public void removerMidia(Midia p) throws MidiaNaoEncontradaException {
		if(repositorio instanceof RepositorioManagerArquivo){
			((RepositorioManagerArquivo) this.repositorio).getRepositorioMidiasArquivo().remover(p);
		} else if(repositorio instanceof RepositorioManagerBD){
			((RepositorioManagerBD) this.repositorio).getRepositorioMidiasBD().remover(p);
		} else if(repositorio instanceof RepositorioManagerLista){
			((RepositorioManagerLista) this.repositorio).getRepositorioMidiasLista().remover(p);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			((RepositorioManagerVetor) this.repositorio).getRepositorioMidiasVetor().remover(p);
		}				
	}

	public List<Midia> listar(){
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioMidiasArquivo().listar();
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioMidiasBD().listar();
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioMidiasLista().listar();
		} else if(repositorio instanceof RepositorioManagerVetor) {
			Midia[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioMidiasVetor().listar();
			List<Midia> lista = new ArrayList<Midia>();
			for (int i = 0; i < vetor.length; i++) {
				lista.add(vetor[i]);
			}
			return lista;
		}
		return null;
	}

	public Midia buscarMidia(Long id) {
		if(repositorio instanceof RepositorioManagerArquivo){
			return ((RepositorioManagerArquivo) this.repositorio).getRepositorioMidiasArquivo().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerBD){
			return ((RepositorioManagerBD) this.repositorio).getRepositorioMidiasBD().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerLista){
			return ((RepositorioManagerLista) this.repositorio).getRepositorioMidiasLista().buscarPorId(id);
		} else if(repositorio instanceof RepositorioManagerVetor) {
			Midia[] vetor = ((RepositorioManagerVetor) this.repositorio).getRepositorioMidiasVetor().listar();
			for (int i = 0; i < vetor.length; i++) {
				if(vetor[i]!=null && vetor[i].getId().equals(id)){
					return vetor[i];
				}
			}
		}
		return null;

	}

}
