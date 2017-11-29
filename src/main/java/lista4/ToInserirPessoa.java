package lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import entity.Pessoa;

public class ToInserirPessoa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {
				// Encaminha para a página de detalhe/edição
		return "/lista04/cadastroPessoa.jsp";
	}

}
