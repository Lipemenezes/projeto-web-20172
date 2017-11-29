package aula01;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "formulario")
@SessionScoped

public class FormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private String nome;

	private String matricula;

	private char sexo;

	private Double nota1;

	private Double nota2;

	private Double nota3;

	private Double media;

	private List<Double> listaDasMedias;

	public void calcularMedia() throws IOException {

		media = (nota1 + nota2 + nota3) / 3;

		FacesContext.getCurrentInstance().getExternalContext().redirect("tabela.xhtml");

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Double getNota1() {
		return nota1;
	}

	public void setNota1(Double nota1) {
		this.nota1 = nota1;
	}

	public Double getNota2() {
		return nota2;
	}

	public void setNota2(Double nota2) {
		this.nota2 = nota2;
	}

	public Double getNota3() {
		return nota3;
	}

	public void setNota3(Double nota3) {
		this.nota3 = nota3;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public List<Double> getListaDasMedias() {
		return listaDasMedias;
	}

	public void setListaDasMedias(List<Double> listaDasMedias) {
		this.listaDasMedias = listaDasMedias;
	}

}