/**
 * Propósito Establecer el valor del action del formulario con la opción seleccionada
 * @returns
 */
function send(){
    var route = $('#option').val();
    $('#formMain').attr('action', route);
    $('#formMain').submit();
}