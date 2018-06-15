package brayan0428.appstudent.com.appstudents;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Tareas;

public class ActivityEditarTarea extends AppCompatActivity {
    Button guardar,cancelar;
    EditText nombreTarea,fechaTarea,horaIni,horaFin;
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;
    int ano,dia,mes;
    DateFormat dateFormatDay;
    DateFormat dateFormatMonth;
    DateFormat dateFormatYear;
    Date fecha;

    Calendar c;
    int hora,minuto;
    String msn;
    Procesos procesos = new Procesos(this);
    int idTarea = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);

        guardar = findViewById(R.id.guardarTarea);
        cancelar = findViewById(R.id.cancelarTarea);
        nombreTarea = findViewById(R.id.nombreTarea);
        fechaTarea = findViewById(R.id.fechaTarea);
        horaIni = findViewById(R.id.horainicio);
        horaFin = findViewById(R.id.horafin);
        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Editar Tarea");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle datos = this.getIntent().getExtras();
        idTarea = datos.getInt("idTarea");
        nombreTarea.setText(datos.getString("nombreTarea"));
        fechaTarea.setText(datos.getString("fechaTarea"));
        horaIni.setText(datos.getString("horaIni"));
        horaFin.setText(datos.getString("horaFin"));
        dateFormatDay = new SimpleDateFormat("dd");
        dateFormatMonth = new SimpleDateFormat("MM");
        dateFormatYear = new SimpleDateFormat("yyyy");
        fecha= new Date(fechaTarea.getText().toString());

        c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);
        fechaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ano = Integer.parseInt(dateFormatYear.format(fecha));
                mes = Integer.parseInt(dateFormatMonth.format(fecha));
                dia = Integer.parseInt(dateFormatDay.format(fecha));
                ocultarTeclado();
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityEditarTarea.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        fechaTarea.setText(day + "/" + ("0" + month).toString().substring( ("0" + month).length() -2, ("0" + month).length()) + "/" + year);
                    }
                },ano,mes,dia);
                datePickerDialog.show();
            }
        });

        horaIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado();
                TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityEditarTarea.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        horaIni.setText(i + ":" + ("0" + i1).toString().substring( ("0" + i1).length() -2, ("0" + i1).length()));
                    }
                },hora,minuto,false);
                timePickerDialog.show();
            }
        });

        horaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado();
                TimePickerDialog timePickerDialog = new TimePickerDialog(ActivityEditarTarea.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        horaFin.setText(i + ":" + ("0" + i1).toString().substring( ("0" + i1).length() -2, ("0" + i1).length()));
                    }
                },hora,minuto,false);
                timePickerDialog.show();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ocultarTeclado();
                String Titulo = nombreTarea.getText().toString();
                String Fecha = fechaTarea.getText().toString();
                String HoraIni = horaIni.getText().toString();
                String HoraFin = horaFin.getText().toString();
                if(Titulo.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar el titulo de la tarea");
                    return;
                }
                if(!Utilidades.validarFecha(Fecha)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una fecha valida");
                    return;
                }
                if(!Utilidades.validaHora(HoraIni)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una hora de inicio valida");
                    return;
                }
                if(!Utilidades.validaHora(HoraFin)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una hora fin valida");
                    return;
                }
                msn = procesos.actualizarTarea(idTarea,Titulo,Fecha,HoraIni,HoraFin);
                if(msn.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Tarea actualizada exitosamente");
                    redireccionar();
                }else{
                    Utilidades.mostrarMensaje(getApplicationContext(),msn);
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redireccionar();
            }
        });
    }

    public void ocultarTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(nombreTarea.getWindowToken(), 0);
    }

    private void redireccionar(){
        Intent intent = new Intent(this,TareasActivity.class);
        startActivity(intent);
        finish();
    }
}
