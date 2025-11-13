import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;
    private String residencia;
    private String cedula;
    private ArrayList<Mascota> mascotas;//Recordar lo del uml, una persona varios perros un perro 1 persona :D
    

    //Getters
    public String getNombre() {return nombre;}
    public int getEdad() {return edad;}
    public String getResidencia() {return residencia;}
    public String getCedula() {return cedula;}
    public ArrayList<Mascota> getMascotas(){return mascotas;}

    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setEdad(int edad) {this.edad = edad;}
    public void setResidencia(String residencia) {this.residencia = residencia;}
    public void setCedula(String cedula) {this.cedula = cedula;}

    //Constructor
    public Persona(String nombre, int edad, String residencia, String cedula) {
        this.nombre = nombre;
        this.edad = edad;
        this.residencia = residencia;
        this.cedula = cedula;
        this.mascotas = new ArrayList<>(); //Inicializamos aqui pa q puedan haber personas sin lista, sin necesidad de andar haciendo cositas raras
    }

    //Metodos
    public void adoptarMascota(Mascota nuevita){
        mascotas.add(nuevita);
        nuevita.setDuenio(this); //Esto hace referencia al objeto actual, entonces esta persona va a ser el duenio de la mascotita

        //Ahora vamos a guardar la fechita
        GregorianCalendar fechaActual = new GregorianCalendar();
        nuevita.setFechaAdopcion(fechaActual);
    }

    public void cambiarNombreMascota(Mascota mascota, String nNuevo){
        String nAntiguo = mascota.getNombre();
        mascota.setNombre(nNuevo);
        System.out.println("Has cambiado el nombre de " + nAntiguo + " a " + nNuevo);
    }

    public void mostrarMascotas(){
        if(mascotas.isEmpty()){
            System.out.println("La persona no tiene mascotas");
            return;
        }
        
        System.out.println("La persona tiene " + mascotas.size() + " mascota(s)");
        for(Mascota m : mascotas){
            if(m instanceof Perro){
                System.out.println("La mascota es un Perrito");
            }else{
                System.out.println("La mascota es un Gatito");
            }
            System.out.println("Nombre de la mascota: " + m.getNombre());
            System.out.println("Edad de la mascota: " + m.calcularEdad() + " años");
            System.out.println("Raza de la mascota: " + m.getRaza());
            System.out.println("Peso de la mascota: " + m.getPeso());

            //Ahora toda la vaina para fecha de adopcion
            GregorianCalendar fechaAdopcion = m.getFechaAdopcion();
            int dia = fechaAdopcion.get(Calendar.DAY_OF_MONTH);
            int mes = fechaAdopcion.get(Calendar.MONTH) + 1; // +1 pq enero es 0
            int anio = fechaAdopcion.get(Calendar.YEAR);
            System.out.println("Fecha de adopción: " + dia + "/" + mes + "/" + anio);
            System.out.println("-----------");
        }
    }

}
