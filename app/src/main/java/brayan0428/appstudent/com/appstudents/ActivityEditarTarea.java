package brayan0428.appstudent.com.appstudents;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        nombreTarea.setText(datos.getString("nombreTarea"));
        fechaTarea.setText(datos.getString("fechaTarea"));
        horaIni.setText(datos.getString("horaIni"));
        horaFin.setText(datos.getString("horaFin"));
        dateFormatDay = new SimpleDateFormat("dd");
        dateFormatMonth = new SimpleDateFormat("MM");
        dateFormatYear = new SimpleDateFormat("yyyy");
        fecha= new Date(fechaTarea.getText().toString());
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
    }

    public void ocultarTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(nombreTarea.getWindowToken(), 0);
    }
}
