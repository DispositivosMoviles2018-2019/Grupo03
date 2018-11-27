package uce.edu.ec.vista;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uce.edu.ec.modelo.Vehiculo;
import uce.edu.ec.proyecto_final_1h_g03.R;

public class RegistroVehiculoActivity extends AppCompatActivity {

    private EditText placa;
    private EditText marca;
    private EditText costo;
    private Switch matriculado;
    private EditText color;
    public static Boolean modificacion = false;
    private Date fechaAux = new Date();

    private TextView fecha;
    private DatePickerDialog.OnDateSetListener fechaElegir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);

        placa = (EditText) findViewById(R.id.txt_placa_vehiculo);
        marca = (EditText) findViewById(R.id.txt_marca_vehiculo);
        costo = (EditText) findViewById(R.id.txt_costo_vehiculo);
        color = (EditText) findViewById(R.id.txt_color_vehiculo);

        fecha = (TextView) findViewById(R.id.txt_fecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar fechaHoy = Calendar.getInstance();
                int year = fechaHoy.get(Calendar.YEAR);
                int month = fechaHoy.get(Calendar.MONTH);
                int day = fechaHoy.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegistroVehiculoActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, fechaElegir, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        fechaElegir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                fechaAux.setTime(calendar.getTimeInMillis());

                fecha.setText("Fecha de Fabricacion: " + date);
            }
        };

        matriculado = (Switch) findViewById(R.id.switch_matriculado);

        if (modificacion == true) {
            String placa = getIntent().getStringExtra("valorPlaca");
            System.out.println(placa);
            llenarParaModificar(placa);
        }
    }

    public void llenarParaModificar(String placa) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        List<Vehiculo> vehiculos = MainActivity.vehiculos;
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) {
                this.placa.setFocusable(false);
                this.placa.setText(v.getPlaca());
                this.marca.setText(v.getMarca());
                this.fechaAux = v.getFechaFabricacion();
                this.fecha.setText("Fecha de fabricacion: " + sdf.format(this.fechaAux).toString());
                this.costo.setText(v.getCosto().toString());
                if (v.getMatriculado() == true) {
                    this.matriculado.setChecked(true);
                } else {
                    this.matriculado.setChecked(false);
                }
                this.color.setText(v.getColor());
                vehiculos.remove(v);
                break;
            }
        }
        MainActivity.setVehiculos(vehiculos);
    }

    public void guardar(View view) throws IOException {
        List<Vehiculo> vehiculos;
        Vehiculo vehiculo = new Vehiculo();

        if (this.placa.getText().toString().isEmpty()) {
            Toast.makeText(this, "Campo Placa vacio", Toast.LENGTH_SHORT).show();
        } else {
            this.placa.setText(this.placa.getText().toString().toUpperCase());
            System.out.println(this.placa.getText().toString());

            Pattern pattern1 = Pattern.compile("^[A-Z]{3}-\\d{4}$");
            Matcher matcher1 = pattern1.matcher(this.placa.getText().toString());
            Boolean repetido = false;
            if (matcher1.matches()) {
                for (Vehiculo v : MainActivity.vehiculos) {
                    if (this.placa.getText().toString().equalsIgnoreCase(v.getPlaca())) {
                        repetido = true;
                    }
                }
                if (!repetido) {
                    if (this.marca.getText().toString().isEmpty()) {
                        Toast.makeText(this, "Campo Marca vacio", Toast.LENGTH_SHORT).show();
                    } else {
                        if (this.costo.getText().toString().isEmpty()) {
                            Toast.makeText(this, "Campo Costo vacio", Toast.LENGTH_SHORT).show();
                        } else {
                            if (this.color.getText().toString().isEmpty()) {
                                Toast.makeText(this, "Campo Color vacio", Toast.LENGTH_SHORT).show();
                            } else {

                                String placa = this.placa.getText().toString();
                                String marca = this.marca.getText().toString();
                                String costo = this.costo.getText().toString();
                                String color = this.color.getText().toString();

                                vehiculo.setFechaFabricacion(fechaAux);

                                Boolean matriculado;
                                if (this.matriculado.isChecked() == true) {
                                    matriculado = true;
                                } else {
                                    matriculado = false;
                                }

                                vehiculo.setPlaca(placa);
                                vehiculo.setMarca(marca);
                                vehiculo.setCosto(Double.parseDouble(costo));
                                vehiculo.setMatriculado(matriculado);
                                vehiculo.setColor(color);

                                vehiculos = MainActivity.vehiculos;
                                vehiculos.add(vehiculo);

                                MainActivity.setVehiculos(vehiculos);
                                Intent siguiente;
                                siguiente = new
                                        Intent(this, ListaActivity.class);
                                startActivity(siguiente);
                                finish();
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Vehiculo ya existente ingrese otra placa", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Formato de placa incorrecto tipo de formmato: FRT-8907", Toast.LENGTH_LONG).show();
            }
        }
    }
}
