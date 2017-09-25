package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe respons·vel por criar e destruir conex√µes com bancos de dados
 * 
 * O banco escolhido foi o MySQL, assim È necess·rio utilizar o respectivo
 * driver JDBC
 * 
 * @author Vilmar C. Pereira J√∫nior
 * 
 *         Disciplina de Desenvolvimento Web Senac 2017.1
 *
 */
public class ConnectionFactory {

	public static Connection obterConexao() {
		String nomeEsquema = "dbprojeto1";
		String enderecoBanco = "jdbc:mysql://localhost/" + nomeEsquema;
		String usuario = "root";
		String senha = "1234";
		String driverJDBC = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driverJDBC);
			Connection conexao = DriverManager.getConnection(enderecoBanco, usuario, senha);
			System.out.println("Conex„o aberta");
			return conexao;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Erro ao obter conex„o com o banco: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void fecharConexao(Connection con) {
		try {
			con.close();
			System.out.println("Conex„o fechada");
		} catch (SQLException e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
	}
}