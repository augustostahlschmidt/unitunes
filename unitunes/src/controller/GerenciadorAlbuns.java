package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

import db.AlbunsCRUD;
import db.IdGenerator;
import model.Album;
import model.AlbumPorId;

public class GerenciadorAlbuns {
	private AlbunsCRUD crud;
	
	public GerenciadorAlbuns() {
		this.crud = new AlbunsCRUD();
	}
	
	public int criarAlbum(String nome, int idCriador, Date dataCriacao, ArrayList<Integer> midias) {
		if(midias.isEmpty()) {
			return -1;
		}
		
		int qtdeMidias = midias.size();
		int idAlbum = new IdGenerator().getNextAlbumId();
		
		for(Integer idMidia : midias) {
			this.crud.criarAlbum( idAlbum, nome, idCriador, dataCriacao, qtdeMidias, idMidia);			
		}
		return idAlbum;
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<AlbumPorId> getAlbunsRecentes() {
		ArrayList<Album> albuns = new ArrayList<>();	
		albuns = this.crud.lerTodosAlbuns();
		ArrayList<AlbumPorId> albunsPorId = new ArrayList<>();
		
		java.util.Date data = new java.util.Date();      
		int anoAtual = data.getYear();
        //java.sql.Date dataSql = new java.sql.Date(data.getTime());		
		
		albuns.stream()
		.filter(a -> a.getData().getYear() == anoAtual)
		.collect(Collectors.toList());

		AlbumPorId album = null;
		int ultimoId = -1;
		for(Album a : albuns) {		
			if(a.getIdAlbum() == ultimoId) {
				album.addMidia(a.getIdMidia());
			} else {
				if(album != null)
					albunsPorId.add(album);
				album = new AlbumPorId();
				album.setCriador(a.getCriador());
				album.setData(a.getData());
				album.setIdAlbum(a.getIdAlbum());
				album.setNome(a.getNome());
				album.setQtdeMidias(a.getQtdeMidias());
				album.addMidia(a.getIdMidia());
			}
			
			ultimoId = a.getIdAlbum();
		}
		if(album != null) {
			albunsPorId.add(album);	
		}
		
		return albunsPorId;
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<AlbumPorId> getAlbunsNovos() {
		ArrayList<Album> albuns = new ArrayList<>();	
		albuns = this.crud.lerTodosAlbuns();
		ArrayList<AlbumPorId> albunsPorId = new ArrayList<>();
		
		java.util.Date data = new java.util.Date();      
		int mesAtual = data.getMonth();
        //java.sql.Date dataSql = new java.sql.Date(data.getTime());		
		
		albuns.stream()
		.filter(a -> a.getData().getMonth() == mesAtual)
		.collect(Collectors.toList());

		AlbumPorId album = null;
		int ultimoId = -1;
		for(Album a : albuns) {		
			if(a.getIdAlbum() == ultimoId) {
				album.addMidia(a.getIdMidia());
			} else {
				if(album != null)
					albunsPorId.add(album);
				album = new AlbumPorId();
				album.setCriador(a.getCriador());
				album.setData(a.getData());
				album.setIdAlbum(a.getIdAlbum());
				album.setNome(a.getNome());
				album.setQtdeMidias(a.getQtdeMidias());
				album.addMidia(a.getIdMidia());
			}
			
			ultimoId = a.getIdAlbum();
		}
		if(album != null) {
			albunsPorId.add(album);	
		}
		
		return albunsPorId;	
	}
	
	public ArrayList<AlbumPorId> getTodosAlbuns() {
		ArrayList<Album> albuns = new ArrayList<>();
		albuns = this.crud.lerTodosAlbuns();	
		ArrayList<AlbumPorId> albunsPorId = new ArrayList<>();
		
		AlbumPorId album = null;
		int ultimoId = -1;	
		for(Album a : albuns) {		
			if(a.getIdAlbum() == ultimoId) {
				album.addMidia(a.getIdMidia());
			} else {
				if(album != null) {
					albunsPorId.add(album);
				}
				album = new AlbumPorId();
				album.setCriador(a.getCriador());
				album.setData(a.getData());
				album.setIdAlbum(a.getIdAlbum());
				album.setNome(a.getNome());
				album.setQtdeMidias(a.getQtdeMidias());
				album.addMidia(a.getIdMidia());
			}
			
			ultimoId = a.getIdAlbum();
		}
		if(album != null) {
			albunsPorId.add(album);	
		}
		
		return albunsPorId;		
	}

	public ArrayList<Integer> getMidiasNoAlbum(int idAlbum) {
		ArrayList<Album> albuns = new ArrayList<>();
		ArrayList<Integer> midias = new ArrayList<>();
		
		albuns = this.crud.lerTodosAlbuns();	
		
		albuns.stream()
		.filter(a -> a.getIdAlbum() == idAlbum)
		.collect(Collectors.toList());
		
		for(Album a : albuns) {
			midias.add(a.getIdMidia());
		}
		
		return midias;	
	}

	public AlbumPorId getAlbum(int idAlbum) {
		ArrayList<Album> albuns = new ArrayList<>();
		albuns = this.crud.lerTodosAlbuns();		
		AlbumPorId album = null;
		boolean first = true;
		
		albuns.stream()
		.filter(a -> a.getIdAlbum() == idAlbum)
		.collect(Collectors.toList());
		
		for(Album a : albuns) {		
			if(first) {
				album = new AlbumPorId();
				album.setCriador(a.getCriador());
				album.setData(a.getData());
				album.setIdAlbum(a.getIdAlbum());
				album.setNome(a.getNome());
				album.setQtdeMidias(a.getQtdeMidias());
				album.addMidia(a.getIdMidia());					
			} else {
				album.addMidia(a.getIdMidia());					
			}
		}
		return album;			
	}
	
}
