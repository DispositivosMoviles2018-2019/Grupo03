package uce.edu.ec.vista;

import android.content.Intent;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import uce.edu.ec.controlador.ImplementacionReservaCRUD;
import uce.edu.ec.controlador.ImplementacionUsuarioCRUD;
import uce.edu.ec.controlador.ImplementacionVehiculoCRUD;
import uce.edu.ec.modelo.Reserva;
import uce.edu.ec.modelo.Usuario;
import uce.edu.ec.modelo.Vehiculo;
import uce.edu.ec.final_2h_g03.R;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText clave;
    public List<Usuario> usuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList();


        usuario = (EditText) findViewById(R.id.txt_usuario_login);
        clave = (EditText) findViewById(R.id.txt_clave_login);

    }

    public void ingresar(View view) {

        ImplementacionUsuarioCRUD crudUsuario = new ImplementacionUsuarioCRUD(this);
        String usuario = this.usuario.getText().toString();
        String clave = this.clave.getText().toString();

        Intent siguiente;
        if (crudUsuario.buscarPorParametro(usuario) != null) {
            if(((Usuario)crudUsuario.buscarPorParametro(usuario)).getClave().equals(clave)) {
                siguiente = new Intent(this, MenuOpcionesActivity.class);
                startActivity(siguiente);
                Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this, "Clave incorrecta", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
        }

    }

    public void registro(View view) {
        Intent siguiente;
        siguiente = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(siguiente);
        finish();
    }

    /*public void getData() {
        String urlService = "https://servicio-autos.cfapps.io/vehiculo/list/";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        Gson gson = new Gson();

        try {
            url = new URL(urlService);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            String json = "";

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            json = response.toString();

            Type collectionType = new TypeToken<ArrayList<Vehiculo>>() {
            }.getType();
            vehiculos.addAll((List<Vehiculo>) gson.fromJson(json, collectionType));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


//Leer el archivo con xml
    /*public List<Object> leerArchivoVehiculo(String carpeta, String nombre) {

        XStream xs = new XStream(new DomDriver());
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
            objects.addAll((List<Vehiculo>) xs.fromXML(sb.toString()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return objects;
    }*/

   /* public List<Object> leerArchivoUsuario(String carpeta, String nombre) {
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
    }*/

    /*public static void cargar() {
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
    }*/




}
