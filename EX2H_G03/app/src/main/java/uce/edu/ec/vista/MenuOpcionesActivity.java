package uce.edu.ec.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import uce.edu.ec.ex2h_g03.R;

public class MenuOpcionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
    }

    public void vehiculos(View view) {
        Intent siguiente;
        siguiente = new Intent(this, ListaVehiculosActivity.class);
        startActivity(siguiente);
        finish();
    }

    public void reserva(View view) {
        Intent siguiente;
        siguiente = new Intent(this, ValidacionDisponibilidadActivity.class);
        startActivity(siguiente);
        finish();
    }
}
