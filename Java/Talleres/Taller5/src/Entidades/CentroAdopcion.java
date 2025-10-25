package Entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class CentroAdopcion implements Serializable{
    private String nombre;
    private ArrayList<Persona> personas;
    private ArrayList<Perro> perros;

    //Getters 
    public String getNombre() {return nombre;}

    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}

    //Constructor
    public CentroAdopcion(String nombre){
        this.nombre = nombre;
        this.personas = new ArrayList<>();
        this.perros = new ArrayList<>();
    }

    //Metodos
    public void rescatarMascota(Perro perrito){
        perros.add(perrito);
    }

    public void mostrarInternos(){
        for(Perro perrito : perros){
            System.out.println("Las mascotas disponibles son las siguientes");
            double edad = perrito.calcularEdad();
            if(edad>=1){
                System.out.println("-----------");
                System.out.println("Nombre del perrito: " + perrito.getNombre());
                System.out.println("Edad del perrito: " + edad + " anios");
                System.out.println("Raza del perrito: " + perrito.getRaza());
                System.out.println("Peso del perrito: " + perrito.getPeso());
                System.out.println("-----------");
            }
        }
    }

    public void darAdopcion(Perro perrito, Persona duenio){
        duenio.adoptarMascota(perrito);
        perros.remove(perrito);
    }

    public Persona buscarCliente(String cedula){
        for(Persona personita : personas){
            if (personita.getCedula().equals(cedula)){
                return personita;
            }
        }
        return null;
    }

    public void agregarCliente(Persona persona){
        if(buscarCliente(persona.getCedula())==null){
            System.out.println("Cliente agregado");
            personas.add(persona);
            return;
        }
        System.out.println("Esa persona ya está registrada");
    }

    public Perro buscarPerro(String nombre){
        for(Perro p : perros){
            if(p.getNombre().equals(nombre)){
                return p;
            }
        }
        return null;
    }

    public void mostrarAdopciones(){
        if(personas.size()==0){
            System.out.println("Aun no han ocurrido adopciones");
            return;
        }
        
        for(Persona p:personas){
            System.out.println("################");
            System.out.println(p.getNombre());
            p.mostrarMascotas();
            System.out.println("################");
        }
    } 
}