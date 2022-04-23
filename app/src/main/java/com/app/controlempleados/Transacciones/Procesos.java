package com.app.controlempleados.Transacciones;

public class Procesos {
    public static final String DATABASE = "dbEmpleados";
    public static final String TABLA_EMPLEADOS = "tbEmpleados";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDOS = "apellidos";
    public static final String DIRECCION = "direccion";
    public static final String EDAD = "edad";
    public static final String PUESTO = "puesto";


    public static final String CREATE_TABLE_EMPLEADOS = "CREATE TABLE "+TABLA_EMPLEADOS+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                                                         "nombre TEXT, apellidos TEXT, edad INTEGER, direccion TEXT, puesto TEXT)";

    public static final String DROP_TABLE_EMPLEADOS = "DROP TABLE IF EXISTS " + TABLA_EMPLEADOS;

    public static final String DELETE_EMPLEADO = "DELETE FROM "+ TABLA_EMPLEADOS +" WHERE id = ";

    public static final String SELECT_EMPLEADO = "SELECT * FROM "+TABLA_EMPLEADOS +" WHERE id = ";

    public static final String SELECT_TABLE_EMPLEADOS = "SELECT * FROM "+TABLA_EMPLEADOS;
}
