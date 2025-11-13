import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CentroAdopcion implements Serializable{
    private static final long serialVersionUID = 1L;
    Scanner sc = new Scanner(System.in);

    private String nombre;
    private int ganancias;
    private ArrayList<Persona> clientes;
    private ArrayList<Mascota> internos;
    private ArrayList<Mascota> guarderia;

    //Getters 
    public String getNombre() {return nombre;}
    public ArrayList<Persona> getClientes(){return clientes;}
    public ArrayList<Mascota> getInternos(){return internos;}
    public ArrayList<Mascota> getGuarderia(){return guarderia;}
    public int getGanancias(){return ganancias;}

    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setClientes(ArrayList<Persona> clientes){this.clientes = clientes;}
    public void setInternos(ArrayList<Mascota> internos){this.internos = internos;}
    public void setGuarderia(ArrayList<Mascota> guarderia){this.guarderia = guarderia;}

    //Constructor
    public CentroAdopcion(String nombre){
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.internos = new ArrayList<>();
        this.guarderia = new ArrayList<>();
        this.ganancias = 0;
    }

    //Metodos
    public void rescatarMascota(Mascota nuevo){
        internos.add(nuevo);
    }

    public void mostrarInternos(){
        System.out.println("Las mascotas disponibles son las siguientes");

        for(Mascota m : internos){
            double edad = m.calcularEdad();
            
            //Solo muestra las mascotas mayores a 1 año
            if(edad>=1){
                //Dependiento si es gato o perro muestra al inicio que especie es
                if(m instanceof Perro){
                    System.out.println("Perrito");
                }else{
                    System.out.println("Gatito");
                }
                System.out.println("Nombre: " + m.getNombre());
                System.out.println("Edad: " + edad + " años");
                System.out.println("Raza: " + m.getRaza());
                System.out.println("Peso: " + m.getPeso());
                System.out.println("-----------");
            }
        }
    }

    //Al adoptar es importante removerlo de la lista del centro 
    public void darAdopcion(Mascota m, Persona duenio){
        duenio.adoptarMascota(m);
        internos.remove(m);
        System.out.println(duenio.getNombre() + " ha adoptado a " + m.getNombre());
    }

    public void agregarCliente(Persona persona){
        //Le saca la cedula a la persona y busca el cliente, si ya existe no lo agrega 
        if(buscarCliente(persona.getCedula())==null){
            System.out.println("Cliente agregado");
            clientes.add(persona);
            return;
        }
        System.out.println("Esa persona ya está registrada");
    }

    public Persona buscarCliente(String cedula){
        //Usa la lista de personas del centro y busca la cedula, si no hay retorna nul
        for(Persona personita : clientes){
            if (personita.getCedula().equals(cedula)){
                return personita;
            }
        }
        return null;
    }

    public Mascota buscarMascota(String nombre, ArrayList<Mascota> lista){
        //Si la lista esta vacia no puede buscar mascota y retorna null.
        if(lista.size() == 0){
            return null;
        }

        for(Mascota m : lista){
            if(m.getNombre().equals(nombre)){
                return m;
            }
        }
        return null;
    }

    public void mostrarAdopciones(){
        if(clientes.size()==0){
            System.out.println("Aun no han ocurrido adopciones");
            return;
        }
        
        for(Persona p:clientes){
            System.out.println("################");
            System.out.println(p.getNombre());
            p.mostrarMascotas();
            System.out.println("################");
        }
    }
    
    public void dejarMascota(Persona cliente){
        if(cliente.getMascotas().size() == 0){
            System.out.println("La persona no tiene mascotas para dejar");
            return;
        }

        cliente.mostrarMascotas();
        System.out.print("Ingrese el nombre de la mascota que quiera dejar a nuestro cuidado: ");
        String nombre = sc.nextLine();

        Mascota buscada = buscarMascota(nombre, cliente.getMascotas());

        while(buscada == null){
            System.out.print("Nombre incorrecto, ingreselo nuevamente: ");
            nombre = sc.nextLine();
            buscada = buscarMascota(nombre, cliente.getMascotas());
        }

        cliente.getMascotas().remove(buscada);
        guarderia.add(buscada);

        System.out.println("La mascota " + nombre + " ha ingresado en la guarderia.");

    }

    public void recogerMascota(String nombre, Persona persona){
        Mascota buscada = buscarMascota(nombre, guarderia);

        if(buscada == null){
            System.out.println("Esa mascota no está en la guarderia");
            return;
        }

        if(buscada.getDuenio() == persona){
            System.out.println("La mascota " + nombre + " ha regresado con su dueño");
            persona.getMascotas().add(buscada);
            guarderia.remove(buscada);
        }else{
            System.out.println("La persona no es dueña de esa mascota");
        }
    }

    public void interactuar(Mascota mascota){
        System.out.println("Elige lo que quieres hacer con la mascota: ");
        int opcion;
        
        if(mascota instanceof Perro){
            System.out.println("1. Tirar la pelota");
            System.out.println("2. Salir al parque");
            System.out.println("3. Jugar con otros perros");
            System.out.println("4. Bañarlo");
            System.out.print("Su opcion: ");
            
            opcion = Utils.retornaIntRango(1, 4);
            
            if(opcion == 4){
                ((Perro)mascota).baniar();
            }else{
                ((Perro)mascota).jugar(opcion);
            }
        }else{
            System.out.println("1. Afilar uñas del gato");
            System.out.println("2. Acariciar al gato");
            System.out.print("Su opcion: ");
            
            opcion = Utils.retornaIntRango(1, 2);
            
            ((Gato)mascota).jugar(opcion);
        }

        ganancias += 10;
        System.out.println("El acumulado de ganancias es de " + ganancias + "$");

    }

    //Metodo auxiliares
    public void mostrarGuarderia(){
        if(guarderia.isEmpty()){
            System.out.println("No hay mascotas en la guardería");
            return;
        }
        
        System.out.println("Las mascotas disponibles son las siguientes");
        
        for(Mascota m : guarderia){
            if(m instanceof Perro){
                System.out.println("Perrito");
            }else{
                System.out.println("Gatito");
            }
            System.out.println("Nombre: " + m.getNombre());
            System.out.println("-----------");
        }
    }

    public int cantidadInternosDisponibles(){
        int cantidad = 0;
        for(Mascota m : internos){
            if(m.calcularEdad()>1){
                cantidad++;
            }
        }
        return cantidad;
    }
}