package br.unitins.books.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.books.model.Dado;

public class DadoDAO extends DAO<Dado> {
	
	public boolean create (Dado dado) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO dado ");
		sql.append("	(descricao, lado, cor, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ?, ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, dado.getDescricao());
			stat.setString(2, dado.getLado());
			stat.setString(3, dado.getCor());
			if (dado.getPreco() != null)
				stat.setFloat(4, dado.getPreco());
			else
				stat.setNull(4, java.sql.Types.FLOAT);
			
			if (dado.getEstoque() != null)
				stat.setInt(5, dado.getEstoque());
			else
				stat.setNull(6, java.sql.Types.INTEGER);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclusão realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public boolean update(Dado dado) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE dado ");
		sql.append("	SET descricao=?, lado=?, cor=?, preco=?, estoque=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, dado.getDescricao());
			stat.setString(2, dado.getLado());
			stat.setString(3, dado.getCor());
			stat.setFloat(4, dado.getPreco());
			stat.setInt(5, dado.getEstoque());
			stat.setInt(6, dado.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Alteração realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;		
		
	}

	public boolean delete(int id) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM dado ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remoção realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;
	}

	public List<Dado> findAll() {
		List<Dado> listaDado = new ArrayList<Dado>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, lado, cor, preco, estoque ");
		sql.append("FROM ");
		sql.append("	dado ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Dado dado = null;
			
			while(rs.next()) {
				dado = new Dado();
				dado.setId(rs.getInt("id"));
				dado.setDescricao(rs.getString("descricao"));
				dado.setLado(rs.getString("lado"));
				dado.setCor(rs.getString("cor"));
				dado.setPreco(rs.getFloat("preco"));
				dado.setEstoque(rs.getInt("estoque"));
				listaDado.add(dado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaDado;
	}

	public List<Dado> findByDescricao(String descricao) {
		List<Dado> listaDado = new ArrayList<Dado>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, lado, cor, preco, estoque ");
		sql.append("FROM ");
		sql.append("	dado ");
		sql.append("WHERE ");
		sql.append("	descricao ilike ? ");
		sql.append("ORDER BY descricao ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + descricao  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Dado dado = null;
			
			while(rs.next()) {
				dado = new Dado();
				dado.setId(rs.getInt("id"));
				dado.setDescricao(rs.getString("descricao"));
				dado.setLado(rs.getString("lado"));
				dado.setCor(rs.getString("cor"));
				dado.setPreco(rs.getFloat("preco"));
				dado.setEstoque(rs.getInt("estoque"));
				listaDado.add(dado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaDado;
	}	
	
	public List<Dado> findByLado(String lado) {
		List<Dado> listaDado = new ArrayList<Dado>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, lado, cor, preco, estoque ");
		sql.append("FROM ");
		sql.append("	dado ");
		sql.append("WHERE ");
		sql.append("	lado ilike ? ");
		sql.append("ORDER BY lado ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + lado  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Dado dado = null;
			
			while(rs.next()) {
				dado = new Dado();
				dado.setId(rs.getInt("id"));
				dado.setDescricao(rs.getString("descricao"));
				dado.setLado(rs.getString("lado"));
				dado.setCor(rs.getString("cor"));
				dado.setPreco(rs.getFloat("preco"));
				dado.setEstoque(rs.getInt("estoque"));
				listaDado.add(dado);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaDado;
	}	
	
	public Dado findById(int id) {
		Dado dado = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, descricao, lado, cor, preco, estoque ");
		sql.append("FROM ");
		sql.append("	dado ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				dado = new Dado();
				dado.setId(rs.getInt("id"));
				dado.setDescricao(rs.getString("descricao"));
				dado.setLado(rs.getString("lado"));
				dado.setCor(rs.getString("cor"));
				dado.setPreco(rs.getFloat("preco"));
				dado.setEstoque(rs.getInt("estoque"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return dado;
	}

}
