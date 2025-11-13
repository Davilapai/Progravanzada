import java.util.Scanner;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Main{

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        CentroAdopcion huellitas;

        try{
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream("adopcion.bin"));
            huellitas = (CentroAdopcion)lectura.readObject();
            lectura.close();
            System.out.println("Centro de adopcion cargado");

        }catch(Exception e){
            Utils.escribirError(e);
            System.out.println("No hay archivos para cargar");
            huellitas = new CentroAdopcion("Huellitas");
        }

        while(true){
            System.out.println("Escoge una opcion");
            System.out.println("\t1) Rescatar una mascota");
            System.out.println("\t2) Adoptar un mascota");
            System.out.println("\t3) Cambiar nombre mascota");
            System.out.println("\t4) Dejar mascota en la guarderia");
            System.out.println("\t5) Interactuar con una mascota");
            System.out.println("\t6) Mirar clientes");
            System.out.println("\t0) Guardar y salir");
            System.out.print("Su opcion: ");
            
            int opcion = Utils.retornaNumeroSwitch();

            switch (opcion) {
                case 0:{
                    try{
                        ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("adopcion.bin"));
                        archivo.writeObject(huellitas);
                        archivo.close();
                    }catch(Exception e){
                        Utils.escribirError(e);
                        System.out.println("Error al guardar el archivo");
                    }
                    break;
                }

                case 1:{
                    System.out.print("Ingrese el nombre de la mascota: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese la raza de la mascota: ");
                    String raza = sc.nextLine();

                    System.out.print("Ingrese el peso de la mascota: ");
                    float peso = -1;

                    while(peso<1){
                        peso = Utils.retornaFloatNegativo();
                    }

                    //Todo esto para la fecha q salia con un LocalDate -.-
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    GregorianCalendar fechaNacimiento = new GregorianCalendar();
                    boolean valido = false;

                    while(!valido){
                        System.out.print("Ingrese la fecha de nacimiento de la mascota (dd/mm/yyyy): ");
                        String fecha = sc.nextLine();
                        
                        try {
                            fechaNacimiento.setTime(df.parse(fecha));

                            // Validar que no sea futura
                            GregorianCalendar hoy = new GregorianCalendar();
                            if (fechaNacimiento.after(hoy)) {
                                System.out.println("La mascota del futuro aun no existe, ingrese la fecha nuevamente.");
                            } else if((hoy.get(Calendar.YEAR)-fechaNacimiento.get(Calendar.YEAR)) >= 30){
                                System.out.println("Esta mascota es demasiado vieja para estar viva.");
                            } else {
                                valido = true; // Fecha válida
                            }

                        } catch (Exception e) {
                            Utils.escribirError(e);
                            System.out.println("Formato invalido, ingresa nuevamente");
                        }
                    }
                    System.out.println("Defina si la mascota es un perro o un gato");
                    System.out.println("\t1. Perro");
                    System.out.println("\t2. Gato");
                    System.out.print("Su opcion: ");

                    int tipoM = Utils.retornaIntRango(1,2);
                    
                    if(tipoM == 1){
                        //Vamos a asumir que el perro fue bañado para ser rescatado y jugaron con el antes de llevarlo al refugio
                        Perro perrito = new Perro(raza, fechaNacimiento, peso, nombre, new GregorianCalendar(), new GregorianCalendar(), true);
                        huellitas.rescatarMascota(perrito);
                        System.out.println("La mascota " + nombre + " ha sido rescatada");  
                    }else{
                        Gato gatito = new Gato(raza, fechaNacimiento, peso, nombre, new GregorianCalendar(), false);
                        huellitas.rescatarMascota(gatito);
                        System.out.println("La mascota " + nombre + " ha sido rescatada");
                    }
                    break;
                }
                
                case 2:{
                    if(huellitas.cantidadInternosDisponibles()==0){
                        System.out.println("Aun no hay mascotas disponibles");
                        break;
                    }
                    Persona personita = null;
                    System.out.println("Ingrese la cedula de la persona que va a adoptar: ");
                    String cedula = Utils.retornaCedula();  

                    if(huellitas.buscarCliente(cedula) == null){
                    
                        System.out.println("La persona aun no está agregada, vamos a agregarla");

                        System.out.print("Ingrese el nombre de la persona: ");
                        String nombresito = sc.nextLine();

                        System.out.print("Ingrese la edad de la persona: ");
                        int edad = Utils.retornaInt();

                        System.out.print("Ingrese la direccion de la persona: ");
                        String residencia = sc.nextLine();

                        personita = new Persona(nombresito,edad,residencia,cedula);
                        huellitas.agregarCliente(personita);
                    }else{
                        System.out.println("La persona ya existe en el sistema");
                        personita = huellitas.buscarCliente(cedula);
                    }

                    huellitas.mostrarInternos();

                    System.out.print("Ingrese el nombre de la mascota a adoptar: ");
                    String nMascota = sc.nextLine();

                    Mascota p = huellitas.buscarMascota(nMascota, huellitas.getInternos());

                    while (p == null || p.calcularEdad() < 1) {
                        System.out.println("No se encontró la mascota, ingrese otro nombre:");
                        nMascota = sc.nextLine(); 
                        p = huellitas.buscarMascota(nMascota, huellitas.getInternos());
                    }

                    huellitas.darAdopcion(p,personita);
                    break;
                }


                case 3:{
                    String cedula = Utils.retornaCedula();

                    Persona personita = huellitas.buscarCliente(cedula);
                    
                    if(personita == null){
                        System.out.println("Lo sentimos, la persona no existe en el sistema");
                    }else{
                        personita.mostrarMascotas();
                        System.out.print("Ingrese el nombre de la mascota que va a cambiar nombre: ");
                        String nAntiguo = sc.nextLine();

                        Mascota mascotita = huellitas.buscarMascota(nAntiguo, personita.getMascotas());

                        while(mascotita == null){
                            System.out.print("No se encontró la mascota. Ingrese nuevamente el nombre: ");
                            nAntiguo = sc.nextLine();
                            mascotita = huellitas.buscarMascota(nAntiguo, personita.getMascotas());
                        }


                        System.out.print("Ingrese el nuevo nombre: ");
                        String nNuevo = sc.nextLine();

                        personita.cambiarNombreMascota(mascotita, nNuevo); 
                    }
                    break;
                }
                
                case 4:{   
                    String cedula = Utils.retornaCedula();

                    Persona personita = huellitas.buscarCliente(cedula);
                    
                    if(personita == null){
                        System.out.println("Lo sentimos, la persona no existe en el sistema");
                    }else{
                        System.out.println("Elige lo que quieres hacer");
                        System.out.println("\t1. Ingresar mascota en la guarderia");
                        System.out.println("\t2. Retirar mascota de la guarderia");
                        System.out.print("Su opcion: ");

                        int tipoM = Utils.retornaIntRango(1, 2);

                        if(tipoM == 1){
                            huellitas.dejarMascota(personita);
                        }else{
                            if(huellitas.getGuarderia().isEmpty()){
                                System.out.println("No hay mascotas en la guardería");
                            }else{
                                System.out.print("Ingrese el nombre de la mascota: ");
                                String nombre = sc.nextLine();
                                huellitas.recogerMascota(nombre, personita);
                            }
                        }
                            
                    }
                    break;
                }

                case 5:{
                    if(huellitas.getGuarderia().isEmpty()){
                        System.out.println("No hay animales en la guardería para jugar");
                        break;
                    }
                    System.out.println("Elige la mascota con la que quieres jugar");
                    huellitas.mostrarGuarderia();
                    System.out.print("Ingrese el nombre de la mascota con la que va a jugar: ");
                    String nMascota = sc.nextLine();
                    Mascota mascotita = huellitas.buscarMascota(nMascota, huellitas.getGuarderia());

                        while(mascotita == null){
                            System.out.print("No se encontró la mascota. Ingrese nuevamente el nombre: ");
                            nMascota = sc.nextLine();
                            mascotita = huellitas.buscarMascota(nMascota, huellitas.getGuarderia());
                        }
                    huellitas.interactuar(mascotita);
                    break;
                }

                case 6:{
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