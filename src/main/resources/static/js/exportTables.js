/**
 * Nombre estiloTablaExport
 * Propósito Integrar lo botones para exportar las tablas a excel y pdf para imprimir
 * Inputs
 * @param tabla
 * @param image
 * @returns
 */
function estiloTablaExport(tabla, image) {
    tabla.dataTable({
    	"searching": false,
    	"bDestroy": true,
    	bProcessing: true,
    	"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
    	dom: 'lfrtipB',
    	buttons: [
    		{
    			extend : 'excelHtml5',
    			text : 'Exportar a excel',
    			className : 'btn btn-primary',
    			title : getTitleE(),
                exportOptions: {
                    columns: columns
                },
    			messageTop : 'Reporte de movimientos'
    		},
    		{
				extend: 'pdfHtml5',
				pageSize: "A4", //LEGAL, ....
				orientation: "portrait", //landscape
				text : 'Imprimir',
				className : 'btn btn-default',
				download: 'open',
				exportOptions: {
                    columns: columns
                },
	             customize: function (doc) {
	            	 doc.content.splice(0,1);
	            	 doc.pageMargins = [30,80,30,60];
                     doc.defaultStyle.fontSize = 8;
                     doc.styles.tableHeader.fontSize = 9;
	            	 var now = new Date();
					 var jsDate = now.getDate()+'-'+(now.getMonth()+1)+'-'+now.getFullYear();
					 doc['header']=(function() {
						return{
							columns : [
								{
									image : image,
									width : 100,
									height : 40,
									margin : [30, 20]
								},
								{
									alignment: 'left',
									bold: true,
									text: getTitleP(),
									fontSize: 18,
									margin: [40, 34]
								}
							]
						}
					});
					doc['footer']=(function(page, pages) {
						return {
							columns : [
								{
									alignment: 'left',
									margin : [30, 30],
									text: ['Documento generado el: ', { text: jsDate.toString() }]
								},
								{
									alignment: 'right',
									margin : [30, 30],
									text: ['Página ', { text: page.toString() },	' de ',	{ text: pages.toString() }]
								}
							]
						}
					});
	             }
    		}
    	],
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
    	},
    	initComplete : function(){
    		$('.buttons-pdf').html('<span class="glyphicon glyphicon-print"></span> Imprimir');
    	}
    });
}