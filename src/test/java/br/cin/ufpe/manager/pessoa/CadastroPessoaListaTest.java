package br.cin.ufpe.manager.pessoa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.cin.ufpe.manager.cadastro.CadastroPessoa;
import br.cin.ufpe.manager.entity.Pessoa;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.entity.Usuario;
import br.cin.ufpe.manager.exception.PessoaNaoEncontradaException;

/***
 * 
 * @author Fagner Fernandes
 * Classe responsavel por testar a classe ControladorPessoa
 */

@RunWith(value=JUnit4.class)
public class CadastroPessoaListaTest {
	
	@Test
	public void testarAtualizacao(){
		CadastroPessoa cadastroPessoa = new CadastroPessoa(TipoRepositorio.LISTA);
		for (int i = 0; i < 10; i++) {
			Usuario u = new Usuario();
			u.setId(new Long(i+1));
			u.setCpf("123.456.789-"+i+(i+1));
			u.setNome("John "+ (i+1));
			cadastroPessoa.inserirNovaPessoa(u);
		}
		
		Usuario usu = (Usuario) cadastroPessoa.buscarPessoa(new Long(1));
		usu.setNome("John Macloud");
		cadastroPessoa.atualizarPessoa(usu);
		Usuario usuRepo = (Usuario) cadastroPessoa.buscarPessoa(usu.getId());
		Assert.assertTrue(usuRepo.getNome().equals(usu.getNome()));
	}
	
	@Test
	public void testarInsercao(){
		CadastroPessoa cadastroPessoa = new CadastroPessoa(TipoRepositorio.LISTA);
		for (int i = 0; i < 10; i++) {
			Usuario u = new Usuario();
			u.setId(new Long(i+1));
			u.setCpf("123.456.789-"+i+(i+1));
			u.setNome("John "+ (i+1));
			cadastroPessoa.inserirNovaPessoa(u);
		}
		
		Usuario usu = (Usuario) cadastroPessoa.buscarPessoa(new Long(1));
		Assert.assertNotNull(usu);
	}
	
	@Test
	public void testarListar(){
		CadastroPessoa cadastroPessoa = new CadastroPessoa(TipoRepositorio.LISTA);
		for (int i = 0; i < 10; i++) {
			Usuario u = new Usuario();
			u.setId(new Long(i+1));
			u.setCpf("123.456.789-"+i+(i+1));
			u.setNome("John "+ (i+1));
			cadastroPessoa.inserirNovaPessoa(u);
		}
		
		List<Pessoa> lista = cadastroPessoa.listar();
		Assert.assertTrue(lista.size()>0);
	}	
	
	@Test
	public void testarRemocao(){
		CadastroPessoa cps = new CadastroPessoa(TipoRepositorio.LISTA);
		for (int i = 0; i < 10; i++) {
			Usuario u = new Usuario();
			u.setId(new Long(i+1));
			u.setCpf("123.456.789-"+i+(i+1));
			u.setNome("John "+ (i+1));
			cps.inserirNovaPessoa(u);
		}
		
		Usuario usu = (Usuario) cps.buscarPessoa(new Long(1));
		try {
			cps.removerPessoa(usu);
		} catch (PessoaNaoEncontradaException e) {
			e.printStackTrace();
		}
		usu = (Usuario) cps.buscarPessoa(new Long(1));
		Assert.assertNull(usu);
	}	

}
