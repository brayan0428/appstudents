package brayan0428.appstudent.com.appstudents;

import android.content.Context;
import android.content.SharedPreferences;
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

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }

    public static void guardarDatosSesion(Context c,int id,String nombre){
        SharedPreferences sharedPreferences = c.getSharedPreferences("DatosUsuario",c.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",id);
        editor.putString("nombre",nombre);
        editor.commit();
    }

    public static int obtenerIdSesion(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("DatosUsuario",c.MODE_PRIVATE);
        return sharedPreferences.getInt("id",0);
    }

    public static String obtenerNombreSesion(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("DatosUsuario",c.MODE_PRIVATE);
        return sharedPreferences.getString("nombre","");
    }

    public static double retornarNotaMinima(double nota1,double nota2,int porcentaje_1,int porcentaje_2) {
        double notaMinima = 3.0,
                notaMaxima = 5.0;
        double porcentaje_Nota1 = (nota1 * porcentaje_1) / 100;
        double porcentaje_Nota2 = (nota2 * porcentaje_2) / 100;
        double diferencia = notaMinima - (porcentaje_Nota1 + porcentaje_Nota2);
        double resultado = (diferencia * notaMaxima) / ((notaMaxima * (100 - (porcentaje_1 + porcentaje_2))) / 100);
        return redondearDecimales(resultado,1);
    }
}
