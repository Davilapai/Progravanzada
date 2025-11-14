import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Utils{
    public static Scanner sc = new Scanner(System.in);
    
    public static void escribirError(Exception e){
        try{
            // Fecha y hora actual usando LocalDate en lugar de Gregorian calendar JAJAJAJA
            LocalDateTime fechaHora = LocalDateTime.now();
            BufferedWriter file = new BufferedWriter(new FileWriter("excepciones.txt",true)); //el true es para el append q feo java
            file.write(fechaHora + " - " + e.getMessage());
            file.newLine();
            file.close();
        } catch (Exception ex) {
            System.err.println("Error al registrar la excepción: " + ex.getMessage());
        }
    }

    public static int retornaNumeroSwitch(){
        int opcion;
                try{
                    opcion=sc.nextInt();
                    sc.nextLine();
                }catch(Exception e){
                    escribirError(e);
                    sc.nextLine();
                    opcion = 12;
                }
        return opcion;
    }

    public static float retornaFloatNegativo(){
        float peso;
        try{
            peso = sc.nextFloat();
            sc.nextLine();
            }catch(Exception e){
                escribirError(e);
                System.out.print("Ingrese un numero valido: ");
                sc.nextLine();
                peso = -1;
        }
        return peso;
    }

    public static int retornaIntRango(int inferior, int superior) {
        int numerito;
        while (true) {
            try {
                numerito = sc.nextInt();
                sc.nextLine();
                if (numerito < inferior || numerito > superior)
                    throw new Exception("Número fuera de rango");
                return numerito;
            } catch (Exception e) {
                escribirError(e);
                System.out.print("Elija una opción válida (" + inferior + " - " + superior + "): ");
                sc.nextLine(); // limpiar el buffer después del error
            }
        }
    }

    public static int retornaInt(){
        int numerito;
        while (true) {
            try {
                numerito = sc.nextInt();
                sc.nextLine();
                return numerito;
            } catch (Exception e) {
                Utils.escribirError(e);
                sc.nextLine(); // limpiar el buffer después del error
                System.out.print("Ingrese el numero nuevamente: ");
            }
        } 
    }

    public static String retornaCedula(){
        System.out.print("Ingrese la cedula del cliente: ");
        String cedula;

        while(true){
            try{
                cedula = sc.nextLine(); 
                if(cedula.length() != 10) throw new Exception("Cedula invalida, solo 10 caracteres");
                return cedula;
                } catch(Exception e){
                    Utils.escribirError(e);
                    System.out.println(e.getMessage());
                    System.out.print("Ingrese nuevamente: ");
            }
        }
    }

    
}
