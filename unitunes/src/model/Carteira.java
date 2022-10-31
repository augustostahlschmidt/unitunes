package model;

import db.IdGenerator;

public class Carteira {
	private int idCarteira;
	private int dono;
	private double saldo;
	
	public Carteira() {	
	}
	
	public void setIdCarteira(int idCarteira) {
		this.idCarteira = idCarteira;
	}

	public int getIdCarteira() {
		return idCarteira;
	}

	public int getDono() {
		return dono;
	}

	public void setDono(int dono) {
		this.dono = dono;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
