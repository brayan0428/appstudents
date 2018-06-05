package brayan0428.appstudent.com.appstudents.Database;

/**
 * Created by bllanos on 25/04/2018.
 */

public class Constantes {
    //Nombre de la Base de Datos
    public static String BD_NAME = "StudentsApp";

    //Creación de Tablas
    public static  String TABLE_TAREAS =
            "CREATE TABLE tareas (" +
            "id integer primary key autoincrement," +
            "idusuario integer," +
            "titulo text not null," +
            "fecha text not null," +
            "hora_ini text not null," +
            "hora_fin text not null)";

    public static String TABLE_MATERIAS =
            "CREATE TABLE materias (" +
            "id integer primary key autoincrement," +
            "idusuario integer," +
            "nombre text not null," +
            "profesor text," +
            "salon text," +
            "nota1 real," +
            "nota2 real," +
            "nota3 real," +
            "porcentaje1 integer," +
            "porcentaje2 integer," +
            "porcentaje3 integer)";

    public static String TABLE_USUARIOS =
            "CREATE TABLE usuarios (" +
            "id integer primary key autoincrement," +
            "nombre text not null," +
            "email text not null," +
            "clave text not null," +
            "habilitado integer)";

    //Borrar Tablas
    public static String DROP_TABLE_TAREAS = "DROP TABLE tareas";
    public static String DROP_TABLE_MATERIAS = "DROP TABLE materias";
    public static String DROP_TABLE_USUARIOS = "DROP TABLE usuarios";
}
