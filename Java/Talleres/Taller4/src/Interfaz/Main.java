package Interfaz;

import Entidades.*; //Con asterisquito importo todo
import java.util.Scanner;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        CentroAdopcion huellitas = new CentroAdopcion("Huellitas");

        while(true){
            System.out.println("Escoge una opcion");
            System.out.println("\t1) Rescatar un perro");
            System.out.println("\t2) Adoptar un perro");
            System.out.println("\t3) Cambiar nombre perro");
            System.out.println("\t4) Mirar clientes");
            System.out.println("\t0) Salir");
            System.out.print("Su opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 0:
                    System.out.println("Gracias por usar nuestros serivicios");
                    break;

                case 1:{
                    System.out.print("Ingrese el nombre del perrito: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese la raza del perrito: ");
                    String raza = sc.nextLine();

                    System.out.print("Ingrese el peso del perrito: ");
                    float peso = sc.nextFloat();
                    sc.nextLine();

                    //Todo esto para la fecha q salia con un LocalDate -.-
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    GregorianCalendar fechaNacimiento = new GregorianCalendar();
                    boolean valido = false;

                    while(!valido){
                        System.out.print("Ingrese la fecha de nacimiento del perrito (dd/mm/yyyy): ");
                        String fecha = sc.nextLine();
                        
                        try {
                            fechaNacimiento.setTime(df.parse(fecha));

                            // Validar que no sea futura
                            GregorianCalendar hoy = new GregorianCalendar();
                            if (fechaNacimiento.after(hoy)) {
                                System.out.println("El perro del futuro aun no existe, ingrese la fecha nuevamente.");
                            } else {
                                valido = true; // Fecha válida
                            }
                        } catch (Exception e) {
                            System.out.println("Formato invalido, ingresa nuevamente");
                        }
                    }

                    Perro perrito = new Perro(raza, fechaNacimiento, peso, nombre);
                    huellitas.rescatarMascota(perrito);
                    System.out.println("El perrito ha sido rescatado");
                    break;
                }
                
                case 2:{
                    Persona personita = null;
                    System.out.print("Ingrese la cedula de la persona que va a adoptar: ");
                    String cedula = sc.nextLine();

                    if(huellitas.buscarCliente(cedula) == null){
                        System.out.println("La persona aun no está agregada, vamos a agregarla");

                        System.out.print("Ingrese el nombre de la persona: ");
                        String nombresito = sc.nextLine();

                        System.out.print("Ingrese la edad de la persona: ");
                        int edad = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Ingrese la direccion de la persona: ");
                        String residencia = sc.nextLine();

                        personita = new Persona(nombresito,edad,residencia,cedula);
                        huellitas.agregarCliente(personita);
                    }else{
                        System.out.println("La persona ya existe en el sistema");
                        personita = huellitas.buscarCliente(cedula);
                    }

                    huellitas.mostrarInternos();
                    
                    //Agregue un atributo bool que muestra si hay perros disponibles o no
                    if(!huellitas.disponibles())break;

                    System.out.print("Ingrese el nombre del perrito a adoptar: ");
                    String nPerrito = sc.nextLine();

                    Perro p = huellitas.buscarPerro(nPerrito);

                    while (p == null) {
                        System.out.println("No se encontró el perrito, ingrese otro nombre:");
                        nPerrito = sc.nextLine(); 
                        p = huellitas.buscarPerro(nPerrito);
                    }

                    huellitas.darAdopcion(p,personita);
                    System.out.println("El perrito ha sido adoptado");

                    break;
                }


                case 3:{
                    System.out.print("Ingrese la cedula del cliente: ");
                    String cedula = sc.nextLine();
                    Persona personita = huellitas.buscarCliente(cedula);
                    
                    if(personita == null){
                        System.out.println("Lo sentimos, la persona no existe en el sistema");
                    }else if(!personita.tienePerros()){
                        System.out.println("La persona no tiene perros");
                    }else{
                        personita.mostrarMascotas();
                        System.out.print("Ingrese el nombre de la mascota que va a cambiar nombre: ");
                        String nAntiguo = sc.nextLine();

                        System.out.print("Ingrese el nuevo nombre: ");
                        String nNuevo = sc.nextLine();

                        personita.cambiarNombreMascota(nAntiguo, nNuevo); 
                    }
                    break;
                }
                
                case 4:{
                    System.out.println("La lista de clientes es");
                    huellitas.mostrarAdopciones();
                    break;
                }

                default:{
                    System.out.println("Opcion invalida, ingrese nuevamente");
                    break;
                }
            }

            if(opcion == 0) break;

        }

    }
}
