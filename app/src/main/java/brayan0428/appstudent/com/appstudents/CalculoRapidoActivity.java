package brayan0428.appstudent.com.appstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class CalculoRapidoActivity extends AppCompatActivity {
    EditText nota1,nota2;
    Spinner porcentaje1,porcentaje2;
    Button calcular;
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;
    int porcentaje_1,porcentaje_2;
    TextView resultado;
    final double notaMinima = 3.0,
                 notaMaxima = 5.0;
    int diferenciaPorcentaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_rapido);

        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        porcentaje1 = findViewById(R.id.porcentajeNota1);
        porcentaje2 = findViewById(R.id.porcentajeNota2);
        resultado = findViewById(R.id.resultado);
        calcular = findViewById(R.id.calcular);

        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Cálculo Rapido");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nota1.getText().toString().equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe digitar la nota 1");
                    return;
                }
                if(nota2.getText().toString().equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe digitar la nota 2");
                    return;
                }
                porcentaje_1 = Integer.parseInt(porcentaje1.getSelectedItem().toString());
                porcentaje_2 = Integer.parseInt(porcentaje2.getSelectedItem().toString());
                diferenciaPorcentaje = Integer.parseInt(porcentaje1.getSelectedItem().toString()) + Integer.parseInt(porcentaje2.getSelectedItem().toString());
                if (diferenciaPorcentaje >=  100) {
                    Utilidades.mostrarMensaje(getApplicationContext(),"La suma de porcentajes indicados es incorrecta, debe ser menor al 100%");
                    return;
                }
                retornarNotaMinima();
            }
        });

    }

    public void retornarNotaMinima(){
        double porcentaje_Nota1 = (Double.parseDouble(nota1.getText().toString()) * porcentaje_1) / 100;
        double porcentaje_Nota2 = (Double.parseDouble(nota2.getText().toString()) * porcentaje_2) / 100;
        double diferencia = notaMinima - (porcentaje_Nota1 + porcentaje_Nota2);
        double resultado = (diferencia * notaMaxima) / ((notaMaxima * (100 - (porcentaje_1 + porcentaje_2)))/100);
        String nota = "La nota minima que debes sacar es " + Utilidades.redondearDecimales(resultado,1);
        if(resultado > 5 ){
            nota += ", no la pasas ni reuniendo las esferas del dragón :(";
        }else{
            if(resultado >= 4 && resultado <= 5 ){
                nota += ", dificil, pero no imposible!";
            }else {
                if(resultado >= 3 && resultado <= 4 ){
                    nota += ", animo, si se puede!";
                }else{
                    if(resultado >= 0 && resultado <= 3 ){
                        nota += ", imposible no ganar la materia!";
                    }else{
                        if(resultado <= 0){
                            nota = "Ya ganaste la materia, eres un nerd!";
                        }
                    }
                }
            }
        }
        this.resultado.setText(nota);
    }
}
