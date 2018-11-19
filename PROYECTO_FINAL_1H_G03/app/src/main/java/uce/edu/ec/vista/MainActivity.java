package uce.edu.ec.vista;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import uce.edu.ec.modelo.Usuario;
import uce.edu.ec.modelo.Vehiculo;
import uce.edu.ec.proyecto_final_1h_g03.R;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;
    public static List<Usuario> usuarios;
    public static List<Vehiculo> vehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList();
        vehiculos = new ArrayList();

        usuario = (EditText) findViewById(R.id.txt_usuario_login);
        clave = (EditText) findViewById(R.id.txt_clave_login);
    }

    public void ingresar(View view) {
        for (Object o : leerArchivoUsuario("/archivos/", "miarchivo")) {
            Usuario u = (Usuario) o;
            usuarios.add(u);
        }

        for (Object o : leerArchivoVehiculo("/archivos/", "miarchivo1")) {
            Vehiculo v = (Vehiculo) o;
            vehiculos.add(v);
        }
        Collections.sort(vehiculos);
        if (vehiculos.isEmpty()) {
            cargar();
        }

        String usuario = this.usuario.getText().toString();
        String clave = this.clave.getText().toString();

        Intent siguiente;
        for (Usuario u : usuarios) {
            if (usuario.trim().equalsIgnoreCase(u.getUsuario()) && clave.trim().equalsIgnoreCase(u.getClave())) {
                siguiente = new Intent(this, ListaActivity.class);
                startActivity(siguiente);
                finish();
                //Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show();
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

    public List<Object> leerArchivoVehiculo(String carpeta, String nombre) {

        /*XStream xs = new XStream(new DomDriver());
        final String[] PATRONES = new String[]{"dd-MMM-yyyy",
                "dd-MMM-yy",
                "yyyy-MMM-dd",
                "yyyy-MM-dd",
                "yyyy-dd-MM",
                "yyyy/MM/dd",
                "yyyy.MM.dd",
                "MM-dd-yy",
                "dd-MM-yyyy"};
        DateConverter dateConverter = new DateConverter("dd/MM/yyyy", PATRONES);//pone todas las fecha que encuentre en un solo formato

        xs.registerConverter(dateConverter);
        xs.alias("vehiculos", List.class);//pone autos enbes de list en el xml
        xs.alias("vehiculo", Vehiculo.class);//pone sola auto enbes de pones todo el paquete
*/
        Gson gson = new Gson();
        List<Object> objects = new ArrayList();
        Object object;

        File localFile = new File((Environment.getExternalStorageDirectory() + carpeta));
        if (!localFile.exists()) {
            localFile.mkdir();
        }

        File file = new File(localFile, nombre + ".txt");
        StringBuilder sb = new StringBuilder();
        try {
            String texto = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((texto = br.readLine()) != null) {
                sb.append(texto);
            }
            Type collectionType = new TypeToken<ArrayList<Vehiculo>>() {
            }.getType();
            //objects.addAll((List<Vehiculo>) xs.fromXML(sb.toString()));
            objects.addAll((List<Vehiculo>) gson.fromJson(sb.toString(), collectionType));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return objects;
    }

    public List<Object> leerArchivoUsuario(String carpeta, String nombre) {
        List<Object> objects = new ArrayList();
        Object object;

        File localFile = new File((Environment.getExternalStorageDirectory() + carpeta));
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        File file = new File(localFile, nombre + ".bin");

        try {
            FileInputStream fis;
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                object = ois.readObject();
                objects.add(object);
            }
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static void cargar() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca("Audi");
        vehiculo.setPlaca("XTR-9784");
        vehiculo.setColor("Negro");
        vehiculo.setCosto(79990.0);
        vehiculo.setMatriculado(true);
        vehiculo.setFechaFabricacion(new GregorianCalendar(2015, 11, 13).getTime());
        vehiculos.add(vehiculo);
        vehiculo = new Vehiculo();
        vehiculo.setMarca("Honda");
        vehiculo.setPlaca("CCD-0789");
        vehiculo.setColor("Blanco");
        vehiculo.setCosto(15340.0);
        vehiculo.setMatriculado(false);
        vehiculo.setFechaFabricacion(new GregorianCalendar(1998, 03, 05).getTime());
        vehiculos.add(vehiculo);
    }

    public static void persistir() throws IOException {
        File file;
        File localFile = new File(Environment.getExternalStorageDirectory() + "/archivos/");
        file = new File(localFile, "miarchivo1.txt");
        file.delete();
        /*XStream xs = new XStream(new DomDriver());
        final String[] PATRONES = new String[]{"dd-MMM-yyyy",
                "dd-MMM-yy",
                "yyyy-MMM-dd",
                "yyyy-MM-dd",
                "yyyy-dd-MM",
                "yyyy/MM/dd",
                "yyyy.MM.dd",
                "MM-dd-yy",
                "dd-MM-yyyy"};
        DateConverter dateConverter = new DateConverter("dd/MM/yyyy", PATRONES);//pone todas las fecha que encuentre en un solo formato

        xs.registerConverter(dateConverter);
        xs.alias("vehiculos", List.class);//pone autos enbes de list en el xml
        xs.alias("vehiculo", Vehiculo.class);//pone sola auto enbes de pones todo el paquete
        String xml = xs.toXML(vehiculos);
*/
        Gson gson = new Gson();
        String json = gson.toJson(vehiculos);
        FileWriter escribir = new FileWriter(file, true);
        escribir.write("");
        //escribir.write(xml);
        escribir.write(json);
        escribir.close();
    }

    public static List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static void setVehiculos(List<Vehiculo> vehiculos) {
        MainActivity.vehiculos = vehiculos;
    }
}
