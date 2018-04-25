package brayan0428.appstudent.com.appstudents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import brayan0428.appstudent.com.appstudents.Adapters.TareasAdapter;
import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Tareas;

public class TareasActivity extends AppCompatActivity {
    FloatingActionButton agregarTarea;
    EditText tituloTarea,fechaTarea,horaini,horafin;
    Button guardar,cancelar;
    Calendar c;
    int anio,mes,dia,hora,minuto;
    AlertDialog alert;
    Context context = TareasActivity.this;
    List<Tareas> tareasList;
    RecyclerView recyclerView;
    TareasAdapter tareasAdapter;
    Procesos procesos = new Procesos(this);
    String msn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        agregarTarea = findViewById(R.id.actionTareas);
        recyclerView = findViewById(R.id.recycleView);
        //Inicializamos el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        inicializarDatos();
        tareasAdapter = new TareasAdapter(this,tareasList);
        recyclerView.setAdapter(tareasAdapter);

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TareasActivity.this);
                LayoutInflater inflater = TareasActivity.this.getLayoutInflater();
                View v = inflater.inflate(R.layout.agregar_tarea_modal,null);
                builder.setView(v);
                builder.setTitle("Agregar Tarea");
                tituloTarea = v.findViewById(R.id.nombreTarea);
                fechaTarea = v.findViewById(R.id.fechaTarea);
                horaini = v.findViewById(R.id.horainicio);
                horafin = v.findViewById(R.id.horafin);
                guardar = v.findViewById(R.id.guardarTarea);
                cancelar = v.findViewById(R.id.cancelarTarea);

                fechaTarea.setInputType(InputType.TYPE_NULL);
                horaini.setInputType(InputType.TYPE_NULL);
                horafin.setInputType(InputType.TYPE_NULL);

                c = Calendar.getInstance();
                dia = c.get(Calendar.DAY_OF_MONTH);
                mes = c.get(Calendar.MONTH);
                anio = c.get(Calendar.YEAR);
                hora = c.get(Calendar.HOUR_OF_DAY);
                minuto = c.get(Calendar.MINUTE);

                fechaTarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(TareasActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;
                                fechaTarea.setText(day + "/" + ("0" + month).toString().substring( ("0" + month).length() -2, ("0" + month).length()) + "/" + year);
                            }
                        },anio,mes,dia);
                        datePickerDialog.show();
                    }
                });

                horaini.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(TareasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                horaini.setText(i + ":" + ("0" + i1).toString().substring( ("0" + i1).length() -2, ("0" + i1).length()));
                            }
                        },hora,minuto,false);
                        timePickerDialog.show();
                    }
                });

                horafin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(TareasActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                horafin.setText(i + ":" + ("0" + i1).toString().substring( ("0" + i1).length() -2, ("0" + i1).length()));
                            }
                        },hora,minuto,false);
                        timePickerDialog.show();
                    }
                });

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String Titulo = tituloTarea.getText().toString();
                        String Fecha = fechaTarea.getText().toString();
                        String HoraIni = horaini.getText().toString();
                        String HoraFin = horafin.getText().toString();
                        if(Titulo.equals("")){
                            Utilidades.mostrarMensaje(context,"Debe ingresar el titulo de la tarea");
                            return;
                        }
                        if(!Utilidades.validarFecha(Fecha)){
                            Utilidades.mostrarMensaje(context,"Debe ingresar una fecha valida");
                            return;
                        }
                        if(!Utilidades.validaHora(HoraIni)){
                            Utilidades.mostrarMensaje(context,"Debe ingresar una hora de inicio valida");
                            return;
                        }
                        if(!Utilidades.validaHora(HoraFin)){
                            Utilidades.mostrarMensaje(context,"Debe ingresar una hora fin valida");
                            return;
                        }
                        tareasList.add(new Tareas(tareasList.size(),Titulo,Fecha,HoraIni,HoraFin));
                        msn = procesos.insertarTarea(Titulo,Fecha,HoraIni,HoraFin);
                        if(msn.equals("")){
                            tareasAdapter.notifyDataSetChanged();
                            alert.dismiss();
                            Utilidades.mostrarMensaje(getApplicationContext(),"Guardado correctamente");
                            inicializarDatos();
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
        tareasList = new ArrayList<>();
        tareasList = procesos.consultarTareas();
    }
}
