package br.cin.ufpe.manager.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.manager.entity.Administrador;
import br.cin.ufpe.manager.entity.ItemMidia;
import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.entity.Pessoa;
import br.cin.ufpe.manager.entity.Usuario;

public class FileHandler {
	
	private static Logger log = LoggerFactory.getLogger(FileHandler.class);
	
	private BufferedReader carregarLeitorDados(String nomeArq) {
		File f = new File(nomeArq);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			log.error("Arquivo " + nomeArq + " não encontrado.");
		}
		return br;
	}

	public List<ItemMidia> carregarDadosItens(String nomeArq) {
		List<ItemMidia> lista = new ArrayList<ItemMidia>();
		BufferedReader br = carregarLeitorDados(nomeArq);
		String linha = null;
		try {
			while((linha = br.readLine())!=null){
				String[] vetorItem = linha.split(",");
				ItemMidia item = new ItemMidia();
				item.setId(Long.parseLong(vetorItem[0]));
				item.setDescricao(vetorItem[1]);
				lista.add(item);
			}
		} catch (NumberFormatException e) {
			log.error("Erro ao converter id na linha " + linha);
		} catch (IOException e) {
			log.error("Erro ao ler o arquivo " + nomeArq);
		}
		return lista;
	}
	
	public List<Midia> carregarDadosMidia(String nomeArq) {
		List<Midia> lista = new ArrayList<Midia>();
		BufferedReader br = carregarLeitorDados(nomeArq);
		String linha = null;
		try {
			while((linha = br.readLine())!=null){
				String[] vetorMidia = linha.split(",");
				Midia midia = new Midia();
				midia.setId(Long.parseLong(vetorMidia[0]));
				midia.setNome(vetorMidia[1]);
				lista.add(midia);
			}
		} catch (NumberFormatException e) {
			log.error("Erro ao converter id na linha " + linha);
		} catch (IOException e) {
			log.error("Erro ao ler o arquivo " + nomeArq);
		}
		return lista;
	}

	public List<Pessoa> carregarDadosPessoa(String nomeArq) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		BufferedReader br = carregarLeitorDados(nomeArq);
		String linha = null;
		try {
			while((linha = br.readLine())!=null){
				String[] vetorPessoa = linha.split(",");
				if(linha.contains("ADM")){
					Administrador admin = new Administrador();
					admin.setId(Long.parseLong(vetorPessoa[0]));
					admin.setNome(vetorPessoa[1]);
					admin.setCpf(vetorPessoa[2]);
					admin.setEmail(vetorPessoa[3]);
					lista.add(admin);					
				} else { 
					Usuario usuario = new Usuario();
					usuario.setId(Long.parseLong(vetorPessoa[0]));
					usuario.setNome(vetorPessoa[1]);
					usuario.setCpf(vetorPessoa[2]);
					lista.add(usuario);
				}
			}
		} catch (NumberFormatException e) {
			log.error("Erro ao converter id na linha " + linha);
		} catch (IOException e) {
			log.error("Erro ao ler o arquivo " + nomeArq);
		}
		return lista;
	}

	public void escreverPessoasNoArquivo(String nomeArquivo, List<Pessoa> pessoas) {
		File f = new File(nomeArquivo);
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for (Pessoa p : pessoas) {
				bw.write(p.toString());
			}
			bw.close();
		} catch (IOException e) {
			log.error("Erro ao gravar no arquivo.");
		}		
	}

	public void escreverMidiasNoArquivo(String nomeArquivo, List<Midia> midias) {
		File f = new File(nomeArquivo);
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for (Midia m : midias) {
				bw.write(m.toString());
			}
			bw.close();
		} catch (IOException e) {
			log.error("Erro ao gravar no arquivo.");
		}		
	}
	
	public void escreverItensNoArquivo(String nomeArquivo, List<ItemMidia> itens) {
		File f = new File(nomeArquivo);
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			for (ItemMidia it : itens) {
				bw.write(it.toString());
			}
			bw.close();
		} catch (IOException e) {
			log.error("Erro ao gravar no arquivo.");
		}
	}
	

}
