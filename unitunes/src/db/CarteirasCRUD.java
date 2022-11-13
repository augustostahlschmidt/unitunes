package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Carteira;

public class CarteirasCRUD {
	public CarteirasCRUD() {
		
	}

	@SuppressWarnings("finally")
	public void atualizarSaldo(int idDono, double saldo) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "UPDATE carteiras SET saldo = ? WHERE dono = ?";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);

			ps = connection.prepareStatement(sql);
			ps.setDouble(1, saldo);
			ps.setInt(2, idDono);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) {  }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { }
		    }
		}		
	}

	@SuppressWarnings("finally")
	public Carteira lerCarteira(int idDono) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Carteira carteira = new Carteira();
		
		String sql = "SELECT * FROM carteiras WHERE dono = ?";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, idDono);
			rs = ps.executeQuery();
			rs.first();

			rs.last();
			if (rs.getRow() < 1){
			    // process case with no results
				carteira.setIdCarteira(-1);
				return carteira;
				
			} else{
			    // process case with results
				carteira.setDono(rs.getInt("dono"));
				carteira.setIdCarteira(rs.getInt("idCarteira"));
				carteira.setSaldo(rs.getDouble("saldo"));
				rs.beforeFirst();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) {  }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { }
		    }
			return carteira;
		}
	}
	
}
