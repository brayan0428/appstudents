package brayan0428.appstudent.com.appstudents;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import brayan0428.appstudent.com.appstudents.Database.Procesos;

public class EditarMateriaActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;
    EditText nombre,profesor,salon;
    TextInputEditText nota1,nota2,nota3;
    Spinner porcentaje1,porcentaje2,porcentaje3;
    Button guardar,cancelar;
    int idMateria = 0;
    String msn;
    Procesos procesos = new Procesos(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_materia);
        nombre = findViewById(R.id.mNombreMateria);
        profesor = findViewById(R.id.mProfesor);
        salon = findViewById(R.id.mSalon);
        nota1 = findViewById(R.id.mNota1);
        nota2 = findViewById(R.id.mNota2);
        nota3 = findViewById(R.id.mNota3);
        porcentaje1 = findViewById(R.id.mPorcentajeNota1);
        porcentaje2 = findViewById(R.id.mPorcentajeNota2);
        porcentaje3 = findViewById(R.id.mPorcentajeNota3);
        guardar = findViewById(R.id.guardarMateria);
        cancelar = findViewById(R.id.cancelarMateria);
        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Editar Materia");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Bundle datos = this.getIntent().getExtras();
        idMateria = datos.getInt("idMateria");
        nombre.setText(datos.getString("nombreMateria"));
        profesor.setText(datos.getString("profesor"));
        salon.setText(datos.getString("salon"));
        nota1.setText(datos.getDouble("nota1") + "");
        nota2.setText(datos.getDouble("nota2") + "");
        nota3.setText(datos.getDouble("nota3") + "");
        porcentaje1.setSelection(((ArrayAdapter) porcentaje1.getAdapter()).getPosition(String.valueOf(datos.getInt("porcentaje1"))));
        porcentaje2.setSelection(((ArrayAdapter) porcentaje2.getAdapter()).getPosition(String.valueOf(datos.getInt("porcentaje2"))));
        porcentaje3.setSelection(((ArrayAdapter) porcentaje3.getAdapter()).getPosition(String.valueOf(datos.getInt("porcentaje3"))));

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreMateria = nombre.getText().toString().trim();
                String profesorMateria = profesor.getText().toString().trim();
                String salonMateria = salon.getText().toString().trim();
                float nota1Materia = getFloat(nota1.getText().toString().trim());
                float nota2Materia = getFloat(nota2.getText().toString().trim());
                float nota3Materia = getFloat(nota3.getText().toString().trim());
                int porcentaje1Materia = getInt(porcentaje1.getSelectedItem().toString().trim());
                int porcentaje2Materia = getInt(porcentaje2.getSelectedItem().toString().trim());
                int porcentaje3Materia = getInt(porcentaje3.getSelectedItem().toString().trim());
                msn = procesos.actualizarMateria(idMateria,nombreMateria,profesorMateria,salonMateria,nota1Materia,nota2Materia,nota3Materia,porcentaje1Materia,porcentaje2Materia,porcentaje3Materia);
                if(msn.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Materia actualizada exitosamente");
                    redireccionar();
                }else{
                    Utilidades.mostrarMensaje(getApplicationContext(),msn);
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public float getFloat(String valor){
        if(valor.equals("")){
            return 0;
        }
        return Float.parseFloat(valor);
    }

    public int getInt(String valor){
        if(valor.equals("")){
            return 0;
        }
        return Integer.parseInt(valor);
    }

    private void redireccionar(){
        Intent intent = new Intent(this,MateriasActivity.class);
        startActivity(intent);
        finish();
    }
}
