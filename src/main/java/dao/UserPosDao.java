package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaobanco.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDao {

	private static Connection connection;

	public UserPosDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());

			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public List<Userposjava> listar() {
		List<Userposjava> list = new ArrayList<Userposjava>();

		try {
			String sql = "select * from userposjava";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();

			while (resultado.next()) {
				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));

				list.add(userposjava);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Userposjava buscaPorId(Long id) {
		Userposjava retorno = new Userposjava();

		try {
			String sql = "select * from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();

			while (resultado.next()) {
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public void atualizar(Userposjava userposjava) {
		try {
			String sql = "update userposjava set nome = ? where id =" + userposjava.getId();
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, userposjava.getNome());
			statment.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {

		try {
			String sql = "delete from userposjava where id =" + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "INSERT INTO telefoneuser( numero, tipo, usuariopessoa) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());

			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<BeanUserFone> listUserFone(Long idUser) {
		List<BeanUserFone> beanUserFone = new ArrayList<BeanUserFone>();
		try {

			String sql = " select nome, numero, email from telefoneuser as fone ";
			sql += " inner join userposjava as userp ";
			sql += " on fone.usuariopessoa = userp.id ";
			sql += " where userp.id = " + idUser;

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultado = preparedStatement.executeQuery();

			while (resultado.next()) {
				BeanUserFone userfone = new BeanUserFone();

				userfone.setNome(resultado.getString("nome"));
				userfone.setNumero(resultado.getString("numero"));
				userfone.setEmail(resultado.getString("email"));

				beanUserFone.add(userfone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUserFone;
	}

	public void deletePorUser(Long idUser) {
		try {
			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;

			PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
			preparedStatement.executeUpdate();
			connection.commit();

			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
