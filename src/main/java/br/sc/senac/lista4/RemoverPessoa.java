package br.sc.senac.lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import dao.EnderecoDao;

public class RemoverPessoa implements Acao {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("idPessoa"));

		//Remover carro dado um id
		PessoaDao dao = new PessoaDao();
		boolean removeu = dao.excluir(id);
		
		String mensagem;
		if(removeu) {
			mensagem = "Pessoa de id (" + id + ") foi removida";  
		}else {
			mensagem = "Erro ao remover pessoa";
		}
		
		request.setAttribute("msg", mensagem);
		
		return "/lista04/lista.jsp";
	}

}
