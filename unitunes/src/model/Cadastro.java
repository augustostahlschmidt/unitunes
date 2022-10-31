package model;

import db.IdGenerator;

public class Cadastro {
	private int idCadastro;
	private String email;
	private String senha;
	private String usuario;
	private String nome;
	private int isAcademico;
	
	public Cadastro() {
	}
	
	public void setIdCadastro(int idCadastro) {
		this.idCadastro = idCadastro;
	}

	public int getIdCadastro() {
		return idCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIsAcademico() {
		return isAcademico;
	}

	public void setIsAcademico(int isAcademico) {
		this.isAcademico = isAcademico;
	}
}
