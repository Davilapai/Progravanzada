package cliente;

import entidades.Estudiante;
import java.util.Scanner;

public class RegistrarEstudiante {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Estudiante[] estudiantes = new Estudiante[3];
		estudiantes[0] = new Estudiante("pepito", 18, 4.5); //aqui ya se invoca el constructor
		estudiantes[1] = new Estudiante("nico", 17, 4.2);
		estudiantes[2] = new Estudiante("lorena", 18, 4.8);
		
		definirPruebita(estudiantes[0]);
		definirPruebita(estudiantes[1]);
		definirPruebita(estudiantes[2]);

		mostrarPromedio(estudiantes);
		estudiantesPrueba(estudiantes);
		promedioPrueba(estudiantes);
	}

	public static void definirPruebita(Estudiante est){ //todos los metodos del main tienen que ser estaticos
		System.out.print("Ingrese la nota de este semestre para el estudiante " + est.getNombre() + ": ");
		double nueva = sc.nextDouble();

		System.out.print("Antes de actualizar el promedio " + est.getNombre() + ": ");
		System.out.println((est.definirPruebaAcademica()?"esta en prueba":"no esta en prueba") + " y su promedio es de: " + est.getPromedio());
		
		System.out.print("Después de actualizar el promedio de " + est.getNombre() + ": ");
		est.actualizarPromedio(nueva);
		System.out.println((est.definirPruebaAcademica()?"esta en prueba":"no esta en prueba") + " y su promedio es de: " + est.getPromedio());
	}

	public static void mostrarPromedio(Estudiante arr[]){
		double promedios = 0;
		for(int i = 0; i < arr.length; i++){
			promedios+=arr[i].getPromedio();
		}
		System.out.println("El promedio de los " + arr.length + " estudiantes es: " + (promedios/arr.length));
	}

	public static void estudiantesPrueba(Estudiante arr[]){
		int contador = 0;

		for(int i = 0; i<arr.length; i++){
			if(arr[i].definirPruebaAcademica()){
				contador++;
			}
		}

		System.out.println("La cantidad de estudiantes que estan en prueba es de: " + contador);
	}

	public static void promedioPrueba(Estudiante arr[]){ //imprime el promedio de los estudiantes en prueba
		int contador = 0;
		double promedio = 0;

		for(int i = 0; i<arr.length; i++){
			if(arr[i].definirPruebaAcademica()){
				contador++;
				promedio+=arr[i].getPromedio();
			}
		}

		if (contador > 0) {
			System.out.println("El promedio de los estudiantes en prueba es de : " + (promedio / contador));
		} else {
			System.out.println("No hay estudiantes en prueba académica.");
		}
	}
}
