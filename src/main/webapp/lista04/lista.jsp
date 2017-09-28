<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Aula 06 - Front Controller (listagem de carros)</title>
	</head>
	<body>
		<h1>Lista de carros</h1>
		<a href="/projeto-web/Controller?classe=ListarPessoas">Listar</a>
		<a href="/projeto-web/Controller?classe=ToInserirPessoa">Cadastrar</a>
		<table>
		<tr>
			<th>#</th>
			<th>Nome</th>
			<th>CPF</th>
			<th>#</th>
			<th>Rua</th>
			<th>Numero</th>
			<th>Bairro</th>
			<th colspan="2">Ações</th>
		</tr>
		<c:forEach items="${pessoas}" var="pessoa">
			<tr>
				<td>${pessoa.id}</td>
				<td>${pessoa.nome}</td>
				<td>${pessoa.cpf}</td>
				<td>${pessoa.endereco.id}</td>
				<td>${pessoa.endereco.rua}</td>
				<td>${pessoa.endereco.numero}</td>
				<td>${pessoa.endereco.bairro}</td>
				<td><a href="/projeto-web/Controller?classe=RemoverPessoa&idPessoa=${pessoa.id}">Remover</a></td>
				<td><a href="/projeto-web/Controller?classe=ObterPessoa&idPessoa=${pessoa.id}">Editar</a></td>
			</tr>
		</c:forEach>
		</table>
		<p>${msg}</p>
	</body>
</html>