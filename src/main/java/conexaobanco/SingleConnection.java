package conexaobanco;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	public static void conectar() {

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			System.out.println("Conecou com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return connection;
	}

}
