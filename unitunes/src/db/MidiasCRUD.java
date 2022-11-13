package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import model.Cadastro;
import model.Midia;

public class MidiasCRUD {
	public MidiasCRUD() {		
	}

	public void excluirMidia(int idMidia) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM midias WHERE idMidia = ?";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			ps.setInt(1, idMidia);
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
	public Midia lerMidia(int id) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Midia midia = new Midia();
		
		String sql = "SELECT * FROM midias WHERE idMidia = ?";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.first();

			rs.last();
			if (rs.getRow() < 1){
			    // process case with no results
				midia.setIdMidia(-1);
				return midia;
				
			} else{
			    // process case with results
				midia.setIdMidia(rs.getInt("idMidia"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setCategoria(rs.getInt("categoria"));
				midia.setInfoCategoria(rs.getDouble("infoCategoria"));
				midia.setValor(rs.getDouble("valor"));
				midia.setDataCriacao(rs.getDate("dataCriacao"));
				boolean isLivreAcesso = false;
				if(rs.getInt("isLivreAcesso") == 1)
					isLivreAcesso = true;
				midia.setLivreAcesso(isLivreAcesso);
				midia.setNomeCriadores(rs.getString("nomeCriadores"));
				midia.setConteudo(rs.getString("conteudo"));
				midia.setImagem(rs.getString("imagem"));
				midia.setCriadorMidia(rs.getInt("criadorMidia"));
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
			return midia;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Midia> lerTodasMidias() {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Midia midia;
		ArrayList<Midia> midias = new ArrayList<>();
		
		String sql = "SELECT * FROM midias";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
            while(rs.next())
            {
            	midia = new Midia();
				midia.setIdMidia(rs.getInt("idMidia"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setCategoria(rs.getInt("categoria"));
				midia.setInfoCategoria(rs.getDouble("infoCategoria"));
				midia.setValor(rs.getDouble("valor"));
				midia.setDataCriacao(rs.getDate("dataCriacao"));
				boolean isLivreAcesso = false;
				if(rs.getInt("isLivreAcesso") == 1)
					isLivreAcesso = true;
				midia.setLivreAcesso(isLivreAcesso);
				midia.setNomeCriadores(rs.getString("nomeCriadores"));
				midia.setConteudo(rs.getString("conteudo"));
				midia.setImagem(rs.getString("imagem"));
				midia.setCriadorMidia(rs.getInt("criadorMidia"));
				midias.add(midia);
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
		    
			return midias;
		}		
	}

	public void criarMidia(int idCriador, String nomeCriador, String nomeMidia, String descricaoMidia, double valor,
			int livreAcesso, java.sql.Date data) {

		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO midias (idMidia, nome, descricao, valor, dataCriacao, isLivreAcesso, nomeCriadores, criadorMidia) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			ps.setInt(1, new IdGenerator().getNextMidiaId());
			ps.setString(2, nomeMidia);
			ps.setString(3, descricaoMidia);
			ps.setDouble(4, valor);
			ps.setDate(5, data);
			ps.setInt(6, livreAcesso);
			ps.setString(7, nomeCriador);
			ps.setInt(8, idCriador);
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
