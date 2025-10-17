package Taller1;

import java.util.Scanner;

public class Taller1{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        int opcion;
        do{
            System.out.println("1. Promedio de notas \n2. Calculadora basica \n3. Contador de pares e impares\n4. Tabla de multiplicar \n5. Suma de elementos de un arreglo \n0. Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    promedio();
                    break;
                
                case 2: 
                    calculadora();
                    break;
                
                case 3:
                    paresImpares();
                    break; 
                
                case 4: 
                    tablaMultiplicar();
                    break;

                case 5: 
                    suma();
                    break;

                case 0: 
                    System.out.println("Gracias.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while(opcion != 0);
    }
    
    public static void promedio(){
        System.out.print("Ingrese la cantidad de numeros a promediar: ");
        int cantidad = sc.nextInt();

        float[]arr = new float[cantidad];
        float suma = 0;

        for(int i = 0; i < cantidad; i++){
            System.out.print("Ingrese el numero " + (i+1) + ": ");
            arr[i] = sc.nextFloat();
            suma+=arr[i];
        }
        System.out.println("El promedio de notas es:" + (suma/cantidad));
    }

    public static void calculadora(){
        char opcion;
        do{
            System.out.println("Ingrese la opcion a realizar\n + = Suma\n - = Resta\n * = Multiplicacion\n / = Division\n S = Salir" );
            System.out.print("Su eleccion: ");
            opcion = sc.next().charAt(0); //como java no lee solo 1 char este cosito lee el primer char de cada string ingresado

            int num1 = 0, num2=0;
            switch (opcion) {
                case '+':{
                    System.out.print("Ingrese el primer numero a sumar: ");
                    num1 = sc.nextInt();

                    System.out.print("Ahora ingrese el segundo numero: ");
                    num2 = sc.nextInt();

                    System.out.println("El resultado de su suma es: " + (num1+num2));
                break;  
                }

                case '-':{
                    System.out.print("Ingrese el primer numero a restar: ");
                    num1 = sc.nextInt();

                    System.out.print("Ahora ingrese el segundo numero: ");
                    num2 = sc.nextInt();

                    System.out.println("El resultado de su resta es: " + (num1-num2));
                break;  
                }

                case '*':{
                    System.out.print("Ingrese el primer numero a multiplicar: ");
                    num1 = sc.nextInt();

                    System.out.print("Ahora ingrese el segundo numero: ");
                    num2 = sc.nextInt();

                    System.out.println("El resultado de su multiplicacion es: " + (num1*num2));
                break;  
                }

                case '/':{
                    System.out.print("Ingrese el primer numero a dividir: ");
                    num1 = sc.nextInt();

                    System.out.print("Ahora ingrese el divisor: ");
                    num2 = sc.nextInt();

                    while(num2 == 0){
                        System.out.println("Lo sentimos, no se puede dividir por 0");
                        System.out.print("Ingrese el divisor nuevamente: ");
                        num2 = sc.nextInt();
                    }

                    float resultado = (float)num1 / num2; //caster para tener decimales en caso de ser necesario.

                    System.out.println("El resultado de su division es: " + resultado);
                break;  
                }
                case 's':
                case 'S':{
                    System.out.println("Hasta la proxima");
                break;  
                }

                default:{
                    System.out.println("Opcion no valida, ingrese su opcion nuevamente");
                break;
                }
                    
            }
        }while(opcion != 'S' && opcion != 's');

    }

    public static void paresImpares(){
        System.out.print("Ingrese la cantidad de numeros que quiere separar en pares o impares: ");
        int n = sc.nextInt();
        int entrada, pares=0, impares=0;
        
        for(int i = 0; i < n; i++){
            System.out.print("Ingrese el numero " + (i+1) + " :");
            entrada = sc.nextInt();
            if(entrada % 2 == 0){
                pares++;
            } else{
                impares++;
            }
        }
        System.out.println("Hay " + pares + " numeros pares y " + impares + " numeros impares");
    }

    public static void tablaMultiplicar(){
        System.out.print("Ingrese el numero del que quiere hacer su tabla de multiplicar (hasta el 10): ");
        int n = sc.nextInt();

        for(int i = 1; i <= 10; i++){
             System.out.println(n + " x " + i + " = " + (n*i));
        }
    }

    public static void suma(){
        System.out.print("Ingrese la cantidad de numeros a ingresar: ");
        int cantidad = sc.nextInt();

        double suma = 0;

        int[]arr = new int[cantidad];

        for(int i = 0; i < cantidad; i++){
            System.out.print("Ingrese el numero " + (i+1) + ": "); 
            arr[i] = sc.nextInt();
            suma+=arr[i];
        }
        System.out.println("El promedio de sumarlos es:" + suma);
    }
}
