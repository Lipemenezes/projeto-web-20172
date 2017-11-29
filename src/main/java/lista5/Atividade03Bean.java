package lista5;

import javax.faces.bean.ManagedBean;

import dao.EnderecoDao;
import dao.PessoaDao;
import entity.Endereco;
import entity.Pessoa;

@ManagedBean
public class Atividade03Bean {
	
	private Endereco endereco = new Endereco();
	private Pessoa pessoa = new Pessoa();

    public Atividade03Bean() {
    }
	
	public void salvarEnd() {
		EnderecoDao dao = new EnderecoDao();
		dao.inserir(endereco);
		voltar();
	}
	
	public void salvarPessoa() {
		PessoaDao dao = new PessoaDao();
		dao.inserir(pessoa);
		voltar();
	}
	
	public String voltar() {
		return "ex3.xhtml";
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
		
}
