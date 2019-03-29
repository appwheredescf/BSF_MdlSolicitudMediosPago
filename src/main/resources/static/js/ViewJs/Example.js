var form1;
$(function() {
    form1 = $("#formE").validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            age: {
                required: true,
                number: true
            }
        },
        messages: {
            name: {
                required: "",
                minlength: ""
            },
            age: {
                required: "",
                number: ""
            }
        },
        highlight: function(element) {
            $(element).parent().addClass('has-error');
        },
        unhighlight: function(element){
            $(element).parent().removeClass('has-error');
        },
        submitHandler: function(form1){
            enviar();
        }
    });
});

function enviar() {
    $.ajax({
        url : "example/validar",
        type : 'POST',
        data : {
            name : $("#name").val(),
            age : $("#age").val()
        },
        error : function(xhr){
            console.log(xhr.responseText);
        },
        success : function(data){
            console.log(data);
            if (data === ""){
                bootbox.alert("Ocurrio un error inesperado, intente mas tarde");
            }
            if (data.hasOwnProperty("errores")) {
                if (data.errores.hasOwnProperty("age")) {
                    bootbox.alert(data.errores.age);
                    $("#age").parent().addClass("has-error");
                }
                if (data.errores.hasOwnProperty("name"))
                    bootbox.alert(data.errores.name);
                if (data.errores.hasOwnProperty("AgeError"))
                    bootbox.alert(data.errores.AgeError);
            } else {
                bootbox.alert(data.response);
            }
        }
    });
}