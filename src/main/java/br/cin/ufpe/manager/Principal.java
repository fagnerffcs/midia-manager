package br.cin.ufpe.manager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.cin.ufpe.manager.business.ControladorPrincipal;
import br.cin.ufpe.manager.entity.Administrador;
import br.cin.ufpe.manager.entity.Midia;
import br.cin.ufpe.manager.entity.TipoRepositorio;
import br.cin.ufpe.manager.entity.Usuario;

public class Principal {

	public static void main(String[] args) {

		System.out.println("Bem-vindo ao sistema de gerenciamento de mídias.");
		imprimirMenuPrincipal();
		
		Scanner in = new Scanner(System.in);
		
		try {
			int opcaoRepo = in.nextInt();
			TipoRepositorio tipo;
			//inicializa a versao do repositorio de acordo com o selecionado pelo usuario
			switch (opcaoRepo) {
			case 1:
				tipo = TipoRepositorio.ARQUIVO;
				break;
			case 2:
				tipo = TipoRepositorio.BD;
				break;
			case 3:
				tipo = TipoRepositorio.LISTA;
				break;
			default:
				tipo = TipoRepositorio.VETOR;
				break;
			}
			
			//inicializa a classe de controle do sistema
			ControladorPrincipal controladorPrincipal = new ControladorPrincipal(tipo);
			
			//variavel de controle do fluxo principal
			boolean podeContinuar = true;
			
			//laço principal do sistema
			while (podeContinuar) {

				// imprimir submenu
				imprimirSubMenu();
				
				try{
					//captura a opcao digitada pelo usuario
					int opcaoSubMenu = in.nextInt();
					// inserir
					if(opcaoSubMenu==1){
						imprimirMenuEntidade();
						
						String valor = in.next();
						//insere um objeto Administrador
						if(valor.equals("A")){
							inserirAdmin(in, controladorPrincipal);
						} else if(valor.equals("U")){ //insere um objeto usuario
							inserirUsuario(in, controladorPrincipal);
						} else if(valor.equals("M")){ //insere um objeto Midia
							inserirMidia(in, controladorPrincipal);
						} else if(valor.equals("I")){ //insere um objeto Item
							inserirItemMidia(in, controladorPrincipal);
						} else {
							System.out.println("Opção inválida.");
						}
						
					} else if(opcaoSubMenu==2) { // remover
						imprimirMenuEntidade();
						String valor = in.next();
						
						//insere um objeto Administrador
						if(valor.equals("A")){
							removerAdmin(in, controladorPrincipal);
						} else if(valor.equals("U")){ //insere um objeto usuario
							removerUsuario(in, controladorPrincipal);
						} else if(valor.equals("M")){ //insere um objeto Midia
							removerMidia(in, controladorPrincipal);
						} else if(valor.equals("I")){ //insere um objeto Item
							removerItemMidia(in, controladorPrincipal);
						} else {
							System.out.println("Opção inválida.");
						}
						
					} else if(opcaoSubMenu==3) { // atualizar
						imprimirMenuEntidade();
						String valor = in.next();						
						//atualizar um objeto Administrador
						if(valor.equals("A")){
							atualizarAdmin(in, controladorPrincipal);
						} else if(valor.equals("U")){ //atualizar um objeto usuario
							atualizarUsuario(in, controladorPrincipal);
						} else if(valor.equals("M")){ //atualizar um objeto Midia
							atualizarMidia(in, controladorPrincipal);
						} else if(valor.equals("I")){ //atualizar um objeto Item
							atualizarItemMidia(in, controladorPrincipal);
						} else {
							System.out.println("Opção inválida.");
						}

					} else if(opcaoSubMenu==4) { //listar
						imprimirMenuEntidade();
						String valor = in.next();
						//listar Administradores
						if(valor.equals("A")){
							System.out.println(controladorPrincipal.getCadastroPessoa().listarAdministradores());
						} else if(valor.equals("U")){ //listar Usuarios
							System.out.println(controladorPrincipal.getCadastroPessoa().listarUsuarios());
						} else if(valor.equals("M")){ //listar Midias
							System.out.println(controladorPrincipal.getCadastroMidia().listar());
						} else if(valor.equals("I")){ //listar Itens
							System.out.println(controladorPrincipal.getCadastroItens().listar());
						} else {
							System.out.println("Opção inválida.");
						}
						
					} else {
						System.out.println("Opção inválida.");
					}
					
					System.out.println("Deseja continuar? (S/N)");
					podeContinuar = in.next().equals("S");
					
				}catch(InputMismatchException ime){
					System.out.println("Opção inválida!");
				}

			}
		} catch (NumberFormatException nfe) {
			System.out.println("Informe uma opção de repositório válida.");
			System.exit(-1);
		}
		
		System.out.println("Até a próxima!");
	}

	private static void atualizarItemMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void atualizarMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void atualizarUsuario(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void atualizarAdmin(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void removerItemMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void removerMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void removerUsuario(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void removerAdmin(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void inserirItemMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		// TODO Auto-generated method stub
		
	}

	private static void inserirMidia(Scanner in, ControladorPrincipal controladorPrincipal) {
		System.out.println("Informe o id do usuário: ");
		Long id = in.nextLong();
		Usuario u = (Usuario) controladorPrincipal.getCadastroPessoa().buscarPessoa(id);
		if(u.getMidias()!=null){
			List<Midia> lista = new ArrayList<Midia>();
			boolean inserindoMidias = true;
			while(inserindoMidias){
				Midia m = new Midia();
				System.out.println("Informe o nome da mídia: ");
				m.setNome(in.next());
				m.setDono(u);
				//insere a midia
				lista.add(m);
				controladorPrincipal.getCadastroMidia().inserirNovaMidia(m);
				System.out.println("Deseja continuar inseririndo as mídias do usuário? (S/N) ");
				String resposta = in.next();
				inserindoMidias = resposta.equalsIgnoreCase("S");
			}
			u.setMidias(lista);
			controladorPrincipal.getCadastroPessoa().atualizarPessoa(u);
		} else {
			
		}
	}

	private static void inserirUsuario(Scanner in, ControladorPrincipal controladorPrincipal) {
		Usuario usu = new Usuario();
		System.out.println("Informe o nome do Usuário: ");
		usu.setNome(in.next());
		System.out.println("Informe o CPF do Administrador: ");
		usu.setCpf(in.next());
		controladorPrincipal.getCadastroPessoa().inserirNovaPessoa(usu);
	}

	private static void inserirAdmin(Scanner in, ControladorPrincipal controladorPrincipal) {
		Administrador adm = new Administrador();
		System.out.println("Informe o nome do Administrador: ");
		adm.setNome(in.next());
		System.out.println("Informe o CPF do Administrador: ");
		adm.setCpf(in.next());
		System.out.println("Informe o email do Administrador: ");
		adm.setEmail(in.next());
		controladorPrincipal.getCadastroPessoa().inserirNovaPessoa(adm);
		System.out.println(adm.getNome() + " cadastrado com sucesso.");
	}

	private static void imprimirMenuPrincipal() {
		System.out.println("Informe o tipo do repositório a ser utilizado: ");
		int cont = 1;
		for (TipoRepositorio tipo : TipoRepositorio.values()) {
			System.out.println(cont + " - " + tipo);
			cont++;
		}
	}

	private static void imprimirSubMenu() {
		System.out.println("Informe a opção desejada:");
		System.out.println("1 - Cadastrar Novo");
		System.out.println("2 - Remover");
		System.out.println("3 - Atualizar");
		System.out.println("4 - Listar");
	}	
	
	private static void imprimirMenuEntidade() {
		System.out.println("A - Administrador");
		System.out.println("U - Usuário");
		System.out.println("M - Midia");
		System.out.println("I - Item");
	}	
	
}
