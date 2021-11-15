"use strict";

$('#form').submit(function(e){
	$('#form').parsley().validate();

	if(!($('#form').parsley().isValid())){
		return false;
	}
});// FIM FORM SUBMIT

function removerAjax(oFormElement, urlDestino) {
	if (window.confirm("Você realmente deseja remover o registro?!")) {
		var http = new XMLHttpRequest();
		http.open(oFormElement.method, oFormElement.action, true);
		http.onreadystatechange = function() {
			if (http.readyState === 4) {
				if (http.status === 200) {
                    Swal.fire('Sucesso!', 'Produto adicionado ao carrinho👍!', 'success');
                    setTimeout(() => window.location = urlDestino, 300);
				} else {
				    Swal.fire('Ops!', 'Falha ao remover o registro!', 'error');
				}
			}
		}// onreadystatechange
		http.send(new FormData(oFormElement));
	}
	return false;
}

function removerJQuery(id, url) {
	Swal.fire({
		title: "Deseja realmente remover o registro?!",
		text: "Esta ação não poderá ser desfeita!",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		cancelButtonText: "Cancelar",
		confirmButtonText: "Remover",
		closeOnConfirm: false
		}).then((result) => {
			if (result.isConfirmed) {
				const destino = url + '/' + id;
				$.ajax({
					type: 'DELETE',
					url: destino,
					success: function(){
						Swal.fire({
								title: 'Removido!',
								text: 'Registro removido com sucesso!',
								type: 'success'
							}).then((result) => {
								// window.location = url;
								$('#row_' + id).remove();
							}
						); //FIM swal()
					},
					error: function() {
						Swal.fire('Erro!',
							 'Falha ao remover registro!',
							 'error');
					}
				}); //FIM ajax()
			}
		} //FIM funcion swal
	); //FIM swal();
	
	
}

function salvar(urlDestino) {
	$.ajax({
		type: $('#frm').attr('method'),
		url: $('#frm').attr('action'),
		data: $('#frm').serialize(),
		success: function() {
			Swal.fire({
				title: 'Salvo!',
				text: 'Registor salvo com sucesso!',
				type: 'success'
				}).then((result) => {
					window.location = urlDestino;
				}
			);//FIM swal()
		},
		error: function() {
			Swal.fire('Errou!', 'Falha ao salvar registro!', 'error');
		}
	}); //FIM ajax()
	return false;
}
