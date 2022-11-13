package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import db.VendasCRUD;
import model.Venda;

public class DashboardVendas {
	private VendasCRUD crud;
	
	public DashboardVendas() {
		this.crud = new VendasCRUD();
	}
	
	public ArrayList<Venda> consultarVendas(Date inicio, Date fim) {
		ArrayList<Venda> vendas = this.crud.lerTodasVendas();
		
		vendas.stream()
			.filter(v -> v.getData().after(inicio))
			.filter(v -> v.getData().before(fim))
			.collect(Collectors.toList());
		
		return vendas;
	}
	
	public double consultarTotalVendido(Date inicio, Date fim) {
		ArrayList<Venda> vendas = this.crud.lerTodasVendas();
		
		vendas.stream()
			.filter(v -> v.getData().after(inicio))
			.filter(v -> v.getData().before(fim))
			.collect(Collectors.toList());
		
		double totalVendido = 0;
		for(Venda v : vendas)
			totalVendido += v.getValor();
		return totalVendido;
	}
}
