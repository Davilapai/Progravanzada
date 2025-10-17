import java.util.Scanner;
public class Calculadora {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        double nota1, nota2, nota3;

        System.out.print("Ingresa la primera nota: ");
        nota1 = sc.nextDouble();

        System.out.print("Ingrese la segunda nota: ");
        nota2 = sc.nextDouble();

        System.out.print("Ingrese la tercera nota: ");
        nota3 = sc.nextDouble();

        nota1+=nota2+nota3;
        nota1/=3;

        System.out.println(nota1 >= 3.0 ? "Aprobaste con promedio: " + nota1 : "Reprobaste con promedio: " + nota1);

        sc.close();
    }
}
