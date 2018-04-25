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
            "titulo text not null," +
            "fecha text not null," +
            "hora_ini text not null," +
            "hora_fin text not null)";

    //Borrar Tablas
    public static String DROP_TABLE_TAREAS = "DROP TABLE tareas";
}
