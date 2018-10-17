package com.example.roberto.tarea_03_g03;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String nombre;
    public String apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText usuario = (EditText) findViewById(R.id.user);
                EditText clave = (EditText) findViewById(R.id.pass);
                String usuarioNombre = usuario.getText().toString();
                String claveNombre = clave.getText().toString();

                nombre = usuarioNombre;
                apellido = claveNombre;

                if((usuarioNombre.trim().equalsIgnoreCase("giovanny") && claveNombre.trim().equalsIgnoreCase("cadena"))||(usuarioNombre.trim().equalsIgnoreCase("roberto") && claveNombre.trim().equalsIgnoreCase("chavez"))) {
                    Intent intent = new Intent(v.getContext(), pantallaIngreso.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("apellido", apellido);
                    startActivityForResult(intent, 0);
                    finish();
                }
                else{
                    Intent intent = new Intent(v.getContext(), loginError.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
            }
        });
    }
}