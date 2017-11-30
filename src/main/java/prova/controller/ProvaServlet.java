package prova.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Pessoa;
import prova.dao.PessoaDAO;

@WebServlet("/provaServlet")
public class ProvaServlet extends HttpServlet {
	private PessoaDAO dao;
	private Pessoa pessoa;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pessoa = new Pessoa();
		String cpf = request.getParameter("cpf");
		dao = new PessoaDAO();
		pessoa = dao.pegarPorCpf(cpf);
		String objectToReturn = pessoa.getNome() + "," + pessoa.getSobrenome();		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(objectToReturn);
	}

}