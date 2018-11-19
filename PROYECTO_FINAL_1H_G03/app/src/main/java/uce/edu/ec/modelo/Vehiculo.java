package uce.edu.ec.modelo;

import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable, Comparable<Vehiculo> {
    private String placa;
    private String marca;
    private Date fechaFabricacion;
    private Double costo;
    private Boolean matriculado;
    private String color;

    public Vehiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int compareTo(Vehiculo v) {//aqui ingresa la persona que se compara
        int resultado = placa.compareTo(v.getPlaca());//extraemos la comparacion de los nombres
      /*  if (resultado == 0) {//si los nombres son iguales
            resultado = salario.compareTo(o.getSalario());//extraemos la comparacion de los salarios
            if (resultado == 0) {//si los salarios son iguales
                resultado = cedula.compareTo(o.getCedula());//extraemos la comparacion de la cedula
            }
        }*/
        return resultado;//* ((Persona.getAscendente())?1:-1);//tomo el valor que obtengo del metodo estatico para multiplicar por 1 o -1
    }
}
