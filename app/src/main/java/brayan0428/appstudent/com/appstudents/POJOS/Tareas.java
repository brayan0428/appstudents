package brayan0428.appstudent.com.appstudents.POJOS;

/**
 * Created by bllanos on 25/04/2018.
 */

public class Tareas {
    int Id;
    String Titulo;
    String Fecha;
    String HoraIni;
    String HoraFin;

    public Tareas(int Id,String Titulo,String Fecha,String HoraIni,String HoraFin){
        this.Id = Id;
        this.Titulo  = Titulo;
        this.Fecha = Fecha;
        this.HoraIni = HoraIni;
        this.HoraFin = HoraFin;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHoraIni() {
        return HoraIni;
    }

    public void setHoraIni(String horaIni) {
        HoraIni = horaIni;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String horaFin) {
        HoraFin = horaFin;
    }
}
