package br.sc.senac.lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import entity.Pessoa;
import entity.Endereco;

public class InserirPessoa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		PessoaDao pessoaDao = new PessoaDao();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setCpf(request.getParameter("cpf"));
		
		Pessoa pessoaPorCpf = pessoaDao.obterPorCpf(pessoa.getCpf());
		Endereco endereco = new Endereco();
		endereco.setRua(request.getParameter("rua"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setBairro(request.getParameter("bairro"));
		
		
		pessoa.setEndereco(endereco);
		
		if(pessoaPorCpf != null) {
			pessoa.setId(pessoaPorCpf.getId());
			pessoa.getEndereco().setId(pessoaPorCpf.getEndereco().getId());
			
			if (pessoaDao.atualizar(pessoa)) {
				request.setAttribute("msg", "Pessoa atualizada com sucesso, ID: " + pessoa.getId());
			} else {
				return "/lista04/erro.jsp";
			}
			
		} else {
			
			pessoa.setId(new Long(pessoaDao.inserir(pessoa)));
			if (pessoa.getId() > 0) {
				request.setAttribute("msg", "Pessoa inserir com sucesso, ID: " + pessoa.getId());
			} else {
				return "/lista04/erro.jsp";
			}
			
		}

		
		ListarPessoas acaoListarPessoas = new ListarPessoas();
		return acaoListarPessoas.executar(request, response);
	}
}
