package br.cin.ufpe.manager;

import java.util.Scanner;

import br.cin.ufpe.manager.entity.TipoRepositorio;


public class Principal {

	public static void main(String[] args) {
		
		System.out.println("Bem-vindo ao sistema de gerenciamento de m�dias.");
		imprimirMenuPrincipal();
		Scanner in = new Scanner(System.in);
		try{
			int opcaoRepo = in.nextInt();
			switch (opcaoRepo) {
			case 1:
				break;

			default:
				break;
			}
		}catch(NumberFormatException nfe){
			System.out.println("Informe uma op��o de reposit�rio v�lida.");
			System.exit(-1);
		}
	}

	private static void imprimirMenuPrincipal() {
		System.out.println("Informe o tipo do reposit�rio a ser utilizado: ");
		int cont = 1;
		for(TipoRepositorio tipo : TipoRepositorio.values()){
			System.out.println(cont + " - " + tipo);
			cont++;
		}
	}

}
