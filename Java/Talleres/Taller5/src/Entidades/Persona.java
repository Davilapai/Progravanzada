package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Persona implements Serializable{
    private String nombre;
    private int edad;
    private String residencia;
    private String cedula;
    private ArrayList<Perro> perritos;//Recordar lo del uml, una persona varios perros un perro 1 persona :D
    

    //Getters
    public String getNombre() {return nombre;}
    public int getEdad() {return edad;}
    public String getResidencia() {return residencia;}
    public String getCedula() {return cedula;}

    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setEdad(int edad) {this.edad = edad;}
    public void setResidencia(String residencia) {this.residencia = residencia;}
    public void setCedula(String cedula) throws Exception {
        if(cedula.length() != 10) throw new Exception("La cedula debe de ser de 10 caracteres");
        this.cedula = cedula;
    }

    //Constructor
    public Persona(String nombre, int edad, String residencia, String cedula) {
        this.nombre = nombre;
        this.edad = edad;
        this.residencia = residencia;
        this.cedula = cedula;
        this.perritos = new ArrayList<>(); //Inicializamos aqui pa q puedan haber personas sin lista, sin necesidad de andar haciendo cositas raras
    }

    //Metodos
    public void adoptarMascota(Perro perrete){
        perritos.add(perrete);
        perrete.setDuenio(this); //Esto hace referencia al objeto actual, entonces esta persona va a ser el duenio del perrete

        //Ahora vamos a guardar la fechita
        GregorianCalendar fechaActual = new GregorianCalendar();
        perrete.setFechaAdopcion(fechaActual);
    }

    private Perro buscarMascota(String nombresito){
        for(Perro perro:perritos){
            if(perro.getNombre().equals(nombresito)){
                return perro;
            }
        }
        System.out.println("No hay perrito con ese nombre");
        return null;
    }

    public void cambiarNombreMascota(String nAntiguo, String nNuevo){
        Perro perrito = buscarMascota(nAntiguo);
        if(perrito != null){
            perrito.setNombre(nNuevo);
        }
    }

    public void mostrarMascotas(){
        System.out.println("La persona tiene " + perritos.size() + " perrito(s)");
        for(Perro perrito : perritos){
            System.out.println("-----------");
            System.out.println("Nombre del perrito: " + perrito.getNombre());
            System.out.println("Edad del perrito: " + perrito.calcularEdad() + " anios");
            System.out.println("Raza del perrito: " + perrito.getRaza());
            System.out.println("Peso del perrito: " + perrito.getPeso());

            //Ahora toda la vaina para fecha de adopcion
            GregorianCalendar fechaAdopcion = perrito.getFechaAdopcion();
            int dia = fechaAdopcion.get(Calendar.DAY_OF_MONTH);
            int mes = fechaAdopcion.get(Calendar.MONTH) + 1; // +1 pq enero es 0
            int anio = fechaAdopcion.get(Calendar.YEAR);
            System.out.println("Fecha de adopción: " + dia + "/" + mes + "/" + anio);
            System.out.println("-----------");
        }
    }

}