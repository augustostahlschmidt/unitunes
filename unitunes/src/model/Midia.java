package model;

import java.sql.Date;

import db.IdGenerator;

public class Midia {
	
	private int idMidia;
	private String nome;
	private String descricao;
	private int categoria;
	private double infoCategoria;
	private double valor;
	private Date dataCriacao;
	private boolean isLivreAcesso;
	private String nomeCriadores;
	private String conteudo;
	private String imagem;
	private int criadorMidia;
	
	public Midia() {
	}
	
	public void setIdMidia(int idMidia) {
		this.idMidia = idMidia;
	}

	public int getIdMidia() {
		return idMidia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCriadores() {
		return nomeCriadores;
	}

	public void setNomeCriadores(String nomeCriadores) {
		this.nomeCriadores = nomeCriadores;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getInfoCategoria() {
		return infoCategoria;
	}

	public void setInfoCategoria(double infoCategoria) {
		this.infoCategoria = infoCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isLivreAcesso() {
		return isLivreAcesso;
	}

	public void setLivreAcesso(boolean isLivreAcesso) {
		this.isLivreAcesso = isLivreAcesso;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public int getCriadorMidia() {
		return criadorMidia;
	}

	public void setCriadorMidia(int criadorMidia) {
		this.criadorMidia = criadorMidia;
	}
	
	
	
}
