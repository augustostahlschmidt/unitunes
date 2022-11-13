package controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import db.VendasCRUD;
import model.Cadastro;
import model.Midia;
import model.Venda;

public class GerenciadorVendas {
	private VendasCRUD crud;
	private GerenciadorCarteiras gerenciadorCarteiras;
	
	public GerenciadorVendas() {
		this.crud = new VendasCRUD();
		this.gerenciadorCarteiras = new GerenciadorCarteiras();
	}
	
	public ArrayList<Venda> getComprasRealizadas(int idCadastro){
		ArrayList<Venda> comprasRealizadas = this.crud.lerTodasVendas();
		
		comprasRealizadas.stream()
		.filter(v -> v.getComprador() == idCadastro)
		.collect(Collectors.toList());
		
		return comprasRealizadas;
	}

	public Venda realizarVenda(Midia midia, Cadastro cadastroComprador) {
		double valorMidia = 0;
		
		if(!midia.isLivreAcesso()) {
			valorMidia = midia.getValor();
			this.gerenciadorCarteiras.adicionarCreditos(0, valorMidia * 0.1);
			this.gerenciadorCarteiras.adicionarCreditos(midia.getCriadorMidia(), valorMidia * 0.9);
			this.gerenciadorCarteiras.retirarCreditos(cadastroComprador.getIdCadastro(), valorMidia);			
		}
		
		java.util.Date data = new java.util.Date();      
        java.sql.Date dataSql = new java.sql.Date(data.getTime());		
        
        int idComprador = cadastroComprador.getIdCadastro();
        int idVendedor = midia.getCriadorMidia();
		
		int idVendaRealizada = this.crud.adicionarVenda(valorMidia, dataSql, idComprador, idVendedor, midia.getIdMidia(), "");
		Venda venda = new Venda();
		venda = this.crud.lerVenda(idVendaRealizada);
		return venda;
	}
		
}
