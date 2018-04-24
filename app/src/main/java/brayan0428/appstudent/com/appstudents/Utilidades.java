package brayan0428.appstudent.com.appstudents;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by bllanos on 24/04/2018.
 */

public class Utilidades {

    public static void mostrarMensaje(Context c, String mensaje){
        Toast.makeText(c,mensaje,Toast.LENGTH_LONG).show();
    }

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean validaHora(String hora){
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        formatoHora.setLenient(false);
        try{
            formatoHora.parse(hora);
        }catch(ParseException e){
            return false;
        }
        return true;
    }
}
