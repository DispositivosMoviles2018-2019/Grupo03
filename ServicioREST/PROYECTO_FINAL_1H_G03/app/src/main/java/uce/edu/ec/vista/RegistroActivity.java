package uce.edu.ec.vista;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uce.edu.ec.modelo.Usuario;
import uce.edu.ec.proyecto_final_1h_g03.R;

public class RegistroActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;

    private String archivo = "miarchivo";
    private String carpeta = "/archivos/";

    String file_path = "";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = (EditText) findViewById(R.id.txt_usuario_registro);
        clave = (EditText) findViewById(R.id.txt_clave);
    }

    public void guardar(View view) throws IOException {
        File file;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioAux;
        this.file_path = (Environment.getExternalStorageDirectory() + this.carpeta);
        File localFile = new File(this.file_path);

        if (!localFile.exists()) {
            localFile.mkdir();
        }

        this.name = (this.archivo + ".bin");
        file = new File(localFile, this.name);

        if (file.exists()) {
            try {
                FileInputStream fis;
                fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    usuarioAux = (Usuario) ois.readObject();
                    usuarios.add(usuarioAux);
                }
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (this.usuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Usuario vacio", Toast.LENGTH_SHORT).show();
        } else {
            if (this.clave.getText().toString().isEmpty()) {
                Toast.makeText(this, "Campo Clave vacio", Toast.LENGTH_SHORT).show();
            } else {
                String usuario = this.usuario.getText().toString();
                String clave = this.clave.getText().toString();
                usuarioAux = new Usuario(usuario, clave);
                usuarios.add(usuarioAux);

                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                for (Usuario u : usuarios) {
                    oos.writeObject(u);
                }
                oos.close();
                os.close();
                Intent siguiente;
                siguiente = new
                        Intent(this, MainActivity.class);
                startActivity(siguiente);
                finish();
            }
        }
    }
}
