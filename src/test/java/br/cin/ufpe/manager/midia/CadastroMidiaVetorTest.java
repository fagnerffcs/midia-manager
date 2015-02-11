package br.cin.ufpe.manager.midia;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.cin.ufpe.manager.cadastro.CadastroMidia;
import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.entity.Usuario;
import br.cin.ufpe.manager.exception.MidiaNaoEncontradaException;

/***
 * 
 * @author Fagner Fernandes
 * Classe responsavel por testar a classe ControladorMidia
 */

@RunWith(value=JUnit4.class)
public class CadastroMidiaVetorTest {
	
	@Test
	public void testarAtualizacao(){
		CadastroMidia controladorMidia = new CadastroMidia(TipoRepositorio.VETOR);
		for (int i = 0; i < 10; i++) {
			Midia m = new Midia();
			m.setId(new Long(i+1));
			m.setNome("Backup "+(i+1));

			Usuario u = new Usuario();
			u.setNome("José");
			u.setCpf("111.111.111-11");
			m.setDono(u);
			controladorMidia.inserirNovaMidia(m);
		}
		
		Midia midia = (Midia) controladorMidia.buscarMidia(new Long(1));
		midia.setNome("Backup Teste");
		controladorMidia.atualizarMidia(midia);
		Midia midiaRepo = (Midia) controladorMidia.buscarMidia(midia.getId());
		Assert.assertTrue(midiaRepo.getNome().equals(midia.getNome()));			
	}
	
	@Test
	public void testarInsercao(){
		CadastroMidia cm = new CadastroMidia(TipoRepositorio.VETOR);
		for (int i = 0; i < 10; i++) {
			Midia m = new Midia();
			m.setId(new Long(i+1));
			m.setNome("Backup "+ (i+1));
			cm.inserirNovaMidia(m);
		}
		
		Midia usu = (Midia) cm.buscarMidia(new Long(1));
		Assert.assertNotNull(usu);
	}
	
	@Test
	public void testarListar(){
		CadastroMidia cm = new CadastroMidia(TipoRepositorio.VETOR);
		for (int i = 0; i < 10; i++) {
			Midia m = new Midia();
			m.setId(new Long(i+1));
			m.setNome("Backup "+ (i+1));
			cm.inserirNovaMidia(m);
		}
		
		List<Midia> lista = cm.listar();
		Assert.assertTrue(lista.size()>0);
	}	
	
	@Test
	public void testarRemocao(){
		CadastroMidia cMidia = new CadastroMidia(TipoRepositorio.VETOR);
		for (int i = 0; i < 10; i++) {
			Midia m = new Midia();
			m.setId(new Long(i+1));
			m.setNome("John "+ (i+1));
			cMidia.inserirNovaMidia(m);
		}
		
		Midia mid = (Midia) cMidia.buscarMidia(new Long(1));
		try {
			cMidia.removerMidia(mid);
		} catch (MidiaNaoEncontradaException e) {
			e.printStackTrace();
		}
		mid = (Midia) cMidia.buscarMidia(new Long(1));
		Assert.assertNull(mid);
	}	

}
