package br.sc.senac.lista4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Acao {
	public String executar(HttpServletRequest request, HttpServletResponse response);
}
