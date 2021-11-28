
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
    const valorMassa = getNumber($("#massa option:selected"));


//COLOCA DUAS CASAS DECIMAIS
const vt = formatToReal(valorRecheio + valorCobertura + valorMassa);
var valorTotalPed;
//SUBSTITUI (VIRGULA) POR (PONTO)
valorTotalPed = vt.replace(",", ".").replace("R$", "");
//SUBSTITUI (R$) POR (ESPAÇO VAZIO)
//var resultado = valorTotalPed.replace("R$", "");
//TRANFORMA O RESULTADO EM DOUBLE
const valorEmReais = parseFloat(valorTotalPed);
//SET A SOMA NO VALOR TOTAL DO FORM PROD.CUSTOMIZADO
    $('#valor').val(parseFloat(valorTotalPed));
    $('#valor').text(valorTotalPed);

}

function formatToReal(valor) {
    return new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(valor);
}

function apenasNumeros(string)
{
    var numsStr = string.replace(/[^0-9]/g,'');
    return parseInt(numsStr);
}




