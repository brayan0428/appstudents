package brayan0428.appstudent.com.appstudents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import brayan0428.appstudent.com.appstudents.Database.Procesos;

public class MainActivity extends AppCompatActivity {
    CardView grabarAudio,tareas,calculoRapido,materias,cerrarSesion;
    TextView countTareas;
    Procesos procesos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utilidades.mostrarMensaje(this,Utilidades.obtenerIdSesion(this) + "");
        //Asignamos variables
        grabarAudio = findViewById(R.id.GrabarAudio);
        tareas = findViewById(R.id.cardTareas);
        calculoRapido = findViewById(R.id.calculoRapido);
        materias = findViewById(R.id.cardMaterias);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        //Eventos Click
        grabarAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AudioActivity.class);
                startActivity(intent);
            }
        });

        tareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TareasActivity.class);
                startActivity(intent);
            }
        });

        calculoRapido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CalculoRapidoActivity.class);
                startActivity(intent);
            }
        });

        materias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MateriasActivity.class);
                startActivity(intent);
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Esta seguro que desea cerrar sesión?")
                            .setTitle("Confirmación")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    builder.create();
                    builder.show();
                }catch (Exception e){
                    Utilidades.mostrarMensaje(getApplicationContext(),e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        procesos = new Procesos(this);
        countTareas = findViewById(R.id.countTareas);
        countTareas.setText("Tareas (" + procesos.consultarTareas("","").size() + ")");
    }
}
