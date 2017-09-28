package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Endereco;
import entity.Pessoa;

public class PessoaDao {

	private Connection conexao;

	public PessoaDao() {
		conexao = ConnectionFactory.obterConexao();
	}

	public int inserir(Pessoa p) {
		int idInserido = -1;
		String sql = "INSERT INTO PESSOA(NOME, CPF, ENDERECO) VALUES (?, ?)";
		try {
			PreparedStatement ps = this.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCpf());
			ps.setLong(3, p.getEndereco().getId());
			
			EnderecoDao enderecoDao = new EnderecoDao();
			enderecoDao.inserir(p.getEndereco());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Pessoa inserida com sucesso");
			} else {
				System.out.println("Erro ao inserir pessoa");
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idInserido;
	}

	public boolean excluir(int id) {
		boolean removidoSucesso = false;
		String sql = "DELETE FROM PESSOA WHERE ID = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);

			int codigoRetorno = ps.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Pessoa removida com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover pessoa.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}

	public boolean atualizar(Pessoa p) {
		boolean atualizadoSucesso = false;
		String sql = "UPDATE PESSOA SET NOME=?, CPF=? WHERE ID=?";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			instrucaoSQL.setString(1, p.getNome());
			instrucaoSQL.setString(2, p.getCpf());
			instrucaoSQL.setLong(3, p.getId());
			
			EnderecoDao enderecoDao = new EnderecoDao();
			enderecoDao.atualizar(p.getEndereco());

			int codigoRetorno = instrucaoSQL.executeUpdate();
			if (codigoRetorno == 1) {
				System.out.println("Montadora atualizada com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar montadora.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}

	public Pessoa obterPorId(int id) {
		Pessoa p = null;

		String sql = " SELECT * FROM PESSOA WHERE ID=? LIMIT 1";

		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				p = new Pessoa();
				p.setId(new Long(id));
				p.setNome(rs.getString("nome"));
				p.setCpf(rs.getString("cpf"));
				int idEnd = rs.getInt("endereco_id");
				EnderecoDao endDao = new EnderecoDao();
				Endereco endereco = endDao.obterPorId(idEnd);
				p.setEndereco(endereco);
			}
			

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}
	
	public ArrayList<Pessoa> listar() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();

		String sql = "SELECT * FROM PESSOA";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			while (resultadoConsulta.next()) {
				Long idEnd = resultadoConsulta.getLong("endereco_id");
				EnderecoDao endDao = new EnderecoDao();
				Endereco end = endDao.obterPorId(idEnd.intValue());
				Pessoa p = new Pessoa(resultadoConsulta.getLong("id"), resultadoConsulta.getString("nome"), resultadoConsulta.getString("cpf"), end);
				pessoas.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoas;
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}