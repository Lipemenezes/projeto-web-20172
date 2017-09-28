package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Endereco;
import entity.Pessoa;

public class EnderecoDao {

	private Connection conexao;

	public EnderecoDao() {
		conexao = ConnectionFactory.obterConexao();
	}

	public int inserir(Endereco end) {
		int idInserido = -1;
		String sql = "INSERT INTO ENDERECO(RUA, NUMERO, BAIRRO) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, end.getRua());
			ps.setString(2, end.getNumero());
			ps.setString(3, end.getBairro());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Endereco inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir endereco");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}

	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM ENDERECO WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Endereco removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover endereco.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}

	public boolean atualizar(Endereco end) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE ENDERECO SET RUA=?, NUMERO=?, BAIRRO=? WHERE ID=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, end.getRua());
			instrucaoSQL.setString(2, end.getNumero());
			instrucaoSQL.setString(3, end.getBairro());
			instrucaoSQL.setLong(4, end.getId());

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Endereco atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar endereco.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}

	public Endereco obterPorId(int id) {
		Endereco end = null;

		String sql = " SELECT * FROM ENDERECO WHERE ID=? LIMIT 1";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				end = new Endereco();
				end.setId(new Long(id));
				end.setRua(rs.getString("rua"));
				end.setBairro(rs.getString("bairro"));
				end.setNumero(rs.getString("numero"));
			}

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return end;
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}