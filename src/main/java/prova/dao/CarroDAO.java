package prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prova.entity.Carro;
import prova.entity.Montadora;
import dao.ConnectionFactory;

public class CarroDAO {

	private Connection conexao;

	public CarroDAO() {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}

	public Carro obterPorId(int idCarro) {
		Carro c = null;

		String sql = " SELECT * FROM CARRO WHERE ID=? LIMIT 1";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idCarro);
			// stmt.setMaxRows(1);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c = this.criarCarroResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public int inserir(Carro c) {
		int idInserido = -1;
		String sql = "INSERT INTO CARRO(ID_MONTADORA, ANO, MODELO, VALOR) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setInt(1, c.getMontadora().getId());
			ps.setInt(2, c.getAno());
			ps.setString(3, c.getModelo());
			ps.setDouble(4, c.getValor());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Carro inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir carro");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}

	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM CARRO WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Carro removido com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover carro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}

	// ID_MONTADORA INT NOT NULL,
	// ANO INT NULL,
	// MODELO VARCHAR(255) NOT NULL,
	// VALOR DOUBLE NULL,

	public boolean atualizar(Carro c) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE CARRO SET ID_MONTADORA=?, ANO=?, MODELO=?, VALOR=? WHERE ID=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setInt(1, c.getMontadora().getId());
			instrucaoSQL.setInt(2, c.getAno());
			instrucaoSQL.setString(3, c.getModelo());
			instrucaoSQL.setDouble(4, c.getValor());
			instrucaoSQL.setInt(5, c.getId());

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Carro atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar carro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}
	
	public ArrayList<Carro> listar() {
		ArrayList<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM CARRO";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				Carro c = criarCarroResultSet(resultadoConsulta);
				carros.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}

	public ArrayList<Carro> listarPorAno(String ano1, String ano2) {
		ArrayList<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM CARRO WHERE ANO BETWEEN " + ano1 + " and " + ano2;

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				Carro c = criarCarroResultSet(resultadoConsulta);
				carros.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}
	
	public ArrayList<Carro> listarPorValor(String valor1, String valor2) {
		ArrayList<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM CARRO WHERE VALOR BETWEEN " + valor1 + " and " + valor2;

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				Carro c = criarCarroResultSet(resultadoConsulta);
				carros.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}
	
	public ArrayList<Carro> listarPorNomeDaMontadora(String query) {
		ArrayList<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM CARRO C INNER JOIN MONTADORA M ON M.ID = C.ID_MONTADORA WHERE M.NOME LIKE '%" + query + "%'";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				Carro c = criarCarroResultSet(resultadoConsulta);
				carros.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}
	
	public ArrayList<Carro> listarPorModeloDoCarro(String query) {
		ArrayList<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM CARRO WHERE MODELO LIKE '%" + query + "%'";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				// Cria uma nova montadora para cada item retornado no resultSet
				// Usa como chave o nome da coluna na tabela
				Carro c = criarCarroResultSet(resultadoConsulta);
				carros.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carros;
	}

	private Carro criarCarroResultSet(ResultSet resultadoConsulta) {
		Carro c = null;

		try {
			c = new Carro();
			c.setId(resultadoConsulta.getInt("id"));

			// Obtém a montadora do carro chamando MontadoraDAO
			// Afinal, a tabela Carro tem apenas o id_montadora
			int idMontadora = resultadoConsulta.getInt("id_montadora");
			MontadoraDAO montadoraDAO = new MontadoraDAO();
			Montadora m = montadoraDAO.obterPorId(idMontadora);

			c.setMontadora(m);
			c.setAno(resultadoConsulta.getInt("ano"));
			c.setModelo(resultadoConsulta.getString("modelo"));
			c.setValor(resultadoConsulta.getDouble("valor"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

//	public ArrayList<Carro> listarPorFiltro(FiltroCarro filtro) {
//		ArrayList<Carro> carros = new ArrayList<Carro>();
//
//		String sql = "SELECT c.* FROM Carro c " + "INNER JOIN Montadora m ON c.id_montadora = m.id ";
//
//		if (filtro.temCampoPreenchido()) {
//			sql = preencherFiltrosConsulta(sql, filtro);
//		}
//
//		try {
//			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
//			if (filtro.temCampoPreenchido()) {
//				instrucaoSQL = preencherValoresFiltro(instrucaoSQL, filtro);
//			}
//			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();
//
//			while (resultadoConsulta.next()) {
//				// Cria uma nova montadora para cada item retornado no resultSet
//				// Usa como chave o nome da coluna na tabela
//				Carro c = criarCarroResultSet(resultadoConsulta);
//				carros.add(c);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return carros;
//	}

//	private PreparedStatement preencherValoresFiltro(PreparedStatement instrucaoSQL, FiltroCarro filtro) {
//
//		try {
//			int i = 1;
//			// Preenche os valores na ordem
//			if (filtro.getModelo() != null && !filtro.getModelo().isEmpty()) {
//				instrucaoSQL.setString(i, filtro.getModelo());
//				i++;
//			}
//
//			if (filtro.getNomeMontadora() != null && !filtro.getNomeMontadora().isEmpty()) {
//				instrucaoSQL.setString(i, filtro.getNomeMontadora());
//				i++;
//			}
//
//			if (filtro.getPaisMontadora() != null && !filtro.getPaisMontadora().isEmpty()) {
//				instrucaoSQL.setString(i, filtro.getPaisMontadora());
//				i++;
//			}
//
//			if (filtro.getValorInicial() > 0 && filtro.getValorFinal() > 0) {
//				// Seta 2 valores (inicial e final) para formar um intervalo
//				instrucaoSQL.setDouble(i, filtro.getValorInicial());
//				i++;
//				instrucaoSQL.setDouble(i, filtro.getValorFinal());
//				i++;
//			}
//
//			if (filtro.getAnoInicial() > 0 && filtro.getAnoFinal() > 0) {
//				instrucaoSQL.setInt(i, filtro.getAnoInicial());
//				i++;
//				instrucaoSQL.setInt(i, filtro.getAnoFinal());
//				i++;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return instrucaoSQL;
//	}

//	private String preencherFiltrosConsulta(String sql, FiltroCarro filtro) {
//
//		boolean primeiroCampo = true;
//
//		sql += " WHERE ";
//
//		// Monta a consulta verificando cada campo do filtro
//		if (filtro.getModelo() != null && !filtro.getModelo().isEmpty()) {
//			if (!primeiroCampo) {
//				sql += " AND ";
//			}
//			sql += " c.modelo =? ";
//			primeiroCampo = false;
//		}
//
//		if (filtro.getNomeMontadora() != null && !filtro.getNomeMontadora().isEmpty()) {
//			if (!primeiroCampo) {
//				sql += " AND ";
//			}
//			// Nome da MONTADORA (M) --> olhar no SELECT
//			sql += " m.nome=? ";
//			primeiroCampo = false;
//		}
//
//		if (filtro.getPaisMontadora() != null && !filtro.getPaisMontadora().isEmpty()) {
//			if (!primeiroCampo) {
//				sql += " AND ";
//			}
//			// Nome da MONTADORA (M) --> olhar no SELECT
//			sql += " m.pais=? ";
//			primeiroCampo = false;
//		}
//
//		if (filtro.getValorInicial() > 0 && filtro.getValorFinal() > 0) {
//			if (!primeiroCampo) {
//				sql += " AND ";
//			}
//			// Nome da MONTADORA (M) --> olhar no SELECT
//			sql += " c.valor BETWEEN ? AND ? ";
//			primeiroCampo = false;
//		}
//
//		if (filtro.getAnoInicial() > 0 && filtro.getAnoFinal() > 0) {
//			if (!primeiroCampo) {
//				sql += " AND ";
//			}
//			sql += " c.ano BETWEEN ? AND ? ";
//			primeiroCampo = false;
//		}
//
//		return sql;
//	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}