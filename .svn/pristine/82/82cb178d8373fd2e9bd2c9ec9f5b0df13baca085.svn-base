$('.input-sm').on("input", function() {
    this.value = this.value.toUpperCase();
});

//Confirmation
$("body").delegate(".confirm", "click", function(event) {
    event.preventDefault();
    var page = $(this).attr('href');
    var formId = $(this).attr('formId');
    var msg = $(this).attr('data-msg');

    vex.defaultOptions.className = 'vex-theme-flat-attack';
    vex.dialog.confirm({
        message: msg,
        callback: function(confirmed) {
            $(".vex").hide();
            if (confirmed) {
                if (formId == undefined) {
                    if (page == undefined) {
                        $("#" + $('form').attr('id')).submit();
                    } else {
                        window.location.href = page;
                    }
                } else {
                    $("#" + formId).submit();
                }
            } else {
                return false;
            }
        },
        buttons: [
            $.extend({}, vex.dialog.buttons.YES, {
                text: 'Ok'
            }), $.extend({}, vex.dialog.buttons.NO, {
                text: 'Batal'
            })
        ]
    });
});

//BYE 27/07/2015
//Prevent Enter on textfield
$('input').on("keypress", function(e) {
    if (e.keyCode == 13 && e.target.type !== 'submit') {
        e.preventDefault();
        return $(e.target).blur().focus();
    }
});
//BYE 27/07/2015

var digitsOnly = /[1234567890]/g;
var integerOnly = /[0-9\.-]/g;
var alphaOnly = /[A-Za-z]/g;
var usernameOnly = /[0-9A-Za-z\._-]/g;

function restrictInput(myfield, e, restrictionType, checkdot) {

    $('#infoNumber').hide();
    if (!e)
        var e = window.event
    if (e.keyCode)
        code = e.keyCode;
    else if (e.which)
        code = e.which;
    var character = String.fromCharCode(code);

    // if user pressed esc... remove focus from field...
    if (code == 27) {
        this.blur();
        return false;
    }

    // ignore if the user presses other keys
    // strange because code: 39 is the down key AND ' key...
    // and DEL also equals .
    if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && code != 38 && (code != 39 || (code == 39 && character == "'")) && code != 40) {
        if (character.match(restrictionType)) {
            if (checkdot == "checkdot") {
                return !isNaN(myfield.value.toString() + character);
            } else {
                return true;
            }
        } else {
            $('#infoNumber').show();
            return false;
        }
    }
}
