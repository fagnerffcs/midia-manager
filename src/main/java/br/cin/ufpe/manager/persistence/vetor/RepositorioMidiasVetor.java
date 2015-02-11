package br.cin.ufpe.manager.persistence.vetor;

import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.exception.MidiaNaoEncontradaException;
import br.cin.ufpe.manager.interfaces.IRepositorioVetor;

public class RepositorioMidiasVetor implements IRepositorioVetor<Midia> {
	
	private Midia[] midias = new Midia[10];
	private double loadFactor;
	private int indice;
	
	private void duplicarCapacidade(){
		this.indice = 0;
		Midia[] temp = new Midia[midias.length*2];
		for (int i = 0; i < midias.length; i++) {
			temp[i] = midias[i];
			indice = indice+1;
		}
		midias = temp;
	}
	
	public Midia[] listar(){
		return midias;
	}

	public void inserir(Midia t) {
		if(loadFactor >= 0.75){
			duplicarCapacidade();
		}
		midias[indice] = t;		
		this.indice = this.indice+1;
		loadFactor = (double) indice/midias.length;
	}

	public void remover(Midia m) throws MidiaNaoEncontradaException {
		int posicao = buscarPorId(m.getId());
		if(posicao==-1){
			throw new MidiaNaoEncontradaException(m.getId());
		}
		midias[posicao] = null;
		reordenarVetor();
	}

	private void reordenarVetor() {
		for (int posAtual = 0; posAtual < indice; posAtual++) {
			if(midias[posAtual]==null && posAtual < indice){
				midias[posAtual] = midias[posAtual+1]; 
			}
		}
		indice=indice-1;
	}

	public void atualizar(Midia midiaAtualizada) {
		int p = buscarPorId(midiaAtualizada.getId());
		midias[p] = midiaAtualizada;
	}

	public int buscarPorId(Long id) {
		for (int i = 0; i < indice; i++) {
			if(midias[i].getId().equals(id)){
				return i;
			}
		}
		return -1;
	}

}
