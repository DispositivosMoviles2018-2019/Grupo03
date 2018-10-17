package com.example.roberto.tarea_03_g03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class pantallaIngreso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ingreso);

        /*String nombreHered = getIntent().getExtras().getString("nombre");
        String apellidoHered = getIntent().getExtras().getString("apellido");

        String nombreMostrar;

        if((nombreHered.trim().equalsIgnoreCase("giovanny") && apellidoHered.trim().equalsIgnoreCase("cadena"))){
            nombreMostrar = "CADENA FLORES RODRIGO GIOVANNY";
            TextView label = (TextView)findViewById(R.id.label);
            label.setText(nombreMostrar);
            ImageView img= (ImageView) findViewById(R.id.foto);
            img.setImageResource(R.drawable.giovanny1);
        }
        else if((nombreHered.trim().equalsIgnoreCase("roberto") && apellidoHered.trim().equalsIgnoreCase("chavez"))){
            nombreMostrar = "CHAVEZ ESTRELLA MARIO ROBERTO";
            TextView label = (TextView)findViewById(R.id.label);
            label.setText(nombreMostrar);
            ImageView img= (ImageView) findViewById(R.id.foto);
            img.setImageResource(R.drawable.roberto1);
        }*/


        Button btn2 = (Button) findViewById(R.id.backlogin);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent2, 0);
                finish();
            }
        });

        Button btn3 = (Button) findViewById(R.id.exitapp);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}