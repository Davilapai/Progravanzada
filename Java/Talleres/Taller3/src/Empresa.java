import java.util.Scanner;

public class Empresa {
    public static Scanner sc = new Scanner(System.in);    
    public static void main(String[] args) {
        System.out.println("El salario minimo es de: " + Empleado.SALARIOMIN);
        System.out.print("Ingrese la cantidad de empleados de su empresa: ");

        int empleados = sc.nextInt();

        Empleado empleaditos[] = new Empleado[empleados];

        for(int i = 0; i < empleados; i++){
            System.out.println("Ahora va a ingresar los datos para su empleado " + (i+1));
            empleaditos[i]=crearEmpleado();
        }

        for(int i = 0; i < empleados; i++){
            System.out.println("Ahora se va a mostrar la informacion de su empleado " + (i+1));
            mostrarEmpleado(empleaditos[i]);
        }

        System.out.print("Cuanto va a ser el bono de todos sus empleados: ");
        double bonito = sc.nextDouble();

        for(int i = 0; i < empleados; i++){
            empleaditos[i].setBonoEmpleados(bonito);
        }

        double cantidadNomina = 0;

        System.out.println("Ahora vamos a calcular la nomina de su empresa");

        for(int i = 0; i < empleados; i++){
            System.out.println("El empleado " + empleaditos[i].getNombre() + " cumplio años este mes?");
            System.out.print("1) SI \n0) NO \nsu opcion: ");
            int opcion = sc.nextInt();

            while(opcion!=1 && opcion!=0){
                System.out.print("Ingrese la opcion nuevamente: ");
                opcion = sc.nextInt();
            }

            if(opcion == 1){
                System.out.print("Ingrese el bonito de cumpleaños para su empleado: ");
                double bonoCumple = sc.nextDouble();
                cantidadNomina += empleaditos[i].calcularTotalNominal(bonoCumple);
            } else {
                cantidadNomina += empleaditos[i].calcularTotalNominal();
            }

        }

        System.out.println("La cantidad de nomina de la empresa es de: " + cantidadNomina);
        System.out.println("El pago al sena es de: " + (cantidadNomina*0.04));

    }

    public static Empleado crearEmpleado() {
        Empleado nuevito = new Empleado();

        sc.nextLine();
        System.out.print("Ingrese el nombre de su nuevo empleado: ");
        nuevito.setNombre(sc.nextLine());

        System.out.print("Ingrese la cédula de su nuevo empleado: ");
        nuevito.setCedula(sc.nextLong());
        sc.nextLine(); // Consumir salto de línea pendiente

        System.out.print("Ingrese cuántos salarios gana su nuevo empleado: ");
        int salarios = sc.nextInt();
        nuevito.setSalario(salarios * Empleado.SALARIOMIN);

        return nuevito;
    }
    public static void mostrarEmpleado(Empleado pepito){
        System.out.println("Nombre: " + pepito.getNombre());
        System.out.println("Cedula: " + pepito.getCedula());
        System.out.println("Salario: " + pepito.getSalario());
    }

}

