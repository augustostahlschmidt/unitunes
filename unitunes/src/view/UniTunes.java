package view;

import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import controller.DashboardVendas;
import controller.GerenciadorAlbuns;
import controller.GerenciadorCadastros;
import controller.GerenciadorCarteiras;
import controller.GerenciadorFavoritos;
import controller.GerenciadorMidias;
import controller.GerenciadorVendas;
import model.Album;
import model.AlbumPorId;
import model.Cadastro;
import model.Favorito;
import model.Midia;
import model.Venda;

public class UniTunes {
	private Scanner keyboard;
	private boolean isLoggedIn;
	private GerenciadorCadastros gerenciadorCadastros;
	private Cadastro cadastroLogado;
	private boolean isAcademico;
	private GerenciadorMidias gerenciadorMidia;
	private DashboardVendas dashboardVendas;
	private GerenciadorCarteiras gerenciadorCarteiras;
	private GerenciadorVendas gerenciadorVendas;
	private GerenciadorFavoritos gerenciadorFavoritos;
	private GerenciadorAlbuns gerenciadorAlbuns;
	
	public UniTunes() {
		this.keyboard = new Scanner(System.in); 
		this.isLoggedIn = false;
		this.gerenciadorCadastros = new GerenciadorCadastros();
		this.gerenciadorMidia = new GerenciadorMidias();
		this.dashboardVendas = new DashboardVendas();
		this.gerenciadorCarteiras = new GerenciadorCarteiras();
		this.gerenciadorVendas = new GerenciadorVendas();
		this.gerenciadorFavoritos = new GerenciadorFavoritos();
		this.gerenciadorAlbuns = new GerenciadorAlbuns();
	}
	
	public void run() {
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
				System.out.println("\nSeja bem-vindo " + cadastroLogado.getNome() + ".");
				if(cadastroLogado.getIsAcademico() == 1)
					this.isAcademico = true;
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
		
		System.out.println();
		System.out.println("Encerrando aplicativo...");
	}

	private void eventLoop() {		
		int option = -1;
		
		while(option != 0) {
			System.out.println();
			System.out.println("[Menu Inicial]");
			System.out.println("Digite 1 para gerenciar creditos");
			System.out.println("Digite 2 para gerenciar midias");
			System.out.println("Digite 3 para gerenciar favoritos");
			System.out.println("Digite 4 para visualizar albuns");
			System.out.println("Digite 5 para criar midias");
			System.out.println("Digite 6 para criar albuns");
			
			if(this.isAcademico) {
				System.out.println();
				System.out.println("[Opcoes exclusivas para Academicos]");
				System.out.println("Digite 7 para excluir um cadastro");
				System.out.println("Digite 8 para excluir uma midia");
				System.out.println("Digite 9 para visualizar o dashboard de vendas");			
			}		
			
			System.out.println("Digite 0 para sair");
			System.out.println();				
			
			option = keyboard.nextInt();
			keyboard.nextLine();	
	
			if(option == 0)
				return;
			if(option == 1)
				gerenciarCreditos();
			else if(option == 2)
				gerenciarMidias();
			else if(option == 3)
				gerenciarFavoritos();
			else if(option == 4)
				visualizarAlbuns();
			else if(option == 5)
				criarMidias();
			else if(option == 6)
				criarAlbuns();

			if(this.isAcademico) {
				if(option == 7)
					excluirCadastro();
				else if(option == 8)
					excluirMidia();
				else if(option == 9)
					visualizarDashboard();
			}
			
			else {
				System.out.println("\nOpcao invalida...\n");
			}	
		}
			
	}

	private void visualizarDashboard() {
		int option = -1;

		while(option != 0) {
			System.out.println("\n[Opcao 9 - Dashboard Vendas]\n");
			System.out.println("Digite 1 para visualizar as vendas");
			System.out.println("Digite 2 para visualizar o total vendido");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				System.out.println("\nDigite a data de inicio: (Formato aaaa-mm-dd)");
				String i = keyboard.nextLine();	
				System.out.println("\nDigite a data de fim: (Formato aaaa-mm-dd)");
				String f = keyboard.nextLine();	
				
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		        ParsePosition pp1 = new ParsePosition(0);
		        
	            java.util.Date inicioUtil = sf1.parse(i, pp1);         
	            java.sql.Date inicioSql = new java.sql.Date(inicioUtil.getTime());
	            
				SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		        ParsePosition pp2 = new ParsePosition(0);
		        
	            java.util.Date fimUtil = sf2.parse(f, pp2);         
	            java.sql.Date fimSql = new java.sql.Date(fimUtil.getTime());	            
				
				ArrayList<Venda> vendas = this.dashboardVendas.consultarVendas(inicioSql, fimSql);

				System.out.println("\n[Lista de Vendas]");
				System.out.println("| Id | Valor | Data | Comprador | Vendedor | Midia Vendida | Descricao |");
				for(Venda venda : vendas) {
					System.out.println("| " + venda.getIdVenda() + 
							" | " + venda.getValor() + " | " + venda.getData() + 
							" | " + this.gerenciadorCadastros.lerCadastro(venda.getComprador()).getNome() +
							" | " + this.gerenciadorCadastros.lerCadastro(venda.getVendedor()).getNome() +
							" | " + this.gerenciadorMidia.lerMidia(venda.getIdMidiaVendida()).getNome() +
							" | " + " | ");
				}
				System.out.println();
			} else if (option == 2) {
				System.out.println("\nDigite a data de inicio: (Formato aaaa-mm-dd)");
				String i = keyboard.nextLine();	
				System.out.println("\nDigite a data de fim: (Formato aaaa-mm-dd)");
				String f = keyboard.nextLine();	
				
				SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		        ParsePosition pp1 = new ParsePosition(0);
		        
	            java.util.Date inicioUtil = sf1.parse(i, pp1);         
	            java.sql.Date inicioSql = new java.sql.Date(inicioUtil.getTime());
	            
				SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		        ParsePosition pp2 = new ParsePosition(0);
		        
	            java.util.Date fimUtil = sf2.parse(f, pp2);         
	            java.sql.Date fimSql = new java.sql.Date(fimUtil.getTime());	            
				
				double totalVendido = this.dashboardVendas.consultarTotalVendido(inicioSql, fimSql);
				
				System.out.println("\nO total vendido do dia " + inicioSql + " ate o dia " + fimSql + 
						" foi de [R$" + totalVendido + "]\n");
			}
			
		}
	}

	private void excluirMidia() {	
		int option = -1;
		
		while(option != 0) {
			System.out.println("\n[Opcao 8 - Excluir Midia]\n");
			System.out.println("Digite 1 para visualizar todas as midias");
			System.out.println("Digite 2 para excluir uma midia");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				ArrayList<Midia> todasMidias = this.gerenciadorMidia.lerTodasMidias();
				System.out.println("\n[Lista de Midias]\n");
				System.out.println("| Id | Nome | Descricao | Categoria | InfoCategoria | Valor | Data de Criacao | Livre Acesso? | Nome Criadores |");
				for(Midia midia : todasMidias) {
					String livreAcesso = "Nao";
					if(midia.isLivreAcesso())
						livreAcesso = "Sim";
					
					System.out.println("| " + midia.getIdMidia() + " | " + 
							midia.getNome() + " | " + midia.getDescricao() + " | " + midia.getCategoria() + " | " + 
							midia.getInfoCategoria() + " | " + midia.getValor() + " | " + midia.getDataCriacao() + " | "
							+ livreAcesso + " | " + midia.getNomeCriadores() + " | ");
				}
				System.out.println();
			} else if (option == 2) {
				System.out.println("\nExcluindo uma midia.\nDigite o id da midia:\n");
				int idExcluir = keyboard.nextInt();
				keyboard.nextLine();
				
				if(this.gerenciadorMidia.existeMidia(idExcluir)) {
					this.gerenciadorMidia.excluirMidia(idExcluir);
					System.out.println("\nMidia " + idExcluir + " excluida com sucesso.\n");
				}
				else
					System.out.println("\nMidia nao encontrada.\n");
			}
			
		}	
	}

	private void excluirCadastro() {				
		int option = -1;

		while(option != 0) {
			System.out.println("\n[Opcao 7 - Excluir Cadastro]\n");
			System.out.println("Digite 1 para visualizar todos os cadastros");
			System.out.println("Digite 2 para excluir um cadastro");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				ArrayList<Cadastro> todosCadastros = this.gerenciadorCadastros.lerTodosCadastros();
				System.out.println("\n[Lista de cadastros]\n");
				System.out.println("| Id | Email | Nome | Usuario |");
				for(Cadastro cadastro : todosCadastros) {
					System.out.println("| " + cadastro.getIdCadastro() + " | " + cadastro.getEmail() + " | " + cadastro.getNome() + " | " + cadastro.getUsuario());
				}
				System.out.println();
			} else if (option == 2) {
				System.out.println("\nExcluindo cadastro.\nDigite o email do cadastro:\n");
				String email = keyboard.nextLine();	
				
				if(this.gerenciadorCadastros.existeCadastro(email)) {
					this.gerenciadorCadastros.excluirCadastro(email);
					System.out.println("\nCadastro " + email + " excluido com sucesso.");
				}
				else
					System.out.println("\nCadastro nao encontrado.\n");
			}
			
		}
	}

	private void criarAlbuns() {
		System.out.println("\n[Opcao 6 - Criar Album]\n");
		System.out.println("Digite o nome do Album:");	
		String nome = keyboard.nextLine();
		
		java.util.Date data = new java.util.Date();      
        java.sql.Date dataSql = new java.sql.Date(data.getTime());		
        
        ArrayList<Integer> midias = new ArrayList<>();

        System.out.println("Digite o numero da midia ou -1 para parar de inserir midias:");
        int idMidia = -2;
        
        while(idMidia != -1 ) {    	
        	idMidia = keyboard.nextInt();
        	keyboard.nextLine();
        	if(idMidia != -1)
        		midias.add(idMidia);
        }
        
        String listaMidias = "";
        for(Integer i : midias)
        	listaMidias += i + " ";
        System.out.println("\nLista de Midias: " + listaMidias);
        
		int idAlbumCriado = this.gerenciadorAlbuns.criarAlbum(nome, this.cadastroLogado.getIdCadastro(), dataSql, midias);
		
		if(idAlbumCriado == -1) {
			System.out.println("Erro ao processar midias...");
			return;
		} else {
			System.out.println("Album " + nome + " criado com sucesso!");
		}		
		
//		ArrayList<Album> album = this.gerenciadorAlbuns.getAlbum(idAlbumCriado);
//		
//		System.out.println("\nAlbum " + nome + " criado com sucesso!");
//		System.out.println("| Id | Nome | Criador | QtdeMidias | Lista de Midias |");
//		for(Album a : album) {	
//			System.out.println("| " + a.getIdAlbum() + " | " 
//					+ a.getNome() + " | " + a.getCriador() + " | " + a.getQtdeMidias() + " | " + 
//					"" + " |");
//		}
	}

	private void criarMidias() {
		System.out.println("\n[Opcao 5 - Criar Midia]\n");
		System.out.println("Digite o nome da midia:");	
		String nome = keyboard.nextLine();
		System.out.println("\nDigite a descricao da midia:");
		String descricao = keyboard.nextLine();
		
		System.out.println("\nDigite o valor da midia:");
		String valorString = keyboard.nextLine();
		double valor = Double.parseDouble(valorString);
		
		while(valor < 0) {
			System.out.println("Digite um valor positivo.");
			valorString = keyboard.nextLine();
			valor = Double.parseDouble(valorString);			
		}
		
		java.util.Date data = new java.util.Date();      
        java.sql.Date dataSql = new java.sql.Date(data.getTime());		
		
		this.gerenciadorMidia.criarMidia(nome, descricao, valor, 
				this.cadastroLogado.getIdCadastro(), this.cadastroLogado.getNome(), dataSql);
		
		System.out.println("\nMidia " + nome + " criada com sucesso!");
	}

	private void visualizarAlbuns() {
		int option = -1;
		
		while(option != 0) {
			System.out.println("\n[Opcao 4 - Visualizar Albuns]\n");
			System.out.println("Digite 1 para ver albuns recentes");
			System.out.println("Digite 2 para ver albuns novos");
			System.out.println("Digite 3 para ver todos os albuns");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				ArrayList<AlbumPorId> albuns = 
						this.gerenciadorAlbuns.getAlbunsRecentes();
				
				if(albuns.isEmpty()) {
					System.out.println("\nNao ha albuns recentes...");
				}
				else {
					System.out.println("\n[Lista de Albuns Recentes]");
					System.out.println("| Id | Nome | Criador | QtdeMidias |");
					for(AlbumPorId a : albuns) {						
						System.out.println("| " + a.getIdAlbum() + " | " 
								+ a.getNome() + " | " + this.gerenciadorCadastros.lerCadastro(a.getCriador()).getNome() 
								+ " | " + a.getQtdeMidias() + " | ");
					}
				}
			} else if (option == 2) {
				ArrayList<AlbumPorId> albuns = 
						this.gerenciadorAlbuns.getAlbunsNovos();
				
				if(albuns.isEmpty()) {
					System.out.println("\nNao ha albuns novos...");
				}
				else {
					System.out.println("\n[Lista de Albuns Novos]");
					System.out.println("| Id | Nome | Criador | QtdeMidias |");
					for(AlbumPorId a : albuns) {						
						System.out.println("| " + a.getIdAlbum() + " | " 
								+ a.getNome() + " | " + this.gerenciadorCadastros.lerCadastro(a.getCriador()).getNome() 
								+ " | " + a.getQtdeMidias() + " | ");
					}
				}		
			} else if (option == 3) {
				ArrayList<AlbumPorId> albuns = 
						this.gerenciadorAlbuns.getTodosAlbuns();
				
				if(albuns.isEmpty()) {
					System.out.println("\nNao ha albuns...");
				}
				else {
					System.out.println("\n[Lista de Albuns]");
					System.out.println("| Id | Nome | Criador | QtdeMidias |");
					for(AlbumPorId a : albuns) {	
						System.out.println("| " + a.getIdAlbum() + " | " 
								+ a.getNome() + " | " + this.gerenciadorCadastros.lerCadastro(a.getCriador()).getNome() 
								+ " | " + a.getQtdeMidias() + " | ");
					}
				}
			}
		}		
	}

	private void gerenciarFavoritos() {
		int option = -1;
		
		while(option != 0) {
			System.out.println("\n[Opcao 3 - Gerenciar Favoritos]\n");
			System.out.println("Digite 1 para ver midias favoritas");
			System.out.println("Digite 2 para adicionar midia aos favoritos");
			System.out.println("Digite 3 para remover midia dos favoritos");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				ArrayList<Favorito> favoritos = this.gerenciadorFavoritos.getFavoritos(this.cadastroLogado.getIdCadastro());
				if(favoritos.isEmpty()) {
					System.out.println("\nNao ha midias favoritas...");
				}
				else {
					System.out.println("\n[Lista de Midias Favoritas]");
					System.out.println("| Id | Nome |");
					for(Favorito f : favoritos) {
						System.out.println("| " + f.getIdMidia() + " |" 
								+ this.gerenciadorMidia.lerMidia(f.getIdMidia()).getNome() + " |");
					}
				}
			} else if (option == 2) {
				System.out.println("\nDigite o Id da midia a favoritar:");			
				int idMdia = keyboard.nextInt();
				keyboard.nextLine();
				this.gerenciadorFavoritos.adicionarFavorito(this.cadastroLogado.getIdCadastro(), idMdia);
				System.out.println("\nMidia " + this.gerenciadorMidia.lerMidia(idMdia).getNome() + " adicionada aos favoritos!\n");	
			} else if (option == 3) {
				System.out.println("\nDigite o Id da midia a remover dos favoritos:");			
				int idMdia = keyboard.nextInt();
				keyboard.nextLine();
				this.gerenciadorFavoritos.excluirFavorito(this.cadastroLogado.getIdCadastro(), idMdia);
				System.out.println("\nMidia " + this.gerenciadorMidia.lerMidia(idMdia).getNome() + " removida dos favoritos!\n");					
			}
		}	
	}

	private void gerenciarMidias() {
		int option = -1;

		while(option != 0) {
			System.out.println("\n[Opcao 2 - Gerenciar Midias]\n");
			System.out.println("Digite 1 para ver todas as midias");
			System.out.println("Digite 2 para ver midias adquiridas");
			System.out.println("Digite 3 para ver midias de livre acesso");
			System.out.println("Digite 4 para comprar midia");
			System.out.println("Digite 5 para baixar midia");
			System.out.println("Digite 6 para reproduzir midia");
			System.out.println("Digite 7 para buscar midia");
			System.out.println("Digite 0 para sair");
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				ArrayList<Midia> todasMidias = this.gerenciadorMidia.lerTodasMidias();
				System.out.println("\n[Lista de Midias]\n");
				System.out.println("| Id | Nome | Descricao | Categoria | InfoCategoria | Valor | Data de Criacao | Livre Acesso? | Nome Criadores |");
				for(Midia midia : todasMidias) {
					String livreAcesso = "Nao";
					if(midia.isLivreAcesso())
						livreAcesso = "Sim";
					
					System.out.println("| " + midia.getIdMidia() + " | " + 
							midia.getNome() + " | " + midia.getDescricao() + " | " + midia.getCategoria() + " | " + 
							midia.getInfoCategoria() + " | " + midia.getValor() + " | " + midia.getDataCriacao() + " | "
							+ livreAcesso + " | " + midia.getNomeCriadores() + " | ");
				}	
			} else if (option == 2) {
				ArrayList<Venda> comprasRealizadas = this.gerenciadorVendas.getComprasRealizadas(this.cadastroLogado.getIdCadastro());
				ArrayList<Midia> midiasAdquiridas = this.gerenciadorMidia.getMidiasAdquiridas(comprasRealizadas);
				
				System.out.println("\n[Lista de Midias Adquiridas]\n");
				System.out.println("| Id | Nome | Descricao | Categoria | InfoCategoria | Valor | Data de Criacao | Livre Acesso? | Nome Criadores |");
				for(Midia midia : midiasAdquiridas) {
					String livreAcesso = "Nao";
					if(midia.isLivreAcesso())
						livreAcesso = "Sim";
					
					System.out.println("| " + midia.getIdMidia() + " | " + 
							midia.getNome() + " | " + midia.getDescricao() + " | " + midia.getCategoria() + " | " + 
							midia.getInfoCategoria() + " | " + midia.getValor() + " | " + midia.getDataCriacao() + " | "
							+ livreAcesso + " | " + midia.getNomeCriadores() + " | ");
				}				
			} else if (option == 3) {
				ArrayList<Midia> midiasLivreAcesso = this.gerenciadorMidia.getMidiasLivreAcesso();
				
				System.out.println("\n[Lista de Midias de Livre Acesso]\n");
				System.out.println("| Id | Nome | Descricao | Categoria | InfoCategoria | Valor | Data de Criacao | Livre Acesso? | Nome Criadores |");				
				for(Midia midia : midiasLivreAcesso) {
					String livreAcesso = "Nao";
					if(midia.isLivreAcesso())
						livreAcesso = "Sim";
					
					System.out.println("| " + midia.getIdMidia() + " | " + 
							midia.getNome() + " | " + midia.getDescricao() + " | " + midia.getCategoria() + " | " + 
							midia.getInfoCategoria() + " | " + midia.getValor() + " | " + midia.getDataCriacao() + " | "
							+ livreAcesso + " | " + midia.getNomeCriadores() + " | ");
				}				
			} else if (option == 4) {
				System.out.println("\nDigite o Id da midia a ser adquirida:");
				int idMidia = keyboard.nextInt();
				keyboard.nextLine();
				Venda vendaRealizada;
				
				Midia midia = this.gerenciadorMidia.lerMidia(idMidia);
				if(midia.getIdMidia() == -1)
					System.out.println("Midia de Id " + idMidia + " nao encontrada.");
				else {
					boolean possuiSaldo = this.gerenciadorCarteiras.possuiSaldo(this.cadastroLogado.getIdCadastro(), midia.getValor());
					
					if(possuiSaldo) {
						vendaRealizada = this.gerenciadorVendas.realizarVenda(midia, this.cadastroLogado);	
						System.out.println("Midia adquirida com sucesso. Comprovante:");
						System.out.println("\n| Midia | Comprador | Vendedor | Data | Valor |");
						System.out.println("| " + midia.getNome() + " | " + this.cadastroLogado.getNome() + " | " +
								this.gerenciadorCadastros.lerCadastro(midia.getCriadorMidia()).getNome() + " | " +
								vendaRealizada.getData() + " | " + vendaRealizada.getValor() + " |");
					} else {
						System.out.println("Saldo insuficiente para comprar a midia.");
					}
				}
				
			} else if (option == 5) {
				System.out.println("\nDigite o Id da midia a ser baixada:");
				int idMidia = keyboard.nextInt();
				keyboard.nextLine();
				
				if(this.gerenciadorMidia.existeMidia(idMidia)) {
					System.out.println("Baixando...");
					sleep(2);
					System.out.println("Download completo!");
				}
				else {
					System.out.println("Midia de Id " + idMidia + " nao encontrada.");					
				}
			} else if (option == 6) {
				System.out.println("\nDigite o Id da midia a ser reproduzida:");
				int idMidia = keyboard.nextInt();
				keyboard.nextLine();
				
				if(this.gerenciadorMidia.existeMidia(idMidia)) {
					System.out.println("Reproduzindo midia...");
					sleep((int)this.gerenciadorMidia.lerMidia(idMidia).getInfoCategoria());
					System.out.println("Reproducao completa.");
				}
				else {
					System.out.println("Midia de Id " + idMidia + " nao encontrada.");					
				}				
			} else if (option == 7) {
				System.out.println("\nDigite o nome da midia a ser buscada:");
				String nomeBuscar = keyboard.nextLine();
				
				System.out.println("\n[Lista de Midias Encontradas]\n");
				System.out.println("| Id | Nome | Descricao | Categoria | InfoCategoria | Valor | Data de Criacao | Livre Acesso? | Nome Criadores |");
				ArrayList<Midia> midiasBuscadas = this.gerenciadorMidia.buscar(nomeBuscar);
				for(Midia midia : midiasBuscadas) {
					String livreAcesso = "Nao";
					if(midia.isLivreAcesso())
						livreAcesso = "Sim";
					
					System.out.println("| " + midia.getIdMidia() + " | " + 
							midia.getNome() + " | " + midia.getDescricao() + " | " + midia.getCategoria() + " | " + 
							midia.getInfoCategoria() + " | " + midia.getValor() + " | " + midia.getDataCriacao() + " | "
							+ livreAcesso + " | " + midia.getNomeCriadores() + " | ");
				}				
			}
			
		}	
	}

	private void gerenciarCreditos() {
		int option = -1;
		
		while(option != 0) {
			System.out.println("\n[Opcao 1 - Gerenciar Creditos]\n");
			System.out.println("Digite 1 para adicionar creditos");
			System.out.println("Digite 2 para consultar seus creditos");
			System.out.println("Digite 0 para sair");	
			System.out.println();			
			
			option = keyboard.nextInt();
			keyboard.nextLine();
			
			if(option == 0) {
				break;
			}
			
			if(option == 1) {
				System.out.println("\nAdicionando creditos.\nDigite o valor:");
				String valorString = keyboard.nextLine();
				double valor = Double.parseDouble(valorString);

				int pagamento = -1;
				System.out.println("\nDigite a forma de pagamento:\n");
				System.out.println("Digite 1 para cartao de credito");
				System.out.println("Digite 2 para pix");
				System.out.println("Digite 3 para boleto bancario");
				System.out.println("Digite 0 para sair");					
				pagamento = keyboard.nextInt();
				keyboard.nextLine();	
				
				System.out.println("\nRedirecionando para o gateway de pagamento...");
				sleep(1);
				System.out.println("\nPagamento confirmado!");
				
				this.gerenciadorCarteiras.adicionarCreditos(this.cadastroLogado.getIdCadastro(), valor);
				
				System.out.println("\n" + valor + " adicionados a carteira.\nSaldo atual: R$ " + 
						this.gerenciadorCarteiras.consultarCreditos(this.cadastroLogado.getIdCadastro()));
				
			} else if (option == 2) {
				double saldo = this.gerenciadorCarteiras.consultarCreditos(this.cadastroLogado.getIdCadastro());
				System.out.println("\nSaldo atual: R$ " + saldo);
			}
			
		}	
	}

	private void sleep(int i) {
		try { TimeUnit.SECONDS.sleep(i); } catch (InterruptedException e) { e.printStackTrace(); }
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
		this.gerenciadorCarteiras.criarCarteira(this.cadastroLogado.getIdCadastro());
		
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
			
			this.cadastroLogado = gerenciadorCadastros.autenticar(email, password);
			
			if(cadastroLogado.getIdCadastro() > 0) {
				this.isLoggedIn = true;
			} else {
				System.out.println();
				System.out.println("Cadastro " + email + " nao encontrado.");
				break;
			}			
		}
		
	}
}
