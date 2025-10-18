package Entidades;

import java.util.ArrayList;

public class CentroAdopcion {
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

    //Este metodo no es del taller pero me sirve para ver si hay perros o no
    public boolean disponibles(){
        int contador = 0;
        for(Perro p : perros){
            if(p.calcularEdad()<1)contador++;
        }
        if(perros.size()==0 | contador == perros.size())return false;
        return true;
    }

    public void rescatarMascota(Perro perrito){
        perros.add(perrito);
    }

    public void mostrarInternos(){
        if(!disponibles()){
            System.out.println("No hay perros disponibles para adoptar");
            return;
        }
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
        for(Perro perrito : perros){
            System.out.println("Las mascotas disponibles no disponibles son las siguientes");
            double edad = perrito.calcularEdad();
            if(edad<1){
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
        }
        
        for(Persona p:personas){
            System.out.println("################");
            System.out.println(p.getNombre());
            p.mostrarMascotas();
            System.out.println("################");
        }
    } 

    
    

}
