/*
 * Funcion para mostrar barra de progreso.
 */
function mostrarBarraProgreso() {
    var dialog = bootbox.dialog({
        message: '<div class="ui-dialog-content ui-widget-content" style="text-align: center">' +
            '<div class="progress-container"><div class="progress" style="height: 10px"><div class="progress-bar">' +
            '<div class="progress-shadow"></div></div></div></div><br/>' +
            '<label class="ui-widget ui-state-default ui-corner-all">Cargando Su Peticion</label></div>',
        closeButton: false
    }).css({
        'margin-top': function () {
            var w = $(window).height();
            var b = $(".modal-dialog").height();
            // should not be (w-h)/2
            var h = (w) / 2;
            return h + "px";
        }
    });
}

/*
 * Funcion para cerrar barra de progreso.
 */
function CerrarBarraProgreso() {
    bootbox.hideAll();
}

/*
 * Definicion de formato para fecha
 */
Date.prototype.yyyymmdd = function () {
    var mm = this.getMonth() + 1; // getMonth() is zero-based
    var dd = this.getDate();

    return [this.getFullYear(),
        (mm > 9 ? '' : '0') + mm,
        (dd > 9 ? '' : '0') + dd
    ].join('/');
};

/*
 * Funcion para darle estilo a una tabla.
 */
function estiloTabla(tabla) {
    tabla.dataTable({
        "searching": false,
        "bDestroy": true,
        bProcessing: true,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "language": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning&uacute;n dato disponible en esta tabla",
            "sInfo": "",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "&Uacute;ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }

        }
    });
}


/*
 * Funcion que se ejecuta al seleccionar un registro de la tabla.
 */
function seleccionarRegistro(elemento, nombreTabla) {
    if (!elemento.hasClass('Encabezado')) {
        if (elemento.hasClass('selected')) {
            elemento.removeClass('selected');
        }
        else {
            $('#' + nombreTabla + ' tbody tr.selected').removeClass('selected');
            elemento.addClass('selected');
        }
    }
}

/*
 * Funcion para eventos sobre odd y even de las tablas
 */
function oddEvenTabla() {
    $(".even").hover(
        function () {
            $(this).addClass("hoverRow");
            $(this).css('cursor', 'pointer');
        }, function () {
            $(this).removeClass("hoverRow");
        }
    );
    $(".odd").hover(
        function () {
            $(this).addClass("hoverRow");
            $(this).css('cursor', 'pointer');
        }, function () {
            $(this).removeClass("hoverRow");
        }
    );
}


/**
 * Nombre showBootboxDialog
 * Propósito Mostrar un diálogo de bootbox con los parametros que se reciben
 * Inputs
 * @param title
 * @param message
 * @param buttons
 * @returns
 */
function showBootboxDialog(title, message, buttons) {
    var dialog = bootbox.dialog({
        title: title,
        message: message,
        buttons: buttons
    });
    dialog.show();
}

/**
 * Nombre setSelected
 * Propósito Seleccionar un elemento de la tabla y poner en selected el checkbox de la fila
 * Inputs
 * @param elemento
 * @returns
 */
function setSelected(elemento) {
    if (!elemento.hasClass('Encabezado')) {
        if (elemento.hasClass('selected')) {
            elemento.removeClass('selected');
            var input = elemento.find("td > input");
            input.prop('checked', false);
        }
        else {
            elemento.addClass('selected');
            var input = elemento.find("td > input");
            input.prop('checked', true);
        }
    }
}

/**
 * Nombre fillTable
 * Propósito Llenar la tabla con la respuesta que se recibe de la función ajax
 * Inputs
 * @param table
 * @returns
 */
function fillTable(table) {
    $("#resultTable").html(table);
    var columnLenght = $("#tblBusqueda")[0].rows[0].cells.length;
    columns = [];
    for (var i = 0; i < columnLenght; i++) {
        columns.push(i);
    }
    $("#sinSolicitudes").hide();
    $("#DivTablaBuscador").show();
    var tablaReportes = $("#tblBusqueda");
    var canvas = $("#canvas_logo")[0];
    if (canvas) {
        var img = new Image();
        img.src = "img/logo_top.png";
        $(img).on('load', function () {
            var context = canvas.getContext('2d');
            context.drawImage(img, 0, 0, 232, 69, 0, 0, 232, 69);
            estiloTablaExport(tablaReportes, canvas.toDataURL());
            oddEvenTabla();
            tablaReportes.on('click', 'tr', function () {
                seleccionarRegistro($(this), "tblBusqueda");
            });
        });
    }
}

function fillNormaltable(table) {
    $("#resultTable").html(table);
    $("#sinSolicitudes").hide();
    $("#DivTablaBuscador").show();
    var tablaReportes = $("#tblBusqueda");
    estiloTabla(tablaReportes);
    oddEvenTabla();
    tablaReportes.on('click', 'tr', function () {
        setSelected($(this));
    });
}

/**
 * Nombre numberErrors
 * Propósito Busca un numberFormatException y lo valida
 * @param error Es el error que se va a tratar
 * @param showResult Es el id del campo en el que se mostrara el resultado
 * @returns
 */
function numberErrors(error, showResult) {
    var result = error.search("java.lang.NumberFormatException");
    if (result > 0) {
        $("#" + showResult).text("Número no Valido");
    } else {
        $("#" + showResult).text(error);
    }
}

/**
 * Nombre printContenedorCamposID
 * Propósito imprimir un elemento de la pantalla por su id
 * Inputs
 * @param id
 * @returns
 */
function printContenedorCamposId(id) {
    var printContents = document.getElementById(id);
    if (printContents !== null && printContents !== undefined) {
        var href = document.styleSheets[0].href;
        href = href.substring(0, href.lastIndexOf("/") + 1)
            + "impresion.css";
        var toPrint = document.getElementById(id);

        var popupWin = window.open();
        popupWin.document.open();
        popupWin.document.write(toPrint.innerHTML);
        popupWin.document.close();
        popupWin.print();
    }
}


//Solo numeros y letras
$(function () {
    $(".noAlfanumericos").keypress(function (event) {
        var key = event.charCode;
        if (key != 0) {
            var caracter = String.fromCharCode(event.charCode);
            var expresion = new RegExp("^[A-Za-z0-9]");
            var result = expresion.exec(caracter);
            if (result == null) {
                return false;
            }
        }

    });
});
