package uce.edu.ec.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import uce.edu.ec.controlador.RecyclerViewAdaptador;
import uce.edu.ec.proyecto_final_1h_g03.R;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView reciclerViewVehiculo;
    private RecyclerViewAdaptador adaptadorVehiculo;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reciclerViewVehiculo = (RecyclerView) findViewById(R.id.recycler_vehiculo);
        reciclerViewVehiculo.setLayoutManager(new LinearLayoutManager(this));
        Collections.sort(MainActivity.vehiculos);
        adaptadorVehiculo = new RecyclerViewAdaptador(MainActivity.vehiculos);
        reciclerViewVehiculo.setAdapter(adaptadorVehiculo);
    }

    public void nuevo(View view) {
        RegistroVehiculoActivity.modificacion = false;
        Intent siguiente;
        siguiente = new Intent(this, RegistroVehiculoActivity.class);
        startActivity(siguiente);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.persistir:
                try {
                    MainActivity.persistir();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.salir:
                System.exit(0);
                finish();
                break;
        }
        return true;
    }
}
