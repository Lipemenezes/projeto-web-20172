package prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prova.entity.Carro;
import prova.entity.Montadora;
import dao.ConnectionFactory;
import entity.Pessoa;

public class PessoaDAO {

	private Connection conexao;
	private Pessoa p;

	public PessoaDAO() {
		conexao = ConnectionFactory.getInstance().obterConexao();
	}
	
	public Pessoa pegarPorCpf(String cpf) {
		
		String sql = "SELECT * FROM PESSOA WHERE CPF = '" + cpf + "' limit 1";

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(sql);
			ResultSet resultadoConsulta = instrucaoSQL.executeQuery();

			if(resultadoConsulta.next()) {
				p = new Pessoa();
				p.setId(resultadoConsulta.getLong("id"));
				p.setNome(resultadoConsulta.getString("nome"));
				p.setSobrenome(resultadoConsulta.getString("sobrenome"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}


	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
}