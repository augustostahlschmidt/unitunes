package controller;

import java.util.ArrayList;

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
	
	public ArrayList<Cadastro> lerTodosCadastros(){
		return this.crud.lerTodosCadastros();
	}
	
	public boolean existeCadastro(String email) {
		Cadastro cadastro = this.crud.lerCadastro(email);
		
		if(cadastro.getIdCadastro() == -1)
			return false;
		
		return true;
	}

	public void excluirCadastro(String email) {
		this.crud.excluirCadastro(email);
	}
	
	public Cadastro lerCadastro(int id) {
		return this.crud.lerCadastro(id);
	}
}
