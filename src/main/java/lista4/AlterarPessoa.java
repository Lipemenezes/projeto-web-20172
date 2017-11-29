package lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDao;
import entity.Pessoa;
import entity.Endereco;

public class AlterarPessoa implements Acao {

	public String executar(HttpServletRequest request, HttpServletResponse response) {

		Pessoa pessoa = new Pessoa();
		pessoa.setId(Long.parseLong(request.getParameter("idpessoa")));
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setCpf(request.getParameter("cpf"));
		
		Endereco endereco = new Endereco();
		endereco.setId(Long.parseLong(request.getParameter("idendereco")));
		endereco.setRua(request.getParameter("rua"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setBairro(request.getParameter("bairro"));

		pessoa.setEndereco(endereco);

		PessoaDao pessoaDao = new PessoaDao();

		if (pessoaDao.atualizar(pessoa) == true) {
			request.setAttribute("msg", "Pessoa atualizado com sucesso, ID: " + pessoa.getId());
		} else {
			request.setAttribute("msg", "Erro ao atualizar pessoa, ID: " + pessoa.getId());
		}

		ListarPessoas acaoListarPessoas = new ListarPessoas();
		return acaoListarPessoas.executar(request, response);
	}
}
