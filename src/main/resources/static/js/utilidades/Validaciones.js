$(document).ready(function () {

        $('.Fecha').datepicker.dates['es'] = {
            days: ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
            daysShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"],
            daysMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"],
            months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
            today: "Hoy",
            clear: "Borrar",
            weekStart: 1,
            format: "dd/mm/yyyy"
        };

        $('.Fecha').datepicker({
            autoclose: true,
            endDate: '0d',
            language: "es",
            disable: "false"
        });

        $(".RFCFisica").click(function () {
            ElementoMod = this.id;
        })
        $(".CURPFisica").click(function () {
            ElementoMod = this.id;
        })

        $(".RFCFisica").inputmask(
            'Regex',
            {
                regex: "^[a-zA-Z]{4}[0-9]{6}([0-9a-zA-Z]{3})?$",
                "onincomplete": function () {
                    Mensaje('<p style="overflow: hidden; float: left;" class="">' + '</p>'
                        + '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#161;Atenci&oacute;n&#33; </p>' + '<center><label>Formato del RFC no valido' + '</label></center>',
                        "warning", function () {
                            setTimeout(function () {
                                $("#" + ElementoMod).focus();
                            }, 100);

                        })
                }
            });

        $(".CURPFisica").inputmask(
            'Regex',
            {
                regex: "^[a-zA-Z][a,A,e,E,i,I,o,O,u,U,x,X][a-zA-Z]{2}[0-9]{2}[0-1][0-9][0-3][0-9][m,M,h,H][a-zA-Z]{2}[b,B,c,C,d,D,f,F,g,G,h,H,j,J,k,K,l,L,m,M,n,N,p,P,q,Q,r,R,s,S,t,T,v,V,w,W,x,X,y,Y,z,Z]{3}[0-9,a-zA-Z][0-9]$",
                "onincomplete": function () {
                    Mensaje('<p style="overflow: hidden; float: left;" class="">' + '</p>'
                        + '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#161;Atenci&oacute;n&#33; </p>' + '<center><label>Formato del CURP no valido' + '</label></center>',
                        "warning", function () {
                            setTimeout(function () {
                                $("#" + ElementoMod).focus();
                            }, 100);

                        })
                }
            });


        $(".Numeros").inputmask('Regex',
            {
                regex: "^[0-9]{15}"
            });

        $(".CP").inputmask('Regex',
            {
                regex: "^([1-9]{2}|[0-9][1-9]|[1-9][0-9])[0-9]{3}$"
            });


        $(".Money").inputmask('decimal', {
            'integerDigits': 13,
            'alias': 'decimal',
            'min': '0.00',
            'max': '9999999999999.99',
            'groupSeparator': ',',
            'autoGroup': true,
            'digits': 2,
            'placeholder': '0.00',
            'autoUnmask': true,
            'clearMaskOnLostFocus': !1,
            'prefix': '$ '
        });

        // acciones para los botones
        $("#btnCancelar").click(function () {
            window.history.go(-1);
        });

        $("#breadcum").click(function () {
            window.history.go(-1);
        });

    }
);

// Se validan todos los campor requeridos
function ValidarCampos() {
    var Valido = "true";
    $(".Requeridos").each(function (index) {

        //Forma de validar un radio button este seleccionado
        if ($(this).children().attr("type") == "radio") {
            if (!$('input[name="' + $(this).children().attr("name") + '"]').is(':checked')) {
                $(this).addClass('has-error')
                Valido = "false";
            }
            else
                $(this).removeClass('has-error')
        }
        else {

            //Valida Los input que estan a simple vista
            if ($(this).find("input").length > 0 && $(this).find("select").length == 0) {
                if ($(this).find("input").val() == "") {
                    $(this).addClass('has-error')
                    Valido = "false";
                }
                else
                    $(this).removeClass('has-error')
            }
            else if ($(this).find("select").length > 0) {

                if ($(this).find("select").val() == "Null" || $(this).find("select").val() == null || $(this).find("select").val() == "0") {
                    $(this).addClass('has-error')
                    Valido = "false";
                }
                else
                    $(this).removeClass('has-error')
            }
        }
    })
    return Valido;
};
