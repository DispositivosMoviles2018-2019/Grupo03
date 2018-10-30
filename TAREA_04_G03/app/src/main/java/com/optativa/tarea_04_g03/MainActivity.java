package com.optativa.tarea_04_g03;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.optativa.pojos.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;

    public static List<Usuario> usuarios;
    private Usuario usuarioEntrada;

    private String archivo = "miarchivo";
    private String carpeta = "/archivos/";
    File file;
    String file_path = "";
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList();
        usuarioEntrada = new Usuario();

        usuario = (EditText) findViewById(R.id.txt_usuario_login);
        clave = (EditText) findViewById(R.id.txt_clave_login);


    }

    public void ingresar(View view) {

        this.file_path = (Environment.getExternalStorageDirectory() + this.carpeta);
        File localFile = new File(this.file_path);
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        this.name = (this.archivo + ".bin");
        this.file = new File(localFile, this.name);
        try {
            FileInputStream fis;
            fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                usuarioEntrada = (Usuario) ois.readObject();
                usuarios.add(usuarioEntrada);
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String usuario = this.usuario.getText().toString();
        String clave = this.clave.getText().toString();

        Intent siguiente;
        for (Usuario u : usuarios) {
            if (usuario.trim().equalsIgnoreCase(u.getUsuario()) && clave.trim().equalsIgnoreCase(u.getClave())) {
                siguiente = new Intent(this, ListaActivity.class);
                startActivity(siguiente);
                finish();
            }
        }
        //Toast.makeText(this, "Datos incorrectos o usuario invalido debe REGISTRASE", Toast.LENGTH_SHORT).show();
    }

    public void registro(View view) {
        Intent siguiente;
        siguiente = new Intent(this, RegistroActivity.class);
        startActivity(siguiente);
        finish();
    }
}
