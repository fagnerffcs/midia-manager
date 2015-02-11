package br.cin.ufpe.manager.itens;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.cin.ufpe.manager.cadastro.CadastroItens;
import br.cin.ufpe.manager.cadastro.CadastroMidia;
import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.exception.ItemNaoEncontradoException;

/***
 * 
 * @author Fagner Fernandes
 * Classe responsavel por testar a classe ControladorItens
 */
public class CadastroItensTest {

	@Test
	public void testarCadastros(){
		for(TipoRepositorio tipo : TipoRepositorio.values()){
			testarAtualizacao(tipo);
			testarInsercao(tipo);
			testarListar(tipo);
			testarRemocao(tipo);
		}
	}
	
	public void testarAtualizacao(TipoRepositorio tipo){
		CadastroMidia cm = new CadastroMidia(tipo);
		Midia midia = new Midia();
		if(!tipo.equals(TipoRepositorio.BD)){
			midia.setId(new Long(1));
		}
		midia.setNome("Backup 01");
		cm.inserirNovaMidia(midia);
		CadastroItens controladorItens = new CadastroItens(tipo);
		for (int i = 0; i < 10; i++) {
			ItemMidia item = new ItemMidia();
			if(!tipo.equals(TipoRepositorio.BD)){
				item.setId(new Long(i+1));
			}
			item.setDescricao("Big Bang Theory S"+(i+1)+"E"+(i+1));
			item.setMidia(midia);
			controladorItens.inserirNovoItem(item);
		}
		
		ItemMidia itemMidia = (ItemMidia) controladorItens.buscarItem(new Long(1));
		itemMidia.setDescricao("Big Bang Theory S01E01");
		controladorItens.atualizarItem(itemMidia);
		ItemMidia itemMidiaRepo = (ItemMidia) controladorItens.buscarItem(itemMidia.getId());
		Assert.assertTrue(itemMidiaRepo.getDescricao().equals(itemMidia.getDescricao()));
	}
	
	public void testarInsercao(TipoRepositorio tipo){
		CadastroMidia cadastroMidia = new CadastroMidia(tipo);
		Midia midia = new Midia();
		if(!tipo.equals(TipoRepositorio.BD)){
			midia.setId(new Long(1));
		}
		midia.setNome("Backup 01");
		cadastroMidia.inserirNovaMidia(midia);		
		CadastroItens cm = new CadastroItens(tipo);
		for (int i = 0; i < 10; i++) {
			ItemMidia item = new ItemMidia();
			if(!tipo.equals(TipoRepositorio.BD)){
				item.setId(new Long(i+1));
			}
			item.setDescricao("Breaking Bad S01E"+ (i+1));
			item.setMidia(midia);
			cm.inserirNovoItem(item);
		}
		
		ItemMidia usu = (ItemMidia) cm.buscarItem(new Long(1));
		Assert.assertNotNull(usu);
	}
	
	public void testarListar(TipoRepositorio tipo){
		CadastroMidia cadastroMidia = new CadastroMidia(tipo);
		Midia midia = new Midia();
		if(!tipo.equals(TipoRepositorio.BD)){
			midia.setId(new Long(1));
		}
		midia.setNome("Backup 01");
		cadastroMidia.inserirNovaMidia(midia);
		CadastroItens cadastroItens = new CadastroItens(tipo);
		for (int i = 0; i < 10; i++) {
			ItemMidia item = new ItemMidia();
			if(!tipo.equals(TipoRepositorio.BD)){
				item.setId(new Long(i+1));
			}
			item.setDescricao("Backup "+ (i+1));
			item.setMidia(midia);
			cadastroItens.inserirNovoItem(item);
		}
		
		List<ItemMidia> lista = cadastroItens.listar();
		Assert.assertTrue(lista.size()>0);
	}	
	
	public void testarRemocao(TipoRepositorio tipo){
		CadastroMidia cadastroMidia = new CadastroMidia(tipo);
		Midia midia = new Midia();
		if(!tipo.equals(TipoRepositorio.BD)){
			midia.setId(new Long(1));
		}
		midia.setNome("Backup 01");
		cadastroMidia.inserirNovaMidia(midia);		
		CadastroItens cItens = new CadastroItens(tipo);
		for (int i = 0; i < 10; i++) {
			ItemMidia item = new ItemMidia();
			if(!tipo.equals(TipoRepositorio.BD)){
				item.setId(new Long(i+1));
			}
			item.setDescricao("Item Backup "+ (i+1));
			item.setMidia(midia);
			cItens.inserirNovoItem(item);
		}
		
		ItemMidia item = (ItemMidia) cItens.buscarItem(new Long(1));
		try {
			cItens.removerItem(item);
		} catch (ItemNaoEncontradoException e) {
			e.printStackTrace();
		}
		item = (ItemMidia) cItens.buscarItem(new Long(1));
		Assert.assertNull(item);
	}	

}
