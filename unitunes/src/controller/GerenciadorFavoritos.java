package controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import db.FavoritosCRUD;
import model.Favorito;

public class GerenciadorFavoritos {
	private FavoritosCRUD crud;
	
	public GerenciadorFavoritos() {
		this.crud = new FavoritosCRUD();
	}
	
	public void adicionarFavorito(int idCadastro, int idMidia) {
		this.crud.adicionarFavorito(idCadastro, idMidia);
	}
	
	public void excluirFavorito(int idCadastro, int idMidia) {
		this.crud.excluirFavorito(idCadastro, idMidia);
	}
	
	public ArrayList<Favorito> getFavoritos(int idCadastro){
		ArrayList<Favorito> favoritos = this.crud.lerTodosFavoritos();

		favoritos.stream()
		.filter(f -> f.getIdCadastro() == idCadastro)
		.collect(Collectors.toList());
		
		return favoritos;
	}
}
