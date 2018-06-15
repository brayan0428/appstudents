package brayan0428.appstudent.com.appstudents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import brayan0428.appstudent.com.appstudents.Adapters.TareasAdapter;
import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Tareas;

public class TareasActivity extends AppCompatActivity {
    FloatingActionButton agregarTarea;
    EditText tituloTarea,fechaTarea,horaini,horafin;
    EditText fechaIni,fechaFin;
    Button guardar,cancelar,filtrarTarea;
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;
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
        fechaIni = findViewById(R.id.fechaIni);
        fechaFin = findViewById(R.id.fechaFin);
        filtrarTarea = findViewById(R.id.filtrarTarea);
        //Inicializamos el RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        inicializarDatos("","");
        tareasAdapter = new TareasAdapter(this,tareasList);
        recyclerView.setAdapter(tareasAdapter);
        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Tareas");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalTarea();
            }
        });

        c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);

        fechaIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado_();
                DatePickerDialog datePickerDialog = new DatePickerDialog(TareasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        fechaIni.setText(day + "/" + ("0" + month).toString().substring( ("0" + month).length() -2, ("0" + month).length()) + "/" + year);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado_();
                DatePickerDialog datePickerDialog = new DatePickerDialog(TareasActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        fechaFin.setText(day + "/" + ("0" + month).toString().substring( ("0" + month).length() -2, ("0" + month).length()) + "/" + year);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        filtrarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FechaIni = fechaIni.getText().toString();
                String FechaFin = fechaFin.getText().toString();
                if(!Utilidades.validarFecha(FechaIni)){
                    Utilidades.mostrarMensaje(context,"Debe ingresar una fecha inicial valida");
                    return;
                }
                if(!Utilidades.validarFecha(FechaFin)){
                    Utilidades.mostrarMensaje(context,"Debe ingresar una fecha fin valida");
                    return;
                }
                inicializarDatos(FechaIni,FechaFin);
                tareasAdapter = new TareasAdapter(getApplicationContext(),tareasList);
                recyclerView.setAdapter(tareasAdapter);
                //tareasAdapter.notifyDataSetChanged();
            }
        });

    }

    public void inicializarDatos(String fechaIni,String fechaFin){
        tareasList = new ArrayList<>();
        tareasList = procesos.consultarTareas(fechaIni,fechaFin);
    }

    public void ocultarTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(tituloTarea.getWindowToken(), 0);
    }


    public void ocultarTeclado_(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(fechaIni.getWindowToken(), 0);
    }
    public void ModalTarea(){
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
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        fechaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado();
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
                ocultarTeclado();
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
                ocultarTeclado();
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
                ocultarTeclado();
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
                    alert.dismiss();
                    Utilidades.mostrarMensaje(getApplicationContext(),"Guardado correctamente");
                    inicializarDatos("","");
                    tareasAdapter.notifyDataSetChanged();
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
}
