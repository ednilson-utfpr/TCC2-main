let pedidoItems = [];

$(document).ready(function () {
    pedidoItems = JSON.parse(localStorage.getItem("carrinho"));

    if (pedidoItems) {
        pedidoItems.forEach(function (pedidoItem) {
            //Se pedidoItem.produtoCustomizado != null então monta linha de produto customizado
            //Se pedidoItem.produto != null então monta linha de produto normal;
            if($.isEmptyObject(pedidoItem.produtoCustomizado)) {
              adicionarLinhaCarrinho(criarLinhaProduto(pedidoItem));
            } else {
              adicionarLinhaCarrinho(criarLinhaProdutoCustomizado(pedidoItem))
            }
        })
    }
    showCart();
})

$('#bill-to').submit(function (e) {
    $('#bill-to').parsley().validate();

    if (!($('#bill-to').parsley().isValid())) {
        return false;
    }
});

function criarLinhaProdutoCustomizado(pedidoItem) {
    return `
    <tr id="produto-${pedidoItem.produtoCustomizado.id}">
        <td name="imagem" class="cart_product">
            <a href=""><img src="${pedidoItem.produtoCustomizado.imagem}" alt=""></a>
        </td>
        <td name="descricao" class="cart_description">
            <h4><a href="">${pedidoItem.produtoCustomizado.cobertura.nome}, ${pedidoItem.produtoCustomizado.recheio.nome}, ${pedidoItem.produtoCustomizado.formato.nome}, ${pedidoItem.produtoCustomizado.massa.nome}</a></h4>
            <p>Web ID: ${pedidoItem.produtoCustomizado.id}</p>
        </td>
        <td name="valor" class="cart_price">
            <p value="${pedidoItem.valor}">${new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'})
        .format(pedidoItem.valor)} </p>
        </td>
        <td class="cart_quantity">
            <input class="cart_quantity_input" type="number" min="1" step="1" value="${pedidoItem.quantidade}" onchange="updateValue(this)"/>
        </td>
        <td class="cart_total">
            <p value="${pedidoItem.valor * pedidoItem.quantidade}" class="cart_total_price">${formatToReal(pedidoItem.valor * pedidoItem.quantidade)}</p>
        </td>
        <td class="cart_delete">
            <a onclick="removerProduto(this, event);" class="cart_quantity_delete" href="" title="Remover produto" data-toggle="tooltip">
                <i class="fa fa-times"></i>
            </a>
        </td>
    </tr>
    `;
}

function criarLinhaProduto(pedidoItem) {
    return `
    <tr id="produto-${pedidoItem.produto.id}">
        <td name="imagem" class="cart_product">
            <a href=""><img src="${pedidoItem.produto.imagem}" alt=""></a>
        </td>
        <td name="descricao" class="cart_description">
            <h4><a href="">${pedidoItem.produto.nome}</a></h4>
            <p>Web ID: ${pedidoItem.produto.id}</p>
        </td>
        <td name="valor" class="cart_price">
            <p value="${pedidoItem.valor}">${new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'})
        .format(pedidoItem.valor)} </p>
        </td>
        <td class="cart_quantity">
            <input class="cart_quantity_input" type="number" min="1" step="1" value="${pedidoItem.quantidade}" onchange="updateValue(this)"/>
        </td>
        <td class="cart_total">
            <p value="${pedidoItem.valor * pedidoItem.quantidade}" class="cart_total_price">${formatToReal(pedidoItem.valor * pedidoItem.quantidade)}</p>
        </td>
        <td class="cart_delete">
            <a onclick="removerProduto(this, event);" class="cart_quantity_delete" href="" title="Remover produto" data-toggle="tooltip">
                <i class="fa fa-times"></i>
            </a>
        </td>
    </tr>
    `;
}

function adicionarLinhaCarrinho(linha) {
    if ($('#tdPedidoItems tbody') == 0)
        $('#tdPedidoItems').append('<tbody> </tbody>');

    $('#tdPedidoItems tbody').append(linha);
    atualizarCarrinho();
}

function removerProduto(element, e) {
    e.preventDefault();

    if (window.confirm("Você realmente deseja remover o produto?!")) {
        let row = $(element).parent().parent();
        let id = row[0].id.split('-')[1];
        $(row).css('opacity', '.5');
        setTimeout(function () {
            $(row).remove();
            removeProdutoArray(id);
            updateTotal();
            showCart();
            atualizarCarrinho();
        }, 500);
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

function removeProdutoArray(id) {
    let carrinho = JSON.parse(localStorage.getItem("carrinho"));
    let novoCarrinho = carrinho.filter(pedidoItem => (pedidoItem.produto && pedidoItem.produto.id !==id )
    || (pedidoItem.produtoCustomizado && pedidoItem.produtoCustomizado.id != id));
    localStorage.setItem("carrinho", JSON.stringify(novoCarrinho));
}

//FORMATA O VALOR P REAL
function formatToReal(valor) {
    return new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(valor);
}

//ATUALIZA O VALOR X QUANTIDADE
function updateValue(input) {
    let row = input.parentElement.parentElement;
    let valor = row.getElementsByClassName('cart_price')[0].children[0].getAttribute('value');
    row.getElementsByClassName('cart_total_price')[0].textContent = formatToReal(input.value * valor);
    updateLocalStorage(row.id.split('-')[1], input.value);
    updateTotal();
    atualizarCarrinho();
}

function updateLocalStorage(id, qtde) {
    let carrinho = JSON.parse(localStorage.getItem("carrinho"));
    carrinho.forEach(function (pedidoItem) {
        if (pedidoItem.produto && pedidoItem.produto.id !==id ){
            pedidoItem.quantidade = Number(qtde);
        }else if( pedidoItem.produtoCustomizado && pedidoItem.produtoCustomizado.id != id)  {
            pedidoItem.quantidade = Number(qtde);
        }
    })
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
}

function getNumber(jqueryInput) {
    return Number(jqueryInput.text().split('$')[1].replace('.', '').replace(',', '.'));
}

function updateTotal() {
    if ($("#totalCost").length > 0) {
        let total = 0;
        $(".cart_total_price").each(function (index) {
            let valor = getNumber($(this));
            total += valor;
        });
        $('#subTotal').text(formatToReal(total));
        $('#totalCost').text(formatToReal(getNumber($('#subTotal'))));
    }
}

function showCart() {
    if ($('.cart_info tbody tr').length === 0) {
        $('#container-vazio').show();
        $('#container-cheio').hide();
    } else {
        $('#container-vazio').hide();
        $('#container-cheio').show();
    }
}

function saveCliente() {
    $.ajax({
        type: 'POST',
        url: '/cliente',
        data: $('#bill-to').serialize(),
        success: function () {
            Swal.fire('Salvo!', 'Cliente cadastrado com sucesso!', 'success').then((result) =>
                location.reload()
            );
        },
        error: function () {
            Swal.fire('Erro!', 'Falha ao realizar o cadastro!', 'error');
        }
    });
    return false;
}

//FINALIZAR O PEDIDO
function finalizarPedido(urlDestino) {
    let pedido = {};
    pedido.cliente = {};
    pedido.valorTotal = getNumber($('#totalCost'));
    pedido.valorFrete = 0.0;
    pedido.tipoFrete = 0;
    pedido.tipoPagamento = Number($('input[name="tpPagto"]:checked').val());

    let pedidoItem = JSON.parse(localStorage.getItem("carrinho"));

    let data = JSON.stringify({
        "pedido": pedido,
        "pedidoItem": pedidoItem
    });

    //MENSAGEM DE SUCESSO NO PEDIDO
    $.ajax({
        type: $('#frmPedido').attr('method'),
        url: $('#frmPedido').attr('action'),
        contentType: 'application/json',
        data: data,
        success: function () {
            Swal.fire('Obrigado!', 'Seu pedido foi realizado com sucesso!', 'success').then((result) => {
                    localStorage.removeItem("carrinho");
                    window.location = urlDestino;
                }
            );
        },
        error: function () {
            Swal.fire('Erro!', 'Falha ao finalizar pedido...!', 'error');
        }
    });
    return false;
}
