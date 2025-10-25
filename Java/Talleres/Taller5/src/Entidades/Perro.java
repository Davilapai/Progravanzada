package Entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Perro implements Serializable{
    private String raza;
    private GregorianCalendar fechaNacimiento;
    private float peso;
    private String nombre;
    private GregorianCalendar fechaAdopcion; //Esto tmb
    private Persona duenio; //Esto va null hasta q lo adopten

    //Getters
    public String getRaza() {return raza;}
    public GregorianCalendar getFechaNacimiento() {return fechaNacimiento;}
    public float getPeso() {return peso;}
    public String getNombre() {return nombre;}
    public GregorianCalendar getFechaAdopcion() {return fechaAdopcion;}
    public Persona getDuenio() {return duenio;}

    //Setters
    public void setRaza(String raza) {this.raza = raza;}
    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public void setPeso(float peso) {this.peso = peso;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setFechaAdopcion(GregorianCalendar fechaAdopcion) {this.fechaAdopcion = fechaAdopcion;}
    public void setDuenio(Persona duenio) {this.duenio = duenio;}
    

    //Constructor
    public Perro(String raza, GregorianCalendar fechaNacimiento, float peso, String nombre) {
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.nombre = nombre;
    }

    //Metodos
    public double calcularEdad(){
        //Para sacar la fecha de hoy
        Calendar hoy = Calendar.getInstance();

        int años = hoy.get(Calendar.YEAR)-fechaNacimiento.get(Calendar.YEAR);
        int meses = hoy.get(Calendar.MONTH)-fechaNacimiento.get(Calendar.MONTH);
        int dias = hoy.get(Calendar.DAY_OF_MONTH)-fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        // Resta las cosas directamente, pero aveces los meses dan negativo, si dan negativo significa que el cumpleaños aun no ha llegado
        // por eso lo ajustamos 
        if (meses < 0 || (meses == 0 && dias < 0)) {
            años--;
            meses += 12;
        }
        if (dias < 0) {
            dias += 30; // Aproximación
        }

        //Esto es para q todo quede en años
        double edad = años + (meses / 12.0) + (dias / 365.0);

        //Para q salga redondeado a 2 decimales. 
        edad = Math.round(edad*100.0)/100.0;

        return edad;
    }
}