package br.sc.senac.lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import entity.Pessoa;

public class ObterPessoa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		PessoaDao pessoaDao = new PessoaDao();
		int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
		Pessoa pessoa = pessoaDao.obterPorId(idPessoa);

		// Coloca o objeto consultado como atributo da requisição
		request.setAttribute("pessoa", pessoa);

		// Encaminha para a página de detalhe/edição
		return "/lista04/edicaoPessoa.jsp";
	}

}
