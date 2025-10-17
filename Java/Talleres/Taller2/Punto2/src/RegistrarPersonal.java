import java.util.Scanner;

public class RegistrarPersonal {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        System.out.print("Ingrese el numero de personas a agregar: ");
        int n = sc.nextInt();
        sc.nextLine();

        Persona[] personas = new Persona[n];

        for(int i = 0; i < n; i++){
            personas[i] = new Persona();
            System.out.print("Ingrese el nombre para la persona " + (i+1) + ": ");
            personas[i].setNombre(sc.nextLine());

            System.out.print("Ingrese la edad para la persona " + (i+1) + ": ");
            personas[i].setEdad(sc.nextInt());
            sc.nextLine();

            System.out.print("Ingrese el genero para la persona " + (i+1) + ", (H hombre, M mujer): ");
            personas[i].setGenero(sc.next().charAt(0));

            System.out.print("Ingrese el peso (en kg) para la persona " + (i+1) + ": ");
            personas[i].setPeso(sc.nextDouble());

            System.out.print("Ingrese la altura (en metros) para la persona " + (i+1) + ": ");
            personas[i].setAltura(sc.nextDouble());

            System.out.print("Ingrese el salario (en pesos) para la persona " + (i+1) + ": ");
            personas[i].setSalario(sc.nextDouble());

            System.out.print("Ingrese la cedula de identificacion para la persona " + (i+1) + ": ");
            personas[i].setId(sc.nextInt());
            sc.nextLine();
        }

        System.out.println("El total de salarios a pagar es de: " + totalSalarios(personas));
        System.out.println("Personas con imc no saludable");
        imcMalo(personas);

    }

    public static float totalSalarios(Persona arr[]){
        float salarios = 0;

        for(int i = 0; i < arr.length; i++){
            salarios+=arr[i].salarioMinimo();
        }

        return (float)(Math.round(salarios * 10.0) / 10.0);
    }

    public static void imcMalo(Persona arr[]){
        for(int i = 0; i < arr.length; i++){
            arr[i].imprimirInfoIMC();
        }
    }
}
