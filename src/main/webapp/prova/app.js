function validarCpf() {
	var cpf = $('#cpf');

	if (cpf.val().length < 14) {
		alert('O cpf deve ter 14 dígitos');
	} else {
		var countPontos = cpf.val().split(".").length - 1;

		var countTracos = cpf.val().split("-").length - 1;

		if (countPontos != 2 || countTracos != 1)
			alert('O cpf deve conter 2 pontos e um traço');
		else 
			return true;
	}
}

function validarNome() {
	var nome = $('#nome');
	if (nome.val().length == 0) {
		alert('Preencha o nome');
	} else {
		return true;
	}
}

function getPessoa() {

	if(validarNome() && validarCpf()) {
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/projeto-web/provaServlet",
			data: {
				'cpf': $('#cpf').val()
			},
			success: function(resp) {
			},
			fail: function(resp) {
			},
			complete:function(resp) {
		        var respTxt = resp.responseText.split(',');
		        alert('Nome:' + respTxt[0] + ' | Sobrenome:' + respTxt[1]);
		    },
			dataType: "text/plain"
		});
	}
}
