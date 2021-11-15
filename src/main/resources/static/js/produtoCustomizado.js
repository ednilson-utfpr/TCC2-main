
function getNumber(jqueryInput) {
    const texto = jqueryInput.text().split('$')[1];
    return texto ? Number(texto) : 0;
}

function formatToReal(valor) {
    return new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(valor);
}
//ATUALIZA VALOR DO PRODUTO CUSTOMIZADO
function atualizaValorProduto() {
    const valorRecheio = getNumber($("#recheio option:selected"));
    const valorCobertura = getNumber($("#cobertura option:selected"));
    $('#valor').val(valorRecheio + valorCobertura * 1.7);
    $('#valor').text(valorRecheio + valorCobertura * 1.7);

    //70% do valor do recheio e cobertura vai para massa
}