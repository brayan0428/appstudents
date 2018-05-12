package brayan0428.appstudent.com.appstudents;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import brayan0428.appstudent.com.appstudents.Adapters.MateriasAdapater;
import brayan0428.appstudent.com.appstudents.Adapters.TareasAdapter;
import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Materias;

public class MateriasActivity extends AppCompatActivity {
    EditText nombre,profesor,salon,nota1,nota2,nota3;
    Spinner porcentaje1,porcentaje2,porcentaje3;
    Button guardar,cancelar;
    FloatingActionButton agregarMateria;
    RecyclerView recyclerView;
    MateriasAdapater materiasAdapater;
    ArrayList<Materias> materiasList;
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;
    Procesos procesos = new Procesos(this);
    AlertDialog alert;
    String msn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        agregarMateria = findViewById(R.id.actionMaterias);
        recyclerView = findViewById(R.id.recyclerViewMaterias);
        //Inicializamos el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        inicializarDatos();
        materiasAdapater = new MateriasAdapater(this,materiasList);
        recyclerView.setAdapter(materiasAdapater);
        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Materias");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        agregarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MateriasActivity.this);
                LayoutInflater inflater = MateriasActivity.this.getLayoutInflater();
                View v = inflater.inflate(R.layout.agregar_materia_modal,null);
                builder.setView(v);
                Utilidades.mostrarMensaje(getApplication(),"");
                builder.setTitle("Agregar Materia");
                nombre = v.findViewById(R.id.mNombreMateria);
                profesor = v.findViewById(R.id.mProfesor);
                salon = v.findViewById(R.id.mSalon);
                nota1 = v.findViewById(R.id.mNota1);
                nota2 = v.findViewById(R.id.mNota2);
                nota3 = v.findViewById(R.id.mNota3);
                porcentaje1 = v.findViewById(R.id.mPorcentajeNota1);
                porcentaje2 = v.findViewById(R.id.mPorcentajeNota2);
                porcentaje3 = v.findViewById(R.id.mPorcentajeNota3);
                guardar = v.findViewById(R.id.guardarMateria);
                cancelar = v.findViewById(R.id.cancelarMateria);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nombreMateria = nombre.getText().toString().trim();
                        String profesorMateria = profesor.getText().toString().trim();
                        String salonMateria = salon.getText().toString().trim();
                        float nota1Materia = getNumber(nota1.getText().toString().trim());
                        float nota2Materia = getNumber(nota2.getText().toString().trim());
                        float nota3Materia = getNumber(nota3.getText().toString().trim());
                        int porcentaje1Materia = getNumber(porcentaje1.getSelectedItem().toString().trim());
                        int porcentaje2Materia = getNumber(porcentaje2.getSelectedItem().toString().trim());
                        int porcentaje3Materia = getNumber(porcentaje3.getSelectedItem().toString().trim());

                        msn = procesos.insertarMateria(nombreMateria,profesorMateria,salonMateria,nota1Materia,nota2Materia,nota3Materia,
                                                        porcentaje1Materia,porcentaje2Materia,porcentaje3Materia);
                        if(msn.equals("")){
                            Utilidades.mostrarMensaje(getApplicationContext(),"Guardado correctamente");
                            alert.dismiss();
                            inicializarDatos();
                            materiasAdapater.notifyDataSetChanged();
                        }else{
                            Utilidades.mostrarMensaje(getApplicationContext(),msn);
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert.dismiss();
                    }
                });

                builder.create();
                alert = builder.show();
            }
        });
    }

    public void inicializarDatos(){
        materiasList = new ArrayList<>();
        materiasList = procesos.consultarMaterias();
    }

    public int getNumber(String valor){
        if(valor.equals("")){
            return 0;
        }
        return Integer.parseInt(valor);
    }
}
