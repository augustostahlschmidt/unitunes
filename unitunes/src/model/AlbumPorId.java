package model;

import java.sql.Date;
import java.util.ArrayList;

public class AlbumPorId {
	private int idAlbum;
	private int criador;
	private int qtdeMidias;
	private String nome;
	private Date data;
	private ArrayList<Integer> midias;
	String midiasEmLista = "";
	
	public AlbumPorId() {	
		this.midias = new ArrayList<>();
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
	
	public ArrayList<Integer> getMidias(){
		return this.midias;
	}
	
	public void addMidia(int idMidia) {
		this.midias.add(idMidia);
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
