package br.cin.ufpe.manager.persistence.arquivo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.exception.ItemNaoEncontradoException;
import br.cin.ufpe.manager.interfaces.IRepositorio;
import br.cin.ufpe.manager.util.FileHandler;

public class RepositorioItensArquivo implements IRepositorio<ItemMidia> {
	
	private final String nomeArquivo = "itens.txt";
	private FileHandler fh = new FileHandler();
	private List<ItemMidia> itens;
	private static Logger log = LoggerFactory.getLogger(RepositorioPessoasArquivo.class);

	
	public RepositorioItensArquivo(){
		this.itens = fh.carregarDadosItens(nomeArquivo);
	}

	public List<ItemMidia> listar() {
		return this.itens;
	}

	public void inserir(ItemMidia it) {
		this.itens.add(it);
		fh.escreverItensNoArquivo(nomeArquivo, itens);
	}

	public void remover(ItemMidia i) throws ItemNaoEncontradoException {
		ItemMidia item = buscarPorId(i.getId());
		if(item==null){
			throw new ItemNaoEncontradoException(i.getId());
		}
		itens.remove(item);
		fh.escreverItensNoArquivo(nomeArquivo, itens);
	}

	public void atualizar(ItemMidia i) {
		ItemMidia itemAnterior = buscarPorId(i.getId());
		itens.remove(itemAnterior);
		itens.add(i);
		fh.escreverItensNoArquivo(nomeArquivo, itens);		
	}

	public ItemMidia buscarPorId(Long id) {
		for (ItemMidia i : itens) {
			if(i.getId().equals(id)){
				return i;
			}
		}
		log.info("ID " + id + " nao encontrado na lista de itens.");
		return null;
	}

}
