package uce.edu.ec.servicios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import uce.edu.ec.modelo.Vehiculo;

public interface VehiculoService {
    String API_ROUTE = "/post";

    @GET(API_ROUTE)
    Call<List<Vehiculo>> getPost();
}
