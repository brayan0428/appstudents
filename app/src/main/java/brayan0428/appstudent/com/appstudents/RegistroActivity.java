package brayan0428.appstudent.com.appstudents;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import brayan0428.appstudent.com.appstudents.Database.Procesos;

public class RegistroActivity extends AppCompatActivity {
    Button cancelar,registrarme;
    TextInputEditText nombre,email,clave,confirmarclave;
    Procesos procesos = new Procesos(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombreRegistro);
        email = findViewById(R.id.emailRegistro);
        clave = findViewById(R.id.claveRegistro);
        confirmarclave = findViewById(R.id.confirmarClave);
        cancelar = findViewById(R.id.btnCancelar);
        registrarme = findViewById(R.id.btnRegistrarme);

        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nombre.getText().toString().trim();
                String correo = email.getText().toString().trim();
                String password = clave.getText().toString().trim();
                String confirmarpassword = confirmarclave.getText().toString().trim();
                if(name.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar un nombre");
                    return;
                }
                if(correo.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar un email");
                    return;
                }
                if(password.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una clave");
                    return;
                }
                if(confirmarpassword.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Debe ingresar una confirmación de clave");
                    return;
                }
                if(!password.equals(confirmarpassword)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Las claves no coinciden");
                    return;
                }
                if(password.length() < 5){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Las clave debe tener minimo 5 caracteres");
                    return;
                }
                if(procesos.consultarUsuarios(correo,password,false)){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Ya existe un usuario creado con ese correo");
                    return;
                }
                String msn = "";
                msn = procesos.insertarUsuario(name,correo,password);
                if(!msn.equals("")){
                    Utilidades.mostrarMensaje(getApplicationContext(),"Se produjo un error: " + msn);
                    return;
                }
                Utilidades.mostrarMensaje(getApplicationContext(),"Usuario creado exitosamente");
                cancelar.performClick();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
