package br.cin.ufpe.manager.persistence.lista;

import java.util.ArrayList;
import java.util.List;

import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.interfaces.IRepositorio;

public class RepositorioItensLista implements IRepositorio<ItemMidia> {
	
	private List<ItemMidia> itens;
	
	public RepositorioItensLista(){
		this.itens = new ArrayList<ItemMidia>();
	}
	
	public List<ItemMidia> listar() {
		return itens;
	}

	public void inserir(ItemMidia i) {
		itens.add(i);
	}

	public void remover(ItemMidia i) {
		itens.remove(i);
	}

	public void atualizar(ItemMidia i) {
		ItemMidia itemAnterior = buscarPorId(i.getId());
		itens.remove(itemAnterior);
		itens.add(i);
	}

	public ItemMidia buscarPorId(Long id) {
		for (ItemMidia ItemMidia : itens) {
			if(ItemMidia.getId().equals(id)){
				return ItemMidia;
			}
		}
		return null;
	}

}
