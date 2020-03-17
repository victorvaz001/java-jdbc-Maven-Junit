package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDao;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TestBanco {

	@Test
	public void initBanco() {
		UserPosDao dao = new UserPosDao();

		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Jose");
		userposjava.setEmail("jose@hotmail.com");
		dao.salvar(userposjava);
	}

	@Test
	public void initListar() {

		UserPosDao dao = new UserPosDao();

		List<Userposjava> list = dao.listar();
		for (Userposjava userposjava : list) {
			System.out.println(userposjava);
		}
	}

	@Test
	public void listPorId() {

		UserPosDao dao = new UserPosDao();

		Userposjava userposjava = dao.buscaPorId(8L);

		System.out.println(userposjava);
	}

	@Test
	public void initatualizar() {

		UserPosDao dao = new UserPosDao();

		Userposjava userposjava = dao.buscaPorId(8L);
		userposjava.setNome("Paulo Santos");
		dao.atualizar(userposjava);

	}

	@Test
	public void initDelete() {

		UserPosDao dao = new UserPosDao();

		dao.deletar(9L);
	}

	@Test
	public void insertTelefone() {

		UserPosDao dao = new UserPosDao();
		Telefone telefone = new Telefone();

		telefone.setNumero("(31) 2256-6589");
		telefone.setTipo("Casa");
		telefone.setUsuario(2L);

		dao.salvarTelefone(telefone);
	}

	@Test
	public void listUserFone() {

		UserPosDao dao = new UserPosDao();
		List<BeanUserFone> beanUserFones = dao.listUserFone(2L);
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("---------------------------------------------");
		}

	}

	@Test
	public void testDeleteUserFone() {
		UserPosDao dao = new UserPosDao();

		dao.deletePorUser(1L);
	}
}
