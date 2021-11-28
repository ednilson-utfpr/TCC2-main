/*price range*/
if ($.fn.slider) {
    $('#sl2').slider();
}

var RGBChange = function () {
    $('#RGB').css('background', 'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue() + ')')
};

/*scroll to top*/

$(document).ready(function () {
    $(function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            scrollDistance: 300, // Distance from top/bottom before showing element (px)
            scrollFrom: 'top', // 'top' or 'bottom'
            scrollSpeed: 300, // Speed back to top (ms)
            easingType: 'linear', // Scroll to top easing (see http://easings.net/)
            animation: 'fade', // Fade, slide, none
            animationSpeed: 200, // Animation in speed (ms)
            scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
            //scrollTarget: false, // Set a custom target element for scrolling to the top
            scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
            scrollTitle: false, // Set a custom <a> title if required.
            scrollImg: false, // Set true to use image
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
            zIndex: 2147483647 // Z-Index for the overlay
        });
    });
});


//FILTRA OS PRODUTOS POR CATEGORIA
function filtraCateg(element){
    let value = element.getAttribute('value');
    window.location = '/produto?categoria=' + value;
}

//ALTERAR ENDEREÇO ENTREGA - COM CHECK BOX
var cb = $("#cbEnd");

cb.on("click", function(){
const name = document.querySelector("#endEnt");
//const valor = name.value;
const endECakes = "Retirar: Rua E-CAKES, nª 16 - Bairro: Centro - Pato Branco - PR";
$("#endEnt").val(endECakes);
});







