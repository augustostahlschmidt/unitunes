package model;

import java.sql.Date;

import db.IdGenerator;

public class Venda {
	private int idVenda;
	private double valor;
	private Date data;
	private int comprador;
	private int vendedor;
	private int idMidiaVendida;
	private String descricao;
	
	public Venda() {		
	}
	
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getComprador() {
		return comprador;
	}

	public void setComprador(int comprador) {
		this.comprador = comprador;
	}

	public int getVendedor() {
		return vendedor;
	}

	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}

	public int getIdMidiaVendida() {
		return idMidiaVendida;
	}

	public void setIdMidiaVendida(int idMidiaVendida) {
		this.idMidiaVendida = idMidiaVendida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
