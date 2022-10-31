package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IdGenerator {
	public IdGenerator() {
	}
	
	@SuppressWarnings({ "resource", "finally" })
	public int getNextCadastroId() { // tipoId = 0
		int returnId;
		int id = 0;
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ids WHERE tipoId = 0";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			rs = ps.executeQuery();
			rs.first();
			returnId = rs.getInt("id");
			id = returnId;
			
			id++;
			sql = "UPDATE ids SET id = ? WHERE tipoId = 0";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			id = -1;
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { id = -1; }
		    }
		    
			return id;
		}
	}
	
	@SuppressWarnings({ "resource", "finally" })
	public int getNextMidiaId() {  // tipoId = 1
		int returnId;
		int id = 0;
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ids WHERE tipoId = 1";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			rs = ps.executeQuery();
			rs.first();
			returnId = rs.getInt("id");
			id = returnId;
			
			id++;
			sql = "UPDATE ids SET id = ? WHERE tipoId = 1";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			id = -1;
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { id = -1; }
		    }
		    
			return id;
		}
	}	

	@SuppressWarnings({ "resource", "finally" })
	public int getNextAlbumId() {  // tipoId = 2
		int returnId;
		int id = 0;
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ids WHERE tipoId = 2";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			rs = ps.executeQuery();
			rs.first();
			returnId = rs.getInt("id");
			id = returnId;
			
			id++;
			sql = "UPDATE ids SET id = ? WHERE tipoId = 2";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			id = -1;
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { id = -1; }
		    }
		    
			return id;
		}
	}

	@SuppressWarnings({ "resource", "finally" })
	public int getNextCarteiraId() {  // tipoId = 3
		int returnId;
		int id = 0;
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ids WHERE tipoId = 3";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			rs = ps.executeQuery();
			rs.first();
			returnId = rs.getInt("id");
			id = returnId;
			
			id++;
			sql = "UPDATE ids SET id = ? WHERE tipoId = 3";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			id = -1;
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { id = -1; }
		    }
		    
			return id;
		}
	}

	@SuppressWarnings({ "resource", "finally" })
	public int getNextVendaId() {  // tipoId = 4
		int returnId;
		int id = 0;
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ids WHERE tipoId = 4";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			rs = ps.executeQuery();
			rs.first();
			returnId = rs.getInt("id");
			id = returnId;
			
			id++;
			sql = "UPDATE ids SET id = ? WHERE tipoId = 4";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			id = -1;
			e.printStackTrace();
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (ps != null) {
		        try {
		            ps.close();
		        } catch (Exception e) { id = -1; }
		    }
		    if (connection != null) {
		        try {
		        	connection.close();
		        } catch (Exception e) { id = -1; }
		    }
		    
			return id;
		}
	}
}
