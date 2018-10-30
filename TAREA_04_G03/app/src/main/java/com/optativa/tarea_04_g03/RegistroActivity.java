package com.optativa.tarea_04_g03;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    private byte[] foto;

    private String archivo = "miarchivo";
    private String carpeta = "/archivos/";

    String file_path = "";
    String name = "";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        usuario = (EditText) findViewById(R.id.txt_usuario_registro);
        clave = (EditText) findViewById(R.id.txt_clave);
        nombre = (EditText) findViewById(R.id.txt_nombre_registro);
        apellido = (EditText) findViewById(R.id.txt_apellido);
        email = (EditText) findViewById(R.id.txt_email_registro);
        celular = (EditText) findViewById(R.id.txt_celular_registro);
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

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.regresar:
                Intent siguiente;
                siguiente = new Intent(this, MainActivity.class);
                startActivity(siguiente);
                finish();
                break;
            case R.id.salir:
                System.exit(0);
                finish();
                break;
        }
        return true;
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
        /*try {
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
        /*} catch (IOException e) {
            e.printStackTrace();
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(usuarioEntrada.getApellido());
        foto = usuarioEntrada.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        imagen.setImageBitmap(bitmap);*/
        if (this.usuario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Usuario vacio", Toast.LENGTH_SHORT).show();
        } else {
            if (this.clave.getText().toString().isEmpty()) {
                Toast.makeText(this, "Campo Clave vacio", Toast.LENGTH_SHORT).show();
            } else {
                if (this.nombre.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Campo Nombre vacio", Toast.LENGTH_SHORT).show();
                } else {
                    if (this.apellido.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Campo Apellido vacio", Toast.LENGTH_SHORT).show();
                    } else {
                        if (this.email.getText().toString().isEmpty()) {
                            Toast.makeText(this, "Campo E-mail vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                                    "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
                            Pattern pattern = Pattern.compile(emailPattern);

                            Matcher matcher = pattern.matcher(this.email.getText().toString());
                            if (matcher.matches()) {
                                if (this.celular.getText().toString().isEmpty()) {
                                    Toast.makeText(this, "Campo Celular vacio", Toast.LENGTH_SHORT).show();
                                } else {
                                    Pattern pattern1 = Pattern.compile("^09[0-9]{8}$");
                                    Matcher matcher1 = pattern1.matcher(this.celular.getText().toString());
                                    if (matcher1.matches()) {
                                        if (this.ano.getSelectedItem().toString().equals("1950")) {
                                            Toast.makeText(this, "Debe elegir una fecha", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (imagen.getDrawable() == null) {
                                                Toast.makeText(this, "Debe insertar una imagen", Toast.LENGTH_SHORT).show();
                                            } else {
                                                String usuario = this.usuario.getText().toString();
                                                String clave = this.clave.getText().toString();
                                                String nombre = this.nombre.getText().toString();
                                                String apellido = this.apellido.getText().toString();
                                                String email = this.email.getText().toString();
                                                String celular = this.celular.getText().toString();

                                                Calendar fechaAux = Calendar.getInstance();
                                                fechaAux.set(Integer.parseInt(ano.getSelectedItem().

                                                        toString()), Integer.parseInt(mes.getSelectedItem().

                                                        toString()), Integer.parseInt(dia.getSelectedItem().

                                                        toString()));
                                                Date fecha = new Date();
                                                fecha.setTime(fechaAux.getTimeInMillis());

                                                Boolean becado;
                                                if (this.becado.isChecked() == true)

                                                {
                                                    becado = true;
                                                } else

                                                {
                                                    becado = false;
                                                }

                                                Integer genero;
                                                if (masculino.isChecked() == true)

                                                {
                                                    genero = 0;
                                                } else if (femenino.isChecked() == true)

                                                {
                                                    genero = 1;
                                                } else

                                                {
                                                    genero = 2;
                                                }

                                                List<String> asignaturas = new ArrayList();
                                                if (algebra.isChecked() == true)

                                                {
                                                    asignaturas.add(algebra.getText().toString());
                                                }
                                                if (analisis.isChecked() == true)

                                                {
                                                    asignaturas.add(analisis.getText().toString());
                                                }
                                                if (lenguaje.isChecked() == true)

                                                {
                                                    asignaturas.add(lenguaje.getText().toString());
                                                }
                                                if (fisica.isChecked() == true)

                                                {
                                                    asignaturas.add(fisica.getText().toString());
                                                }
                                                if (matematicas.isChecked() == true)

                                                {
                                                    asignaturas.add(matematicas.getText().toString());
                                                }

                                                Usuario usuarioNuevo = new Usuario();

                                                usuarioNuevo.setUsuario(usuario);
                                                usuarioNuevo.setClave(clave);
                                                usuarioNuevo.setNombre(nombre);
                                                usuarioNuevo.setApellido(apellido);
                                                usuarioNuevo.setEmail(email);
                                                usuarioNuevo.setCelular(celular);

                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                if (imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream) == true)

                                                {
                                                    byte[] byteArray = stream.toByteArray();
                                                    imageBitmap.recycle();
                                                    usuarioNuevo.setFoto(byteArray);
                                                }

                                                usuarioNuevo.setGenero(genero);
                                                usuarioNuevo.setFecha(fecha);
                                                usuarioNuevo.setAsignaturas(asignaturas);
                                                usuarioNuevo.setBecado(becado);
                                                usuarios.add(usuarioNuevo);

                                                OutputStream os = new FileOutputStream(file);

                                                ObjectOutputStream oos = new ObjectOutputStream(os);
                                                for (
                                                        Usuario u : usuarios) {
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
                                    } else {
                                        Toast.makeText(this, "El numero celular no tiene el formato deseado, debe tener 10 numeros y iniciar con 09", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(this, "E-mail no cumple los estandares de un correo", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
    }
}
