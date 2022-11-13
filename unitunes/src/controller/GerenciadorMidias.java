package controller;

import java.util.ArrayList;
import java.util.Date;

import db.MidiasCRUD;
import model.Cadastro;
import model.Midia;
import model.Venda;

public class GerenciadorMidias {
	private MidiasCRUD crud;
	
	public GerenciadorMidias() {
		this.crud = new MidiasCRUD();
	}	
	
	public void excluirMidia(int idMidia) {
		this.crud.excluirMidia(idMidia);
	}

	public ArrayList<Midia> lerTodasMidias() {
		return this.crud.lerTodasMidias();
	}

	public boolean existeMidia(int id) {
		Midia midia = this.crud.lerMidia(id);
		
		if(midia.getIdMidia() == -1)
			return false;
		
		return true;
	}

	public Midia lerMidia(int id) {
		return this.crud.lerMidia(id);
	}

	public ArrayList<Midia> buscar(String buscar) {
		ArrayList<Midia> todasMidias = this.lerTodasMidias();
		ArrayList<Midia> buscadas = new ArrayList<>();
		
		for(Midia midia : todasMidias) {
			if(midia.getNome().contains(buscar))
				buscadas.add(midia);
		}
			
		return buscadas;
	}

	public ArrayList<Midia> getMidiasAdquiridas(ArrayList<Venda> comprasRealizadas) {
		ArrayList<Midia> todasMidias = this.lerTodasMidias();	
		ArrayList<Midia> midiasAdquiridas = new ArrayList<>();
		
		for(Venda venda : comprasRealizadas) {
			for(Midia midia : todasMidias) {
				if(venda.getIdMidiaVendida() == midia.getIdMidia()) {
					midiasAdquiridas.add(midia);
					break;
				}
			}
		}
		
		return midiasAdquiridas;
	}

	public ArrayList<Midia> getMidiasLivreAcesso() {
		ArrayList<Midia> todasMidias = this.lerTodasMidias();	
		ArrayList<Midia> midiasLivreAcesso = new ArrayList<>();
		for(Midia midia : todasMidias) {
			if(midia.isLivreAcesso()) {
				midiasLivreAcesso.add(midia);
			}
		}
		return midiasLivreAcesso;
	}

	public void criarMidia(String nomeMidia, String descricaoMidia, double valor,
			int idCriador, String nomeCriador, java.sql.Date data) {
		
		int livreAcesso = 1;
		if(valor > 0)
			livreAcesso = 0;
		
		this.crud.criarMidia(idCriador, nomeCriador, nomeMidia, descricaoMidia, valor, livreAcesso, data);
	}
}
