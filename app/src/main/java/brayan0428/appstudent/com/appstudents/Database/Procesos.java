package brayan0428.appstudent.com.appstudents.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import brayan0428.appstudent.com.appstudents.POJOS.Materias;
import brayan0428.appstudent.com.appstudents.POJOS.Tareas;

/**
 * Created by bllanos on 25/04/2018.
 */

public class Procesos {
    DBHelper db;
    SQLiteDatabase sql;
    public Procesos(Context context){
        this.db = new DBHelper(context,null,1);
    }

    public String insertarTarea(String titulo,String fecha,String hora_ini,String hora_fin){
        try{
            sql = db.getWritableDatabase();
            sql.execSQL("insert into tareas (titulo,fecha,hora_ini,hora_fin) values (" +
                    "'" + titulo +"','" + fecha +"','" + hora_ini +"','" + hora_fin +"')");
            return "";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public ArrayList<Tareas> consultarTareas(){
        try{
            ArrayList<Tareas> tareas = new ArrayList<>();
            sql = db.getReadableDatabase();
            Cursor c = sql.rawQuery("select id,titulo,fecha,hora_ini,hora_fin from tareas order by cast(fecha as date) asc",null);
            if (c.moveToFirst()) {
                do {
                    tareas.add(new Tareas(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
                } while(c.moveToNext());
                return tareas;
            }
            return tareas;
        }catch (Exception e){
            return null;
        }
    }

    public String eliminarTarea(int id){
        try{
            sql = db.getWritableDatabase();
            sql.execSQL("delete from tareas where id = " + id);
            return "";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public ArrayList<Materias> consultarMaterias(){
        try{
            ArrayList<Materias> materias = new ArrayList<>();
            sql = db.getReadableDatabase();
            Cursor c = sql.rawQuery("select id,nombre,profesor,salon,nota1,nota2,nota3,porcentaje1,porcentaje2,porcentaje3 from materias",null);
            if (c.moveToFirst()) {
                do {
                    materias.add(new Materias(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getDouble(4),c.getDouble(5),c.getDouble(6),c.getInt(7),c.getInt(8),c.getInt(9)));
                } while(c.moveToNext());
                return materias;
            }
            return materias;
        }catch (Exception e){
            return null;
        }
    }

    public String insertarMateria(String nombre,String profesor,String salon,float nota1,float nota2,float nota3,int porcentaje1,int porcentaje2,int porcentaje3){
        try{
            sql = db.getWritableDatabase();
            sql.execSQL("insert into materias (nombre,profesor,salon,nota1,nota2,nota3,porcentaje1,porcentaje2,porcentaje3) values (" +
                    "'" + nombre +"','" + profesor +"','" + salon +"'," + nota1 +"," + nota2 +"," + nota3 +"," + porcentaje1 +","+porcentaje2+","+porcentaje3+" )");
            return "";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String eliminarMateria(int id){
        try{
            sql = db.getWritableDatabase();
            sql.execSQL("delete from materias where id = " + id);
            return "";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
