package Entidades;

import java.util.ArrayList;

class CentroAdopcion {
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
            double edad = perrito.calcularEdad();
            if(edad>1){
                System.out.println("-----------");
                System.out.println("Nombre del perrito: " + perrito.getNombre());
                System.out.println("Edad del perrito: " + edad + " anios");
                System.out.println("Raza del perrito: " + perrito.getRaza());
                System.out.println("Peso del perrito: " + perrito.getPeso());
            }
        }
    }

    public void darAdopcion(Perro perrito, Persona duenio){
        duenio.adoptarMascota(perrito);
        perros.remove(perrito);
    }

    private Persona buscarCliente(String cedula){
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
        System.out.println("No hay perros con ese nombre");
        return null;
    }

    public void mostrarAdopciones(){
        for(Persona p:personas){
            System.out.println(p.getNombre());
            p.mostrarMascotas();
        }
    } 

    
    

}
