#include <iostream>
#include <string>
#include <fstream>

using namespace std;

struct articulo {
    string nombre;
    int valorCompra;
    int valorVenta;
};

int leerArchivo(string nombreArchivo, articulo arr[]);
void imprimirArticulo(articulo arr[], int n);
int calcularArticulosGanancia(articulo arr[], int n);
void separarArticulos(articulo arr[], int n, articulo arrGanancias[], articulo arrPerdidas[]);
void escribirArchivo(string nombreArchivo, articulo arr[], int n);


int main() {
    articulo articulosTotales[50];

    int cantidadArticulos = leerArchivo("articulos.txt", articulosTotales);
    int cantidadGanancias = calcularArticulosGanancia(articulosTotales, cantidadArticulos);
    int cantidadPerdidas = cantidadArticulos-cantidadGanancias;

    imprimirArticulo(articulosTotales, cantidadArticulos);

    articulo articulosGanancias[cantidadGanancias];
    articulo articulosPerdidas[cantidadPerdidas];

    separarArticulos(articulosTotales, cantidadArticulos, articulosGanancias, articulosPerdidas);

    cout << "\nArticulos con ganancias:" << endl;
    imprimirArticulo(articulosGanancias, cantidadGanancias);

    cout << "\nArticulos con perdidas:" << endl;
    imprimirArticulo(articulosPerdidas, cantidadPerdidas);

    escribirArchivo("ganancias.txt", articulosGanancias, cantidadGanancias);
    escribirArchivo("perdidas.txt", articulosPerdidas, cantidadPerdidas);

    return 0;
}

int leerArchivo(string nombreArchivo, articulo arr[]) {
    int contador = 0;
    string linea;
    ifstream archivo(nombreArchivo);

    if (!archivo.is_open()) {
        exit(1);
    }
    
    while(getline(archivo, arr[contador].nombre)) {
    getline(archivo, linea);
    if (linea.empty()) break;
    arr[contador].valorCompra=stoi(linea);

    getline(archivo, linea);
    if (linea.empty()) break;
    arr[contador].valorVenta=stoi(linea);

    getline(archivo, linea); // l�nea con #
    contador++;
    }

    archivo.close();
    return contador;
}

void imprimirArticulo(articulo arr[], int n){
    for (int i=0; i<n; i++){
        cout<<endl<<"Articulo: "<<arr[i].nombre;
        cout<<endl<<"Precio de compra: "<<arr[i].valorCompra;
        cout<<endl<<"Precio de venta: "<<arr[i].valorVenta;
    }
}

int calcularArticulosGanancia(articulo arr[], int n){
    int contador=0;

    for(int i=0; i<n; i++){
        if(arr[i].valorVenta>arr[i].valorCompra){
            contador++;
        }
    }
    return contador;
}

void separarArticulos(articulo arr[], int n, articulo arrGanancias[], articulo arrPerdidas[]){
    int ganancias=0, perdidas=0;
    for(int i=0; i<n; i++){
        if(arr[i].valorVenta>arr[i].valorCompra){
            arrGanancias[ganancias++]=arr[i];
        }else{
            arrPerdidas[perdidas++]=arr[i];
        }
    }
}

void escribirArchivo(string nombreArchivo, articulo arr[], int n){
    ofstream archivo(nombreArchivo);

    if (!archivo.is_open()) {
        exit(1);
    }
    
    int plata=0;
    for (int i = 0; i < n; i++) {
        plata+=(arr[i].valorVenta-arr[i].valorCompra);
    }

    archivo<<"Total: "<<plata<<endl;
    archivo<<"Total articulos: "<<n<<endl;
    archivo<<"Nombre articulos: ";

    for (int j=0; j<n; j++){
        archivo<<arr[j].nombre<<", ";
    }
    archivo.close();
}
