package brayan0428.appstudent.com.appstudents;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import brayan0428.appstudent.com.appstudents.Database.Procesos;

public class LoginActivity extends AppCompatActivity {
    TextView registrarme;
    Button iniciar;
    TextInputEditText email,clave;
    Procesos procesos = new Procesos(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailLogin);
        clave = findViewById(R.id.claveLogin);
        iniciar = findViewById(R.id.iniciarSesion);
        registrarme = findViewById(R.id.tvRegistrarme);

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(intent);
                finish();
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = email.getText().toString().trim();
                String password = clave.getText().toString().trim();
                if(correo.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar un email");
                    return;
                }
                if(password.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una clave");
                    return;
                }
                if(!procesos.consultarUsuarios(correo,password,true)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Usuario o Clave invalidos");
                    return;
                }else{
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
