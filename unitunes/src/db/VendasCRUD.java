package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Venda;

public class VendasCRUD {
	public VendasCRUD() {
		
	}

	@SuppressWarnings("finally")
	public int adicionarVenda(double valor, Date data, int comprador, int vendedor, int idMidiaVendida, String descricao) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int idVenda = -1;
		
		String sql = "INSERT INTO vendas (idVenda, valor, data, comprador, vendedor, idMidiaVendida, descricao) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			
			idVenda = new IdGenerator().getNextVendaId();
			ps.setInt(1, idVenda);
			ps.setDouble(2, valor);
			ps.setDate(3, data);
			ps.setInt(4, comprador);
			ps.setInt(5, vendedor);
			ps.setInt(6, idMidiaVendida);
			ps.setString(7, descricao);			
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
		    
		    return idVenda;
		}		
	}

	@SuppressWarnings("finally")
	public ArrayList<Venda> lerTodasVendas() {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Venda venda;
		ArrayList<Venda> vendas = new ArrayList<>();
		
		String sql = "SELECT * FROM vendas";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			
            while(rs.next())
            {
            	venda = new Venda();
            	venda.setIdVenda(rs.getInt("idVenda"));
            	venda.setValor(rs.getDouble("valor"));
            	venda.setData(rs.getDate("data"));
            	venda.setComprador(rs.getInt("comprador"));
            	venda.setVendedor(rs.getInt("vendedor"));
            	venda.setIdMidiaVendida(rs.getInt("idMidiaVendida"));
            	venda.setDescricao(rs.getString("descricao"));
				vendas.add(venda);
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
		    
			return vendas;
		}		
	}

	@SuppressWarnings("finally")
	public Venda lerVenda(int idVenda) {
		DatabaseConnection db = new DatabaseConnection();
		Connection connection = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Venda venda = new Venda();
		
		String sql = "SELECT * FROM vendas WHERE idVenda = ?";
		try {
			ps = connection.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, idVenda);
			rs = ps.executeQuery();
			rs.first();
			rs.last();
			if (rs.getRow() < 1){
			    // process case with no results
				venda.setIdVenda(-1);
				return venda;
				
			} else{
			    // process case with results
            	venda.setIdVenda(rs.getInt("idVenda"));
            	venda.setValor(rs.getDouble("valor"));
            	venda.setData(rs.getDate("data"));
            	venda.setComprador(rs.getInt("comprador"));
            	venda.setVendedor(rs.getInt("vendedor"));
            	venda.setIdMidiaVendida(rs.getInt("idMidiaVendida"));
            	venda.setDescricao(rs.getString("descricao"));
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
		    
			return venda;
		}		
	}	
	
}
