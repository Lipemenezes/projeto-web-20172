package br.sc.senac.lista3.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercicio2Servlet
 */
@WebServlet("/Exercicio2Servlet")
public class Exercicio2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    String html = "<div><h1>String com HTML</h1></div>";	
	
    public Exercicio2Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().append(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.getWriter().append(html);
	}

}
