package brayan0428.appstudent.com.appstudents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

import brayan0428.appstudent.com.appstudents.Database.Procesos;
import brayan0428.appstudent.com.appstudents.POJOS.Grafica;

public class GraficasActivity extends AppCompatActivity {
    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> barEntryLabels;
    BarDataSet barDataSet;
    BarData barData;
    Procesos procesos = new Procesos(this);
    android.support.v7.widget.Toolbar toolbar;
    TextView nombreVista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        barChart = findViewById(R.id.barChart);
        toolbar = findViewById(R.id.toolbar);
        nombreVista = toolbar.findViewById(R.id.nombreVista);
        nombreVista.setText("Grafica");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        barEntries = new ArrayList<>();
        barEntryLabels = new ArrayList<>();
        agregarValores();
        barDataSet = new BarDataSet(barEntries, "Proyectos");
        barData = new BarData(barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.animateY(3000);
    }

    public void agregarValores() {
        ArrayList<Grafica> datos;
        datos = procesos.datosGraficas();
        int c = 1;
        for (int i = 0; i < datos.size(); i++) {
            barEntries.add(new BarEntry(c, Float.parseFloat(String.valueOf(datos.get(i).getPromedio()))));
            c++;
        }
    }
}