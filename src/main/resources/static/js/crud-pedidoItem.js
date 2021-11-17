"use strict";

$('#formPedidoItem').submit(function (e) {
    $('#formPedidoItem').parsley().validate();

    if (!($('#formPedidoItem').parsley().isValid())) {
        return false;
    }
});
//FUNÇÃO ADD - PRODUTO CUSTOMIZADO
function adicionaProdutoCustomizadoCarrinho() {
    var produtoCustomizadoId = Number($('#produtoCustomizadoId').attr('value'));
    //IMAGEM PRODUTO
    var produtoCustomizadoImagem = $('#produtoCustomizadoImagem').attr('value');
    var produtoCustomizadoValor = Number($('#produtoCustomizadoValor').attr('value'));
    var coberturaNome = $('#coberturaNome').attr('value');
    var recheioNome = $('#recheioNome').attr('value');
    var formatoNome = $('#formatoNome').attr('value');

    if (produtoCustomizadoId && produtoCustomizadoValor) {
        try {
            let cart = JSON.parse(localStorage.getItem("carrinho"));
            if (!cart) cart = [];

            let pedidoItem = {};
            pedidoItem.produtoCustomizado = {};
            //IMAGEM PRODUTO
            pedidoItem.produtoCustomizado.cobertura = {};
            pedidoItem.produtoCustomizado.recheio = {};
            pedidoItem.produtoCustomizado.formato = {};
            pedidoItem.pedido = {};

            pedidoItem.produtoCustomizado.id = produtoCustomizadoId;
             //IMAGEM PRODUTO
            pedidoItem.produtoCustomizado.imagem = produtoCustomizadoImagem;

            pedidoItem.produtoCustomizado.cobertura.nome = coberturaNome;
            pedidoItem.produtoCustomizado.recheio.nome = recheioNome;
            pedidoItem.produtoCustomizado.formato.nome = formatoNome;
            pedidoItem.quantidade = 1;
            pedidoItem.valor = produtoCustomizadoValor;

            cart.push(pedidoItem);

            localStorage.setItem("carrinho", JSON.stringify(cart));
            Swal.fire('Sucesso!', 'Produto adicionado ao carrinho👍!', 'success');
            atualizarCarrinho();
        } catch(err) {
            console.log(err);
            Swal.fire('Ops!', 'Ocorreu algum problema ao adicionar o produto no carrinho...!', 'error');
        }
    }
}

//FUNÇÃO ADD - PRODUTO NORMAL
function adicionarProduto(id, nome, imagem, valor) {
    try {
        let cart = JSON.parse(localStorage.getItem("carrinho"));
        if (!cart) cart = [];

        let qtde = 1;
        if ($('#quantidade').length) {
            qtde = $('#quantidade').val();
        }

        let existeProduto = false;
        cart.forEach(function (pedidoItem) {
            if (pedidoItem.produto?.id === id) {
                pedidoItem.quantidade = Number(pedidoItem.quantidade) + Number(qtde);
                existeProduto = true;
            }
        })

        if (!existeProduto) {
            let pedidoItem = {};
            pedidoItem.produto = {};
            pedidoItem.pedido = {};

            pedidoItem.produto.id = id;
            pedidoItem.produto.nome = nome;
            pedidoItem.produto.imagem = imagem;
            pedidoItem.valor = valor;
            pedidoItem.quantidade = qtde; //casos que não tem a inserção de quantidade

            cart.push(pedidoItem);
        }

        localStorage.setItem("carrinho", JSON.stringify(cart));
        Swal.fire('Sucesso!', 'Produto adicionado ao carrinho👍!', 'success');
        atualizarCarrinho();
    } catch (err) {
        console.log(err);
        Swal.fire('Ops!', 'Ocorreu algum problema ao adicionar o produto no carrinho...!', 'error');
    }
}

function atualizarCarrinho(){
    let cart = JSON.parse(localStorage.getItem("carrinho"));
    if(cart) {
        let total = 0;
        cart.forEach(function(el){ total = Number(total) + Number(el.quantidade)});
        $('#cartCount').text('(' + total +')');
    }
}

function removerAjax(oFormElement, urlDestino) {
    if (window.confirm("Você realmente deseja remover o registro?!")) {
        var http = new XMLHttpRequest();
        http.open(oFormElement.method, oFormElement.action, true);
        http.onreadystatechange = function () {
            if (http.readyState === 4) {
                if (http.status === 200) {
                    alert("Registro removido com sucesso!");
                    window.location = urlDestino;
                } else {
                    alert("Falha ao remover o registro!");
                }
            }
        }
        http.send(new FormData(oFormElement));
    }
    return false;
}

function salvar(urlDestino) {
    $.ajax({
        type: $('#formPedidoItem').attr('method'),
        url: $('#formPedidoItem').attr('action'),
        data: $('#formPedidoItem').serialize(),
        success: function () {
            Swal.fire('Salvo!', 'Registro salvo com sucesso!', 'success').then((result) => {
                    window.location = urlDestino;
                }
            );//FIM swal()
        },
        error: function () {
            Swal.fire('Errou!', 'Falha ao salvar registro!', 'error');
        }
    }); //FIM ajax()
    return false;
}
