package prova.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import prova.dao.CarroDAO;
import prova.entity.Carro;

@ManagedBean()
public class ProvaBean implements Serializable {

	private static final long serialVersionUID = -8363296722786327504L;

	private String ano1;
	private String ano2;
	private String valor1;
	private String valor2;
	private String montadora;
	private String modelo;
	private List<Carro> carros = new ArrayList<>();
	
	private CarroDAO dao = new CarroDAO();
	
	public ProvaBean() {
		carros = dao.listar();
	}
	
	public void filtroAno() {
		carros = dao.listarPorAno(ano1, ano2);
	}
	
	public void filtroValor() {
		carros = dao.listarPorValor(valor1, valor2);
	}
	
	public void filtroMontadora() {
		carros = dao.listarPorNomeDaMontadora(montadora);
	}
	
	public void filtroModelo() {
		carros = dao.listarPorModeloDoCarro(modelo);
	}

	public String getAno1() {
		return ano1;
	}

	public void setAno1(String ano1) {
		this.ano1 = ano1;
	}

	public String getAno2() {
		return ano2;
	}

	public void setAno2(String ano2) {
		this.ano2 = ano2;
	}

	public String getValor1() {
		return valor1;
	}

	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

	public String getValor2() {
		return valor2;
	}

	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}

	public String getMontadora() {
		return montadora;
	}

	public void setMontadora(String montadora) {
		this.montadora = montadora;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
		
}