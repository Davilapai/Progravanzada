#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

struct Ingrediente{
    char nombre[20];
    int valor;
    int cantidad;
};

struct Pocion{
    char nombre[20];
    int costoEsperado;
    int costoReal;
    bool sobrecosto = false;
    int cantidadIngredientes;
    Ingrediente* masPopular;
    Ingrediente* ingredientesNecesarios;   //Es un arreglo dinamico 
};

//Declaracion de funciones
int cantidadIngredientesArchivo(string nombre);
void agregarIngrediente(string nombre, Pocion* poti);
long calcularCostoListaIngredientes(Ingrediente* arr, int tamanio);
Ingrediente* ingredientePopular(Ingrediente* arr, int n);
void llenarDatosPocion(Pocion* poti);
void imprimirDatosPocion(Pocion poti);

int main(){
    const int TAM_POCIONES = 3;
    Pocion pocion[TAM_POCIONES];
    string nombresArchivos[TAM_POCIONES] = {"amor.mgc", "veneno.mgc", "levitacion.mgc"};

    strcpy(pocion[0].nombre, "Amor");
    pocion[0].costoEsperado = 12000;

    strcpy(pocion[1].nombre, "Veneno");
    pocion[1].costoEsperado = 1000;

    strcpy(pocion[2].nombre, "Levitacion");
    pocion[2].costoEsperado = 3000;

    for(int i = 0; i < TAM_POCIONES; i++){
        //Obtiene la cantidad de ingredientes del archivo 
        int temp = cantidadIngredientesArchivo(nombresArchivos[i]);

        //Asigna la cantidad de ingredientes a la pocion
        (pocion+i)->cantidadIngredientes = temp;

        //Crea un arreglo dinamico
        (pocion+i)->ingredientesNecesarios = new Ingrediente[temp];

        //Agrega las pociones a ese arreglo dinamicov
        agregarIngrediente(nombresArchivos[i], pocion+i);

        llenarDatosPocion(pocion+i);

        //Imprime los datos de la pocion en esa posicion.
        imprimirDatosPocion(*(pocion+i));
    }

    

    for(int i = 0; i < TAM_POCIONES; i++){
    delete[] (pocion+i)->ingredientesNecesarios;
    }

    

}

//Esta funcion obtiene el tamaño de todo el archivo y lo divide entre el tamaño de cada ingrediente
//Asi obtiene la cantidad de ingredientes del archivo. 
int cantidadIngredientesArchivo(string nombre){
    ifstream file(nombre, ios::binary | ios::ate);

    if(!file.is_open()){
        exit(1);
    }

    int tamanioArchivo = file.tellg();

    file.close();

    return tamanioArchivo / sizeof(Ingrediente);
}

//Esta funcion agrega los ingredientes al arreglo dinamico
void agregarIngrediente(string nombre, Pocion* poti){
    ifstream file(nombre, ios::binary);
    if(!file.is_open()){
        cerr << "Error abriendo archivo: " << nombre << endl;
        exit(1);
    }

    for(int i = 0; i < poti->cantidadIngredientes; i++){
        file.read((char*)& poti->ingredientesNecesarios[i], sizeof(Ingrediente));

    }

    file.close();
}

//Calcula el costo total considerando su valor y su cantidad
long calcularCostoListaIngredientes(Ingrediente* arr, int tamanio){
    long costoTotal = 0;

    for(int i = 0; i < tamanio; i++){
        costoTotal += (arr+i)->valor * (arr+i)->cantidad;
    }

    return costoTotal;
}

//Devuelve un puntero con el ingrediente mas popular
Ingrediente* ingredientePopular(Ingrediente* arr, int n){
    Ingrediente* popular = arr;

    for(int i = 0; i < n; i++){
        if(popular->cantidad < (arr+i)->cantidad){
            popular = arr+i;
        }
    }

    return popular;
}

//Llena los datos de la estructura pocion, empezando por el costo total, luego el sobrecosto (true si lo tiene), y finalmente el ingrediente mas popular
void llenarDatosPocion(Pocion* poti){
    poti->costoReal = calcularCostoListaIngredientes(poti->ingredientesNecesarios, poti->cantidadIngredientes);

    if(poti->costoEsperado < poti->costoReal){
        poti->sobrecosto = true;
    }

    poti->masPopular = ingredientePopular(poti->ingredientesNecesarios, poti->cantidadIngredientes);
}

//Imprime los datos de la estructura pocion (use el ternario para q se viera mas bonito) y tambien los datos de los ingredientes usados para la pocion.
void imprimirDatosPocion(Pocion poti){
    cout << endl << "Nombre: " << poti.nombre << endl;
    cout << "Costo esperado: " << poti.costoEsperado << endl;
    cout << "Costo real: " << poti.costoReal << endl;
    cout << "Sobrecosto: " << (poti.sobrecosto ? "Si tiene sobrecosto" : "No tiene sobrecosto") << endl;
    cout << "Cantidad ingredientes: "  <<  poti.cantidadIngredientes << endl;
    cout << "Ingrediente mas popular: " << poti.masPopular->nombre << endl << endl;
    cout << "INGREDIENTES" << endl;

    for(int i = 0; i < poti.cantidadIngredientes; i++){
        cout << "Nombre: " << (poti.ingredientesNecesarios+i)->nombre << endl;
        cout << "Valor: " << (poti.ingredientesNecesarios+i)->valor << endl;
        cout << "Cantidad: " << (poti.ingredientesNecesarios+i)->cantidad << endl<<endl;
    }
}


