package brayan0428.appstudent.com.appstudents.POJOS;

public class Grafica {
    int id;
    String nombre;
    double promedio;

    public Grafica(int id,String nombre,double promedio){
        this.id = id;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
