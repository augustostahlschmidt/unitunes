package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cadastro;

public class CadastrosCRUD {
	public CadastrosCRUD() {		
	}
	
	@SuppressWarnings("finally")
	public Cadastro lerCadastro(String email, String senha) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cadastro cadastro = new Cadastro();
		
		String sql = "SELECT * FROM cadastros WHERE email = ? AND senha = ?";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, email);
			ps.setString(2, senha);
			rs = ps.executeQuery();
			rs.first();

			rs.last();
			if (rs.getRow() < 1){
			    // process case with no results
				cadastro.setIdCadastro(-1);
				return cadastro;
				
			} else{
			    // process case with results
				cadastro.setEmail(rs.getString("email"));
				cadastro.setIdCadastro(rs.getInt("idCadastro"));
				cadastro.setIsAcademico(rs.getInt("academico"));
				cadastro.setNome(rs.getString("nome"));
				cadastro.setSenha(rs.getString("senha"));
				cadastro.setUsuario(rs.getString("usuario"));
				rs.beforeFirst();
			}
			
//			if (rs.next() == false) {
//				cadastro.setIdCadastro(-1);
//				return cadastro;
//				
//		      } else {
//		        do {
//					cadastro.setEmail(rs.getString("email"));
//					cadastro.setIdCadastro(rs.getInt("idCadastro"));
//					cadastro.setIsAcademico(rs.getInt("academico"));
//					cadastro.setNome(rs.getString("nome"));
//					cadastro.setSenha(rs.getString("senha"));
//					cadastro.setUsuario(rs.getString("usuario"));
//		        } while (rs.next());
//		      }
			
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
		    
			return cadastro;
		}
	}

	@SuppressWarnings("finally")
	public void adicionarCadastro(String email, String senha, String usuario, String nome, int academico) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO cadastros (idCadastro, email, senha, usuario, nome, academico) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			ps.setInt( 1, new IdGenerator().getNextCadastroId() );
			ps.setString(2, email);
			ps.setString(3, senha);
			ps.setString(4, usuario);
			ps.setString(5, nome);
			ps.setInt(6, academico);
			
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
