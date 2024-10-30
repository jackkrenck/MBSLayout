function checking()
{
    $('#info').hide();
    if ($("#chkPassword").val() !== '') {
        if ($("#userPassword").val() !== $("#chkPassword").val()) {

            $('#info').html('<p class="text-info"><strong>Pengesahan Kata Laluan Tidak Sama Dengan Kata Laluan</strong></p>');
            $('#info').show();
            
            $("#userPassword").val("");
            $("#chkPassword").val("");
             $("#userPassword").focus();
        }
    }
}