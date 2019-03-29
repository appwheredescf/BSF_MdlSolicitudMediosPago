
// Funcion para pintar la ventana de mensaje emergente de alerta.
// PARAM text Variable que contiene la cadena que se mostrara en el mensaje.
function msjAlerta(text) {
    bootbox
        .alert({
            message: '<p style="overflow: hidden; float: left; margin-left: 5%;" class="">'
                + '<img style="margin: -220px 0px -240px 0px;" src="img/messages-g.png" /></p>'
                + '<center><br/>'
                + '<label class="mayus">' + text + '</label><br><br></center>',
            className: 'bootboxSinBorde',
            callback: function () {

            }
        });
}

// Funcion para pintar la ventana de mensaje emergente de anuncio.
// PARAM text Variable que contiene la cadena que se mostrara en el mensaje.
function msjAnuncio(text) {
    bootbox
        .alert({
            message: '<p style="overflow: hidden; float: left; margin-left: 5%;" class="">'
                + '<img style="margin: -290px 0px -170px 0px;" src="img/messages-g.png" /></p>'
                + '<center><br/>'
                + '<label class="mayus">' + text + '</label><br><br></center>',
            className: 'bootboxSinBorde',
            callback: function () {

            }
        });
}

// Funcion para pintar la ventana de mensaje emergente de error.
// PARAM text Variable que contiene la cadena que se mostrara en el mensaje.
function msjError(text) {
    bootbox
        .alert({
            message: '<p style="overflow: hidden; float: left; margin-left: 5%;" class="">'
                + '<img style="margin: -380px 0px -90px 0px" src="img/messages-g.png" /></p>'
                + '<center><br/>'
                + '<label class="mayus">' + text + '</label><br><br></center>',
            className: 'bootboxSinBorde',
            callback: function () {

            }
        });
}

// Funcion para pintar la ventana de mensaje emergente de exito.
// PARAM text Variable que contiene la cadena que se mostrara en el mensaje.
function msjExito(text) {
    bootbox
        .alert({
            message: '<p style="overflow: hidden; float: left; margin-left: 5%;" class="">'
                + '<img style="margin: 0px 0px -450px 0px" src="img/messages-g.png" /></p>'
                + '<center><br/>'
                + '<label class="mayus">' + text + '</label><br><br></center>',
            className: 'bootboxSinBorde',
            callback: function () {

            }
        });
}


