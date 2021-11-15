jQuery(function ($) {
    'use strict';

    var form = $('.contact-form');
    form.submit(function () {
        var $this = $(this);
        $.post("sendemail.php", $(".contact-form").serialize(), function (result) {
            if (result.type == 'success') {
                $this.prev().text(result.message).fadeIn().delay(3000).fadeOut();
            }
        });
        return false;
    });

});

