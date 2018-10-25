package com.optativa.tarea_04_g03;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.optativa.pojos.Usuario;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class RegistroActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText usuario;
    private EditText clave;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText celular;
    private Button botonImagen;
    private ImageView imagen;
    private RadioButton masculino;
    private RadioButton femenino;
    private Spinner dia;
    private Spinner mes;
    private Spinner ano;
    private CheckBox algebra;
    private CheckBox analisis;
    private CheckBox lenguaje;
    private CheckBox fisica;
    private CheckBox matematicas;
    private Switch becado;
    Bitmap imageBitmap;


    private Usuario usuarioEntrada ;
    private byte[] foto;


    private String archivo = "miarchivo";
    private String carpeta = "/archivos/";
    File file;
    String file_path = "";
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuarioEntrada= new Usuario();

        usuario = (EditText) findViewById(R.id.txt_usuario);
        clave = (EditText) findViewById(R.id.txt_clave);
        nombre = (EditText) findViewById(R.id.txt_nombre);
        apellido = (EditText) findViewById(R.id.txt_apellido);
        email = (EditText) findViewById(R.id.txt_email);
        celular = (EditText) findViewById(R.id.txt_celular);
        masculino = (RadioButton) findViewById(R.id.rb_masculino);
        femenino = (RadioButton) findViewById(R.id.rb_femenino);
        botonImagen = (Button) findViewById(R.id.btn_foto);
        imagen = (ImageView) findViewById(R.id.imag_view_foto);
        algebra = (CheckBox) findViewById(R.id.checkBoxAlgebra);
        analisis = (CheckBox) findViewById(R.id.checkBoxAnalisis);
        lenguaje = (CheckBox) findViewById(R.id.checkBoxLenguaje);
        fisica = (CheckBox) findViewById(R.id.checkBoxFisica);
        matematicas = (CheckBox) findViewById(R.id.checkBoxMatematicas);
        dia = (Spinner) findViewById(R.id.spinner_dia);
        mes = (Spinner) findViewById(R.id.spinner_mes);
        ano = (Spinner) findViewById(R.id.spinner_ano);
        becado = (Switch) findViewById(R.id.switchBeca);
        String[] opcionesDia = new String[31];
        String[] opcionesMes = new String[12];
        String[] opcionesAno = new String[69];
        for (int i = 1; i <= 31; i++) {
            opcionesDia[i - 1] = String.valueOf(i);
        }
        for (int i = 1; i <= 12; i++) {
            opcionesMes[i - 1] = String.valueOf(i);
        }
        for (int i = 1950; i <= 2018; i++) {
            opcionesAno[i - 1950] = String.valueOf(i);
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesDia);
        dia.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesMes);
        mes.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcionesAno);
        ano.setAdapter(adapter);


        this.file_path = (Environment.getExternalStorageDirectory() + this.carpeta);
        System.out.println(file_path);
        File localFile = new File(this.file_path);
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        this.name = (this.archivo + ".bin");
        this.file = new File(localFile, this.name);
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }

           /* FileInputStream fis = null;
            fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                usuarioEntrada = (Usuario) ois.readObject();
            }
            ois.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(usuarioEntrada.getApellido());
        foto = usuarioEntrada.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        imagen.setImageBitmap(bitmap);*/
    }

    public void tomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }

    public void guardar(View view) throws IOException {
        String usuario = this.usuario.getText().toString();
        String clave = this.clave.getText().toString();
        String nombre = this.nombre.getText().toString();
        String apellido = this.apellido.getText().toString();
        String email = this.email.getText().toString();
        String celular = this.celular.getText().toString();
        String fecha = dia.getSelectedItem().toString() + "/" + mes.getSelectedItem().toString() + "/" + ano.getSelectedItem().toString();

        Boolean becado;
        if (this.becado.isChecked() == true) {
            becado = true;
        } else {
            becado = false;
        }

        String genero;
        if (masculino.isChecked() == true) {
            genero = masculino.getText().toString();
        } else if (femenino.isChecked() == true) {
            genero = femenino.getText().toString();
        } else {
            genero = "Desconocido";
        }

        List<String> asignaturas = new ArrayList();
        if (algebra.isChecked() == true) {
            asignaturas.add(algebra.getText().toString());
        } else if (analisis.isChecked() == true) {
            asignaturas.add(analisis.getText().toString());
        } else if (lenguaje.isChecked() == true) {
            asignaturas.add(lenguaje.getText().toString());
        } else if (fisica.isChecked() == true) {
            asignaturas.add(fisica.getText().toString());
        } else if (matematicas.isChecked() == true) {
            asignaturas.add(matematicas.getText().toString());
        }

        Usuario usuarioObjeto = new Usuario();
        usuarioObjeto.setUsuario(usuario);
        usuarioObjeto.setClave(clave);
        usuarioObjeto.setNombre(nombre);
        usuarioObjeto.setApellido(apellido);
        usuarioObjeto.setEmail(email);
        usuarioObjeto.setCelular(celular);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        imageBitmap.recycle();
        usuarioObjeto.setFoto(byteArray);
        usuarioObjeto.setGenero(genero);
        usuarioObjeto.setFecha(fecha);
        usuarioObjeto.setAsignaturas(asignaturas);
        usuarioObjeto.setBecado(becado);

        OutputStream os = new FileOutputStream(this.file);//guardar los datos en el archivo

        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(usuarioObjeto);
        oos.close();

        Intent siguiente;
        siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }
}
