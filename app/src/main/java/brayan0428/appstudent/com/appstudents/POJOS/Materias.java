package brayan0428.appstudent.com.appstudents.POJOS;

public class Materias {
    int Id;
    String Nombre;
    String Profesor;
    String Salon;
    double Nota1;
    double Nota2;
    double Nota3;
    int Porcentaje1;
    int Porcentaje2;
    int Porcentaje3;

    public Materias(int Id,String Nombre,String Profesor, String Salon,double Nota1,double Nota2,double Nota3,int Porcentaje1,int Porcentaje2,int Porcentaje3){
        this.Id = Id;
        this.Nombre = Nombre;
        this.Profesor = Profesor;
        this.Salon = Salon;
        this.Nota1 = Nota1;
        this.Nota2 =Nota2;
        this.Nota3 = Nota3;
        this.Porcentaje1 = Porcentaje1;
        this.Porcentaje2 = Porcentaje2;
        this.Porcentaje3 = Porcentaje3;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public String getSalon() {
        return Salon;
    }

    public void setSalon(String salon) {
        Salon = salon;
    }

    public double getNota1() {
        return Nota1;
    }

    public void setNota1(double nota1) {
        Nota1 = nota1;
    }

    public double getNota2() {
        return Nota2;
    }

    public void setNota2(double nota2) {
        Nota2 = nota2;
    }

    public double getNota3() {
        return Nota3;
    }

    public void setNota3(double nota3) {
        Nota3 = nota3;
    }
    public int getPorcentaje1() {
        return Porcentaje1;
    }

    public void setPorcentaje1(int porcentaje1) {
        Porcentaje1 = porcentaje1;
    }

    public int getPorcentaje2() {
        return Porcentaje2;
    }

    public void setPorcentaje2(int porcentaje2) {
        Porcentaje2 = porcentaje2;
    }

    public int getPorcentaje3() {
        return Porcentaje3;
    }

    public void setPorcentaje3(int porcentaje3) {
        Porcentaje3 = porcentaje3;
    }

}
