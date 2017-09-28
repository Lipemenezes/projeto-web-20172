<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>Lista 4 - Cadastro Pessoa</title>
	</head>
	<body>
		<form action="/projeto-web/Controller?classe=InserirPessoa" method="POST">
			<fieldset>
				<legend>Dados da Pessoa:</legend>
				<label for="nome">Nome </label><input type="text" name="nome" value="${pessoa.nome}" required><br> 
				<label for="cpf">Cpf </label><input type="text" name="cpf" value="${pessoa.cpf}" required>
			</fieldset>
			<fieldset>	
				<legend>Endereço:</legend>
				<label for="rua">Rua </label><input type="text" name="rua" value="${pessoa.endereco.rua}"><br>
				<label for="numero">Numero </label><input type="text" name="numero" value="${pessoa.endereco.numero}"><br> 
				<label for="bairro">Bairro</label><input type="text" name="bairro" value="${pessoa.endereco.bairro}"><br> 
			</fieldset>
			<input type="SUBMIT" value="Inserir pessoa"><br>
		</form>
	</body>
</html>