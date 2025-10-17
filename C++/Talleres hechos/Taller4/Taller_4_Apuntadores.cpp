#include <iostream>

using namespace std;

struct Persona {
	string nombre;
	int edad;
	int semestre;
};

/*Declaracion de funciones*/
int duplicar(int numero);
void duplicarNumero(int* n);
void llenarPares(int* arr, int n);
void imprimirPersonita(Persona* p);
double calcularPromedioEdad(Persona* arr, int n);
void imprimirNumeritos(int* arr, int n);

/*
Completar siguiendo las instrucciones
*/

int main() {

	//Declaracion de variables
	int numero = 10;

	/*
	    Parte 1. Entendimiento concepto apuntador
	*/
	// 1.1 Imprimir la direccion de memoria de la variable numero usando el operador direccion &
	cout << "Direccion de memoria de numero: " << &numero <<endl;

	// 1.2 Generar una variable de tipo apuntador a entero llamada "direccion" con la direccion de memoria de numero.
	int* direccion = &numero;

	// 1.3 Imprima el contenido de la variable "direccion"
	cout << "Contenido de la variable direccion: " << direccion << endl;
	/*
	    Que tipo de informaciC3n contiene un apuntador?
	    r: una direccion de memoria.
	*/

	// 1.4 A partir de "direccion" usar el operador de direccion * para imprimir el valor de numero
	cout << "Valor del numero usando *: " << *direccion << endl;

	// 1.5 Use el operador de indireccion sobre la variable numero.
	//cout << "Indireccion sobre numero: " << *numero << endl;
	/*
	    Por que se genera un error?
	    r: Porque el operador de indireccion se usa unicamente sobre direcciones de memoria, mientras que numero es un entero
	*/

	/*
	2. Paso por referencia y por valor
	*/
	int valor = 10;
	cout << "Antes: " << valor << endl;
	duplicar(valor);
	cout << "Despues: " << valor << endl;
	
	/* 2.1 
	    Generar una nueva funcion de tipo void que tenga la misma funcionalidad que duplicar.
	    La funcion debe recibir el valor por referencia y duplicarlo al interior de la funcion.

	    Use la funcion en el main e imprima el valor despues de llamar la nueva funciC3n
	*/
	cout << "Antes funcion duplicar:" << valor << endl;
	duplicarNumero(&valor);
	cout << "Despues funcion duplicar: " << valor << endl;

	/*
	    Parte 3. Aritmetica de apuntadores.
	*/

	//3.1 crear un tercer apuntador llamado "suma" que al igual que en 1.2 guarde la direccion de numero
	int* suma = &numero;

	//3.2 Imprimir el valor del apuntador "suma".
	cout << "Valor del apuntador suma: " << suma << endl;

	//3.3 Sumar 1 al apuntador "suma" e imprimirlo nuevamente en pantalla.
	cout << "Valor del apuntador suma + 1: " << suma+1 << endl;
	/*
	    Cuantos bytes aumenta su valor?
	    r: aumentó 4 bytes. 
	*/
	short mini;
	//3.4 crear un cuarto apuntador llamado "apuntadorShort" que guarde la direccion de la variable "mini"
	short* apuntadorShort = &mini;

    //3.5 Imprimir el valor del apuntador "apuntadorShort".
    cout << "Valor del apuntador apuntadorShort: " << apuntadorShort << endl;  

	//3.6 Sumar 1 al apuntador "apuntadorShort" e imprimirlo nuevamente en pantalla.
	cout << "Valor del apuntador apuntadorShort + 1: " << apuntadorShort+1 << endl;

	/*
		Cuantos bytes aumenta su valor?
	    r: aumentó 2 bytes
	*/    
    
    /*
        3.7  Porqué al sumar 1 los apuntadores aumentan en diferente cantidad?
        r: Depende del tamaño del tipo de dato utilizado, en el caso de int son 4 bytes y para short son 2
    */
    
	/*
	    4. Arreglos y apuntadores
	*/

	int arreglo[5] = {1,2,3,4,5};

	cout << arreglo << endl;
	/*
	    4.1 Que tipo de dato aparece cuando se imprime la variable arreglo?
	    r: aparece la direccion de memoria del primer elemento del arreglo.
	*/

	// 4.2 Imprimir la direccion de memoria del elemento en la posicion 1 del arreglo
	cout << "Direccionde memoria del elemento en la posicion 1 del arreglo: " << arreglo+1 << endl;

	// 4.3 Imprimir el elemento en la posicion 1 del arreglo usando indireccion* y aritmetica de apuntadores
	cout << "Elemento en la posicion 1 del arreglo: " << *(arreglo+1) << endl;

	// 4.4 Imprimir los elementos del arreglo usando un ciclo for y indices [i]
	for (int i = 0; i<5; i++){
		cout << "For elemento en la posicion " << i << " del arreglo: " << arreglo[i] << endl;
	}

	// 4.5 Imprimir los elementos del arreglo en un ciclo for usando indireccion* y aritmetica de apuntadores
	for (int i = 0; i<5; i++){
		cout << "For elemento (indireccion) en la posicion " << i << " del arreglo: " << *(arreglo+i) << endl;
	}

	// 4.6 Cambiar el valor del elemento 3 del arreglo por el numero 100 usando indireccion* y aritmetica de apuntadores
	*(arreglo+2)=100;

    int pares[10];
    // 4.7 Crear una funcion que recibe el nombre de un arreglo, su tamanio y lo llena con los primeros numeros pares
    // Usar indireccion y aritmetica de apuntadores. 
    // EJ: Si el arreglo es de tamanio 4 este deber a ser {2,4,6,8}
	llenarPares(pares, 10);

    

	/*
	    5. Apuntadores a estructuras
	*/
	Persona person;
	person.nombre = "juan";
	person.edad = 14;
	person.semestre = 16;

	// 5.1 Generar un apuntador a person llamado "personaApuntador".
	Persona* personaApuntador = &person;

	// 5.2 Usando personaApuntador imprimir los datos de person. Usar ->
	cout << "Nombre: " << personaApuntador->nombre << endl;
	cout << "Edad: " << personaApuntador->edad << endl;
	cout << "Semestre: " << personaApuntador->semestre << endl;

	// 5.3 Usando personaApuntador imprimir los datos de person. Usar indireccion* y . para acceder a los atributos
	cout << "Indireccion Nombre: " << (*personaApuntador).nombre << endl;
	cout << "Indireccion Edad: " << (*personaApuntador).edad << endl;
	cout << "Indireccion Semestre: " << (*personaApuntador).semestre << endl;
	
	// 5.4 crear una segunda persona usando el operador new. Asigne los datos que quiera a sus atributos
	Persona* personita = new Persona;
	personita->nombre = "David";
	personita->edad = 18;
	personita->semestre = 2;

	// 5.5 Generar una funcion que recibe un apuntador a persona e imprime los datos de la persona
	// Usar la funcion para imprimir los datos de la persona creada en 5.4
	imprimirPersonita(personita);
	delete personita;

	// 5.6 Generar un arreglo de estructuras Persona con la palabra new de tama o 3.
	Persona* grupo = new Persona[3];

	// 5.7 Llene los datos del arreglo usando aritmetica de Apuntadores
	for(int i = 0; i < 3; i++){
		(grupo+i)->nombre = "Juan";
		(grupo+i)->edad = 18+i;
		(grupo+i)->semestre = 2+i;
	}

	// 5.8 Generar una funcion que RETORNA el promedio de edad del arreglo. Imprimir el promedio
	cout << "El promedio de edad del arreglo es: " << calcularPromedioEdad(grupo, 3) << endl;
	delete [] grupo; 
	
	// 5.8 Generar un arreglo de numeros usando la palabra new. Tama o del arreglo = 10
	int* numeritos = new int[10];
	
	// 5.9 Con un ciclo for rellenar el arreglo con los primeros multiplos de 3.
	for(int i = 0; i < 10; i++){
		*(numeritos+i) = 3 * (i+1);
	}
	
	// 5.10 Crear una funcion que recibe el arreglo, tamanio e imprime cada uno de sus datos usando aritmetica e indireccion.
	imprimirNumeritos(numeritos, 10);
	delete [] numeritos;
	
	return 0;
}

int duplicar(int numero) {
	return numero * 2;
}

void duplicarNumero(int* n){
	*n *= 2;
}

void llenarPares(int* arr, int n){
	
	for(int i = 0; i < n ; i++){
		*(arr+i) = 2*(i+1);
	}
}

void imprimirPersonita(Persona* p){
	cout << "Nombre de la personita: " << p->nombre << endl;
	cout << "Edad de la personita: " << p->edad << endl;
	cout << "Semestre de la personita: " << p->semestre << endl;
}

double calcularPromedioEdad(Persona* arr, int n){
    double suma = 0;

    for(int i = 0; i < n; i++){
        suma += (arr+i)->edad;
    }

    return suma / n;
}

void imprimirNumeritos(int* arr, int n){
	for(int i = 0; i < n; i++){
		cout << *(arr+i) << " ";
	}
}
              