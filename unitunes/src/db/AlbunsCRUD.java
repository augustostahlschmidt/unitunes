package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Album;
import model.Venda;

public class AlbunsCRUD {
	public AlbunsCRUD() {
		
	}

	@SuppressWarnings("finally")
	public ArrayList<Album> lerTodosAlbuns() {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Album album;
		ArrayList<Album> albuns = new ArrayList<>();
		
		String sql = "SELECT * FROM albuns";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
            while(rs.next())
            {
            	album = new Album();
            	album.setIdAlbum(rs.getInt("idAlbum"));
            	album.setCriador(rs.getInt("criador"));
            	album.setIdMidia(rs.getInt("idMidia"));
            	album.setQtdeMidias(rs.getInt("qtdeMidias"));
            	album.setNome(rs.getString("nome"));
            	album.setData(rs.getDate("data"));
				albuns.add(album);
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
		    
			return albuns;
		}		
	}

	public void criarAlbum(int idAlbum, String nome, int idCriador, Date dataCriacao, int qtdeMidias, int idMidia) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO albuns (idAlbum, criador, qtdeMidias, idMidia, nome, data) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, idAlbum);
			ps.setInt(2, idCriador);
			ps.setInt(3, qtdeMidias);
			ps.setInt(4, idMidia);
			ps.setString(5, nome);
			ps.setDate(6, dataCriacao);
			
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
