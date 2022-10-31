package model;

import java.util.ArrayList;

import db.IdGenerator;

public class Album {
	private int idAlbum;
	private int criador;
	private int qtdeMidias;
	private ArrayList<Midia> midias;
	
	public Album() {
		this.midias = new ArrayList<>();		
	}
	
	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}

	public int getIdAlbum() {
		return idAlbum;
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
	
	public void adicionarMidia(Midia midia) {
		this.midias.add(midia);
		qtdeMidias = this.midias.size();
	}
}
