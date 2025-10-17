#include <iostream>

using namespace std;

struct competidor{
    string nombre;
    int edad;
    int peso;
    int altura;
    int velocidad;
};

int registrarCompetidor(int cupos, int corredores, competidor arr[]);
void mostrarInformacionCompetidores(int n, competidor arr[]);
void mostrarInformacionCompetidor(int n, competidor arr[], int validador);
void simularCarrera(competidor arr[], int n);
void bubbleSortMayorAMenor(double arr[], int arr2[], int n);
void multiplicadores (double arr[], int arr2[], int n, int arr3[]    );
void mostrarResultados(double arr[], int arr2[], int n, competidor arr3[]);

int main(){

    int cantidadCorredores=0;
    int corredoresRegistrados=0;
    bool menu=true;

    cout<<"\tBienvenido al simulador de carreras. "<<endl;
    cout<<"A continuacion porfavor ingrese la cantidad de corredores: ";
    cin>>cantidadCorredores;

    competidor corredores[cantidadCorredores];

    while(menu){
        int option;
        cout<<endl<<endl<<"Ahora porfavor escoja una de las siguientes opciones"<<endl;
        cout<<"\t(1) Registrar competidor"<<endl;
        cout<<"\t(2) Mostrar informacion competidores"<<endl;
        cout<<"\t(3) Mostrar informacion competidor"<<endl;
        cout<<"\t(4) Simular carrera"<<endl;
        cout<<"\t(5) Salir"<<endl;
        cout<<"\tIngrese su opcion: ";
        cin>>option;

        switch(option){
            case 1:{
                corredoresRegistrados= registrarCompetidor(cantidadCorredores, corredoresRegistrados, corredores);
                break;
            }

            case 2:{
                mostrarInformacionCompetidores(corredoresRegistrados, corredores);
                break;
            }

            case 3:{
                int numeroCorredor=0;

                while(numeroCorredor<=0){
                    cout<<endl<<"Porfavor ingresa el numero del corredor que deseas buscar: ";
                    cin>>numeroCorredor;
                    mostrarInformacionCompetidor(numeroCorredor, corredores, corredoresRegistrados);
                }

                break;
            }

            case 4:{
                simularCarrera(corredores, corredoresRegistrados);
                break;
            }

            case 5:{
                cout<<endl<<"Gracias por usar nuestro simulador.";
                return 0;
                break;
            }

            default:{
                cout<<endl<<"Opcion no valida.";
            }
        }
    }
}

//Esta funcion registra competidores teniendo en cuenta la cantidad de plazas disponibles y les asigna un orden.
int registrarCompetidor(int cupos, int corredores, competidor arr[] ){
    cout<<endl<<"La cantidad actual de corredores registrados es de: "<<corredores;
    cout<<endl<<"Hay "<<cupos-corredores<<" cupos disponibles.";

    if(corredores<cupos){
        cin.ignore();
        cout<<endl<<"Ingresa el nombre del corredor "<<corredores+1<<" : ";
        getline(cin, arr[corredores].nombre);

        cout<<"Ingresa la edad del corredor "<<corredores+1<<" : ";
        cin>>arr[corredores].edad;

        cout<<"Ingresa el peso del corredor "<<corredores+1<<" : ";
        cin>>arr[corredores].peso;

        cout<<"Ingresa la altura en cm's del corredor "<<corredores+1<<" : ";
        cin>>arr[corredores].altura;

        cout<<"Ingresa la velocidad del corredor "<<corredores+1<<" : ";
        cin>>arr[corredores].velocidad;

        corredores++;

    } else{
        cout<<endl<<"Lo sentimos no hay mas cupos disponibles";
    }
    return corredores;
}

//Esta funcion muestra unicamente la informacion de los corredores registrados.
void mostrarInformacionCompetidores(int n, competidor arr[]){
    for(int i=0; i<n; i++){
        cout<<endl<<"Corredor "<<i+1<<": ";
        cout<<endl<<"\tNombre: "<<arr[i].nombre;
        cout<<endl<<"\tEdad: "<<arr[i].edad;
        cout<<endl<<"\tPeso: "<<arr[i].peso;
        cout<<endl<<"\tAltura (en cm's): "<<arr[i].altura;
        cout<<endl<<"\tVelocidad: "<<arr[i].velocidad<<endl;
    }
}

//Esta funcion muestra la informacion del corredor identificado con el numero que se le dio al momento del registro
//en caso de no existir muestra un mensaje de error.
void mostrarInformacionCompetidor(int n, competidor arr[], int validador){
    if(n<=validador){
        cout<<endl<<"Nombre: "<<arr[n-1].nombre;
        cout<<endl<<"Edad: "<<arr[n-1].edad;
        cout<<endl<<"Peso: "<<arr[n-1].peso;
        cout<<endl<<"Altura (en cm's): "<<arr[n-1].altura;
        cout<<endl<<"Velocidad: "<<arr[n-1].velocidad<<endl;

    } else{
        cout<<endl<<"Lo sentimos, el corredor no se encuentra registrado aun.";
    }
}

/*Esta función se encarga de simular toda la carrera. Recibe un arreglo de estructuras que representan
a los competidores y un entero con la cantidad de ellos. Lo primero que hace es crear tres arreglos:
uno para llevar el registro de la distancia recorrida por cada corredor, otro para guardar sus velocidades
y otro con los índices originales de los corredores. Luego usa la función bubbleSortMayorAMenor para ordenar
los corredores de mayor a menor según la distancia recorrida. Después entra en un ciclo que se ejecuta mientras
el que va de primero no haya llegado a 200 metros. En cada vuelta llama a la función multiplicadores, que actualiza
las distancias recorridas, teniendo en cuenta un multiplicador que le da ventaja al que va más atrás. Después de
cada actualización vuelve a ordenar con bubbleSortMayorAMenor. Finalmente, cuando alguien supera los 200 metros,
llama a la función mostrarResultados para imprimir los resultados.*/
void simularCarrera(competidor arr[], int n){
    double distancias[n];
    int velocidades[n];
    int corredores[n];

    for(int i=0; i<n; i++){
        velocidades[i]=arr[i].velocidad;
        distancias[i]=arr[i].velocidad;
        corredores[i]=i+1;
    }

    bubbleSortMayorAMenor(distancias,corredores,n);

    while(distancias[0]<=200){
        multiplicadores(distancias, velocidades, n, corredores);
        bubbleSortMayorAMenor(distancias,corredores,n);
    }

    mostrarResultados(distancias, corredores, n, arr);
}

/*Esta función recibe dos arreglos y su tamaño. El primero representa las distancias y el segundo
los identificadores o índices de los corredores. Recorre ambos arreglos y los ordena de mayor a
menor usando el algoritmo burbuja. Si encuentra que una distancia es menor que la siguiente, las
intercambia, y también intercambia los elementos correspondientes en el arreglo de identificadores
para que sigan estando sincronizados con su respectiva distancia.*/
void bubbleSortMayorAMenor(double arr[], int arr2[], int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            if (arr[j] < arr[j + 1]) {

                double temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                int temp2= arr2[j];
                arr2[j]=arr2[j+1];
                arr2[j+1]=temp2;
            }
        }
    }
}

/*Esta función es la que hace avanzar a los corredores. Recibe el arreglo de distancias, el de velocidades
y el de índices, además del tamaño. Recorre a todos los competidores y suma a su distancia un valor que
resulta de multiplicar su velocidad por un multiplicador que comienza en 1.0 y se va incrementando en 0.3
con cada corredor. Esto hace que los que van más atrás reciban un pequeño impulso adicional, simulando
que están presionando más para alcanzar a los que van adelante. Para obtener la velocidad correspondiente,
usa el índice que está en el arreglo de orden arr3, que guarda los corredores en el orden actual.*/
void multiplicadores (double arr[], int arr2[], int n, int arr3[]){
    float multiplicador=1;

    for(int i=0; i<n; i++){
        arr[i]+=arr2[arr3[i]-1]*multiplicador;
        multiplicador+=0.3;
    }
}

/*Esta función imprime en pantalla los resultados finales de la carrera. Recorre el arreglo ordenado de
posiciones e imprime el nombre y la distancia recorrida de cada corredor, indicando quién ganó y en qué lugar
quedaron los demás*/
void mostrarResultados(double arr[], int arr2[], int n, competidor arr3[]){
    for(int i=0; i<n; i++){
        cout<<endl<<i+1<<" Posicion: "<<arr3[arr2[i]-1].nombre<<", Distancia: "<<arr[i];
    }
}
