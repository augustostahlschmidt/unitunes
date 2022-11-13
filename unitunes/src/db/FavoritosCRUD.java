package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Cadastro;
import model.Favorito;

public class FavoritosCRUD {
	public FavoritosCRUD() {
		
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Favorito> lerTodosFavoritos(){
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Favorito favorito;
		ArrayList<Favorito> favoritos = new ArrayList<>();
		
		String sql = "SELECT * FROM favoritos";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
            while(rs.next())
            {
            	favorito = new Favorito();
            	favorito.setIdCadastro(rs.getInt("idCadastro"));
            	favorito.setIdMidia(rs.getInt("idMidia"));
				favoritos.add(favorito);
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
		    
			return favoritos;
		}		
	}

	public void excluirFavorito(int idCadastro, int idMidia) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM favoritos WHERE idCadastro = ? AND idMidia = ?";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			ps.setInt(1, idCadastro);
			ps.setInt(2, idMidia);
			ps.execute();
			
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
	public void adicionarFavorito(int idCadastro, int idMidia) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO favoritos (idCadastro, idMidia) VALUES (?, ?)";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, idCadastro);
			ps.setInt(2, idMidia);
			ps.execute();
			
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
	
}
