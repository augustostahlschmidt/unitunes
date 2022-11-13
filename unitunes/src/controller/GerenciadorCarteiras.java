package controller;

import db.CarteirasCRUD;
import model.Carteira;

public class GerenciadorCarteiras {
	private CarteirasCRUD crud;
	
	public GerenciadorCarteiras() {
		this.crud = new CarteirasCRUD();
	}
	
	public void adicionarCreditos(int idDono, double valor) {
		Carteira carteira = this.crud.lerCarteira(idDono);			
		double saldo = carteira.getSaldo();
		saldo += valor;
		this.crud.atualizarSaldo(idDono, saldo);		
	}
	
	public void retirarCreditos(int idDono, double valor) {
		Carteira carteira = this.crud.lerCarteira(idDono);			
		double saldo = carteira.getSaldo();
		saldo -= valor;
		this.crud.atualizarSaldo(idDono, saldo);	
	}
	
	public double consultarCreditos(int idDono) {
		Carteira carteira = this.crud.lerCarteira(idDono);
		return carteira.getSaldo();
	}

	public boolean possuiSaldo(int idCadastro, double valor) {
		Carteira carteira = this.crud.lerCarteira(idCadastro);			
		double saldo = carteira.getSaldo();		
		
		if(saldo >= valor)
			return true;
		
		return false;
	}
}	
