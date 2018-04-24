package brayan0428.appstudent.com.appstudents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView grabarAudio,tareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asignamos variables
        grabarAudio = findViewById(R.id.GrabarAudio);
        tareas = findViewById(R.id.cardTareas);

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
    }
}
