package mx.appwhere.mediospago.front.application.constants;

import lombok.experimental.UtilityClass;

/**
 * @author Alejandro Martin
 * @version 1.0 - 2017/10/13
 */

@UtilityClass
public final class ApplicationConstants {
    
    public static final String VIEW_MESSAGE_NAME = "message";
    public static final String VIEWS_PRODUCE_HTML = "text/html; charset=UTF-8";
    public static final String FORMATO_NUMERO_MUY_LARGO = "Introduce un numero mas corto";

    //Mensajes de error de validación
    public static final String NULL_MESSAGE = "No debe estar vacio";
    public static final String LENGTH_MESSAGE = "El tamaño debe estar entre {min} y {max}";
    public static final String MIN_MESSAGE = "El valor no debe ser menor a {value}";
    public static final String MAX_MESSAGE = "El valor no debe de superar a {value}";
    public static final String DIGITS_MESSAGE = "El valor no debe tener más de {integer} digitos y {fraction} decimales";
    public static final String SELECT_OPTION_MESSAGE = "Selecciona una opción valida";
    
    public static final String ETL_ERR_NOMBRE_ARCHIVO = "Error en nombre de archivo: {0}";
    public static final String ETL_ERR_LECTURA_ARCHIVO = "Error en la lectura del archivo: {0}";
    public static final String ETL_ERR_ESCTIRURA_ARCHIVO = "Error en la escritura del archivo: {0}";
    public static final String ETL_ERR_REGISTROS_ARCHIVO = "El Archivo: {0}, debe contener unicamente: {1} registros";
    public static final String ETL_ERR_LONGITUD_LINEA = "Error en longitud de la linea: {0} de archivo: {1}";
    public static final String ETL_ERR_LINEAS_AP = "El archivo A_P, no contiene las lineas descritas en archivo C_C";
    public static final String ETL_ERR_CAMPO_VACIO = "El campo: {0}, de la linea: {1}, del archivo: {2}, no debe ser vacio";
    public static final String ETL_ERR_CARACTERES_ESP = "Se encontraron caracteres especiales en linea: {0} de archivo: {1}";
    public static final String ETL_ERR_MINUSCULAS = "Se encontraron letras minusculas en linea: {0} de archivo: {1}";
    public static final String ETL_ERR_CAMPO_NUMERICO = "El campo: {0}, de la linea: {1}, del archivo: {2}, debe contener solo valores num�ricos";
    public static final String ETL_ERR_CAMPO_FECHA = "El campo: {0}, de la linea: {1}, del archivo: {2}, debe contener formato DDMMAAAA";
    public static final String ETL_ERR_VALOR_DEFAULT = "El valor del campo: {0}, de la linea: {1}, del archivo: {2}, debe ser: {3}";
    public static final String ETL_ERR_CREAR_ARCHIVO_CUENTA = "Error al crear archivo de Salida Cuenta";
    public static final String ETL_ERR_ERROR_INESPERADO = "Ocurrio un error inesperado, consulte al equipo de soporte: {0}";
    public static final String ETL_ERR_NO_DIRECTORIO = "No existe el directorio temporal {} para ejecutar el proceso";
    public static final String ETL_ERR_NO_CONS_MEDIO = "No coincide el dato Concecutivo Medio en los archivos AP y XLSX";
    public static final String ETL_ERRO_NO_MAYOR_EDAD = "No es mayor de edad en el campo {0} del archivo Principal";
    
    public static final String TIPO_ARCHIVO_CIFRAS_CONTROL = "C_C";
    public static final String TIPO_ARCHIVO_CPRINCIPAL = "A_P";
    public static final String TIPO_ARCHIVO_ALTACUENTA = "txt";
    public static final String TIPO_ARCHIVO_TARJETAS = "xlsx";
    
    public static final String CVE_PROCESO_MEDIOS_PAGO_ENTRADA = "MED_ENTRADA";
    public static final String CVE_PROCESO_MEDIOS_PAGO_SALIDA = "MED_SALIDA";
    
    public static final int OK  = 1;
    public static final int ERR = 0;
    
    public static final String TIPO_DATO_CADENA   = "CADENA";
    public static final String TIPO_DATO_NUMERICO = "NUMERICO";
    public static final String TIPO_DATO_FECHA    = "FECHA";
    
    
    /** Definicion de indices de campos de archivos */
    public static final int[] CC_CAMPO_TOTAL_REGISTROS = new int[] {8, 13};

    public static final int CTA_NUMERO_CAMPO_SEXO_MUJER = 3;
    public static final int CTA_NUMERO_CAMPO_SEXO_HOMBRE = 4;
    public static final int CTA_NUMERO_CAMPO_FECHA_NACIMIENTO = 13;
    public static final int CTA_NUMERO_CAMPO_CODIGO_POSTAL = 14;



    public static final int CTA_NUMERO_CAMPO_TELEFONO = 27;
    public static final int CTA_NUMERO_CAMPO_CALLE = 30;
    public static final int CTA_NUMERO_CAMPO_NO_EXTERIOR = 31;
    public static final int CTA_NUMERO_CAMPO_COLONIA = 32;
    public static final int CTA_NUMERO_CAMPO_CODIGO_POSTAL_CLIENTE = 33;

    public static final int CAT_TIPO_ARCHIVO_ENTRADA = 1;
    public static final int CAT_TIPO_ARCHIVO_SALIDA = 0;

    //Variables estaticas para obtener campos del consecutivo medio de AP y XLSX

    public static final int CAM_CONSECUTIVO_MEDIO_AP = 57;
    public static final int CAM_CONSECUTIVO_MEDIO_XLSL = 1;

    //Variables estaticas para obtener la edad del AP

    public static final int CAM_EDAD_AP = 30;


    //Clave de archivos
    public  static  final  String CVE_ARCHIVO_AP = "AP";
    public static final  String CVE_ARCHIVO_XLSX = "TARJ";


    //Variable para validar Edad
    public static final int VAR_EDAD = 18;
}
