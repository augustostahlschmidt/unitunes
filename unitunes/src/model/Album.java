package model;

import java.sql.Date;
import java.util.ArrayList;

import db.IdGenerator;

public class Album {
	private int idAlbum;
	private int criador;
	private int qtdeMidias;
	private String nome;
	private int idMidia;
	private Date data;
	
	public Album() {	
	}

	public int getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public int getCriador() {
		return criador;
	}

	public void setCriador(int criador) {
		this.criador = criador;
	}

	public int getQtdeMidias() {
		return qtdeMidias;
	}

	public void setQtdeMidias(int qtdeMidias) {
		this.qtdeMidias = qtdeMidias;
	}

	public int getIdMidia() {
		return idMidia;
	}

	public void setIdMidia(int idMidia) {
		this.idMidia = idMidia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
