package unitunes;

import java.util.Scanner;

import controller.GerenciadorCadastros;
import model.Cadastro;

public class UniTunes {
	private Scanner keyboard;
	private boolean isLoggedIn;
	private GerenciadorCadastros gerenciadorCadastros;
	private Cadastro cadastro;
	
	public UniTunes() {
		this.keyboard = new Scanner(System.in); 
		this.isLoggedIn = false;
		this.gerenciadorCadastros = new GerenciadorCadastros();
	}
	
	public void start() {
		System.out.println("\r\n"
				+ "              _ _______                    \r\n"
				+ "             (_)__   __|                   \r\n"
				+ "  _   _ _ __  _   | |_   _ _ __   ___  ___ \r\n"
				+ " | | | | '_ \\| |  | | | | | '_ \\ / _ \\/ __|\r\n"
				+ " | |_| | | | | |  | | |_| | | | |  __/\\__ \\\r\n"
				+ "  \\__,_|_| |_|_|  |_|\\__,_|_| |_|\\___||___/\r\n"
				+ "                                           \r\n"
				+ "                                           \r\n"
				+ "");	

		int option = 3;	
		System.out.println("Bem-vindo ao uniTunes!");
		
		while(option != 0 || option != 1 || option != 2) {
			if(this.isLoggedIn) {
				System.out.println("\nSeja bem-vindo " + cadastro.getNome() + ".");
				eventLoop();
				break;
			}
			
			System.out.println();
			System.out.println("Digite 1 para fazer log-in");
			System.out.println("Digite 2 para se cadastrar");
			System.out.println("Digite 0 para sair");
			System.out.println();
			
			option = keyboard.nextInt();
			keyboard.nextLine();		

			if(option == 0)
				break;
			if(option == 1)
				login();
			else if(option == 2)
				signup();
		}
	}

	private void eventLoop() {
		System.out.println("");
		
	}

	private void signup() {
		System.out.println();
		
		String email = "";

		System.out.println("Digite um email:");
		email = keyboard.nextLine();
		
		while(!email.contains("@")) {
			System.out.println("Digite um email valido:");
			email = keyboard.nextLine();
		}
		
		System.out.println("Digite uma senha:");
		String password = keyboard.nextLine();		
		
		System.out.println("Digite seu nome:");
		String nome = keyboard.nextLine();		
		
		System.out.println("Digite seu username:");
		String username = keyboard.nextLine();		
		
		this.gerenciadorCadastros.criarCadastro(email, password, username, nome, -1);
		
		System.out.println("\nLogue para confirmar os dados: ");
	}

	private void login() {
		System.out.println();
		String email = "";
		
		while(!this.isLoggedIn) {		
			System.out.println("Digite seu email:");
			email = keyboard.nextLine();
			
			while(!email.contains("@")) {
				System.out.println("Digite um email valido:");
				email = keyboard.nextLine();
			}
			
			System.out.println("Digite sua senha:");
			String password = keyboard.nextLine();		
			
			this.cadastro = gerenciadorCadastros.autenticar(email, password);
			
			if(cadastro.getIdCadastro() > 0) {
				this.isLoggedIn = true;
			} else {
				System.out.println();
				System.out.println("Cadastro " + email + " nao encontrado.");
				break;
			}			
		}
		
	}
	
	public void close() {
		System.out.println();
		System.out.println("Encerrando aplicativo...");
	}
}
