package brayan0428.appstudent.com.appstudents.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bllanos on 25/04/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constantes.BD_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.TABLE_TAREAS);
        db.execSQL(Constantes.TABLE_MATERIAS);
        db.execSQL(Constantes.TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(Constantes.DROP_TABLE_TAREAS);
        db.execSQL(Constantes.DROP_TABLE_MATERIAS);
        db.execSQL(Constantes.DROP_TABLE_USUARIOS);
        onCreate(db);
    }
}
