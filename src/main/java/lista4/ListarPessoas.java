package lista4;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import entity.Pessoa;

public class ListarPessoas implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
		PessoaDao pessoaDao = new PessoaDao();
		List<Pessoa> pessoas = pessoaDao.listar();
		request.setAttribute("pessoas", pessoas);
		return "/lista04/lista.jsp";
	}

}
