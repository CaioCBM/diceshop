package br.unitins.diceshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.doclint.Entity;

import br.unitins.diceshop.model.TipoUsuario;
import br.unitins.diceshop.model.Usuario;

//Cadastra TipoUsuario null como Cliente automaticamente

public class UsuarioDAO extends DAO<Usuario> {
	
	public boolean create (Usuario usuario) {
		
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nome, login, senha, datanascimento, email, tipousuario ) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? , ? , ? , ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setString(5, usuario.getEmail());
			if (usuario.getTipoUsuario() != null)
				stat.setInt(6, usuario.getTipoUsuario().getId());
			else
				stat.setInt(6, 3);
//			stat.setInt(7, usuario.getEndereco().getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Inclus�o realizada com sucesso.");
			
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

	public boolean update(Usuario usuario) {
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario ");
		sql.append("	SET nome=?, login=?, senha=?, datanascimento=?, email=?, tipousuario=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getLogin());
			stat.setString(3, usuario.getSenha());
			stat.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setString(5, usuario.getEmail());
			stat.setInt(6, usuario.getTipoUsuario().getId());
			stat.setInt(7, usuario.getId());
			
			stat.execute();
			
			conn.commit();

			System.out.println("Altera��o realizada com sucesso.");
			
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
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
			conn.commit();

			System.out.println("Remo��o realizada com sucesso.");
			
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

	public List<Usuario> findAll() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));
				
				listaUsuario.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaUsuario;
	}
	
	public Usuario findById(int id) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");

		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return usuario;
	}
	
	public Usuario verificarLoginSenha(String login, String senha) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	login = ? ");
		sql.append("	AND senha = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return usuario;
	}
	
	public Usuario verificarSenhaAlterar(int id, String senha) {
		Usuario usuario = null;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		sql.append("	AND senha = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		
		return usuario;
	}
	
	public List<Usuario> findByNome(String nome) {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, login, senha, datanascimento, email, tipousuario ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	nome ilike ? ");
		sql.append("ORDER BY nome ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome  + "%");
			
			ResultSet rs = stat.executeQuery();
			
			Usuario usuario = null;
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usuario.setEmail(rs.getString("email"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getInt("tipousuario")));
				
				listaUsuario.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaUsuario;
	}
	
	public boolean adicionaEndereco(Usuario usuario) {
		//Chama usuario da sessao
		boolean retorno = false;
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO endereco ");
		sql.append("	( cep, endereco ) ");
		sql.append("VALUES ");
		sql.append("	( ? , ? ) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, usuario.getEndereco().getCep());
			stat.setString(2, usuario.getEndereco().getEndereco());
			
			stat.execute();
			
			ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			
			if (adicionaChaveEstrangeira(usuario.getId(), rs.getInt( "id" ), conn) == false) {
				throw new Exception("Erro ao incluir endereco");
			}
			
			conn.commit();

			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;	
	}
	
	public boolean adicionaChaveEstrangeira(int idusuario, int idendereco, Connection conn) {
		boolean retorno = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE  usuario ");
		sql.append("	SET idendereco = ? ");
		sql.append(" WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
            stat.setInt(1, idendereco);
            stat.setInt(2, idusuario);
            stat.execute();

            retorno = true;
		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
            closeStatement(stat);
        }

		return retorno;
	}


}
