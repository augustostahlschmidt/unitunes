package controller;

import db.CadastrosCRUD;
import model.Cadastro;

public class GerenciadorCadastros {
	private CadastrosCRUD crud;
	
	public GerenciadorCadastros() {
		this.crud = new CadastrosCRUD();
	}
	
	public Cadastro autenticar(String email, String senha) {
		return this.crud.lerCadastro(email, senha);
	}
	
	public void criarCadastro(String email, String senha, String usuario, String nome, int academico) {
		this.crud.adicionarCadastro(email, senha, usuario, nome, academico);
	}
}
