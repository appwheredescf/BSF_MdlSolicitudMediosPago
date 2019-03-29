//valores por defecto dataTable
$.extend(true, $.fn.dataTable.defaults, {
    lengthChange: false,
    ordering: false,
    info: true,
    paging: true,
    destroy: true,
    deferRender: true,
    scrollCollapse: true,
    scroller: true
    ,
    "language": {
        "info": "Mostrando: _START_ a _END_ de _TOTAL_ Registros.",
        "infoEmpty": "Mostrando: 0 a 0 de 0 Registros.",
        "lengthMenu": "Mostrar: _MENU_ Registros.",
        "loadingRecords": "Cargando...",
        "processing": "Procesando...",
        "zeroRecords": "No hubo coincidencias",
        "search": "Filtro en resultados:",
        "emptyTable": "Ning&uacute;n dato disponible en esta tabla.",
        "paginate": {
            "previous": "Anterior",
            "next": "Siguiente"
        }
    }
});
