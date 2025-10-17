#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

struct Transaccion{
    char tipo;
    long monto;

};

struct Cliente{
    char nombre[21];
    char numeroCuenta[11];
    char tipoCuenta;
    int numeroTransacciones;
    Transaccion* transacciones;
};

Cliente leerArchivo(char* nombre){
    Cliente cliente;
    ifstream file(nombre, ios::binary);
    
    if(!file)exit(1);

    file.read((char*)&cliente, sizeof(Cliente));

    cliente.transacciones = new Transaccion[cliente.numeroTransacciones];

    file.read((char*)cliente.transacciones, sizeof(Transaccion)*cliente.numeroTransacciones);

    file.close();
    return cliente;
}

long sumatoria(Transaccion* transacciones, int tam, char cual){
    long suma = 0;

    for(int i=0; i<tam; i++){
        if((transacciones+i)->tipo==cual){
            suma+=(transacciones+i)->monto;
        }
    }
    return suma;
}

long saldoTotal(Transaccion* transacciones, int tam){
    long creditos = sumatoria(transacciones,tam,'C');
    long debitos = sumatoria(transacciones,tam,'D');
    return (creditos-debitos);
}

void generarArchivo(Cliente cliente, char* nombre){
    ofstream file(nombre);
    if(!file)exit(1);

    file << "Cliente: " << cliente.nombre << endl;
    file << "Cuenta: " << cliente.numeroCuenta << endl;

    if(cliente.tipoCuenta == 'A'){
        file << "Tipo de cuenta: Ahorros" << endl;
    }else{
        file << "Tipo de cuenta: Corriente" << endl;
    }

    file << "Total en cuenta: " << saldoTotal(cliente.transacciones,cliente.numeroTransacciones) << endl;
    file << "Total creditos: " << sumatoria(cliente.transacciones,cliente.numeroTransacciones,'C') << endl;
    file << "Total debitos: " << sumatoria(cliente.transacciones,cliente.numeroTransacciones,'D') << endl;
    file << "Numero de transacciones en Archivo: " << cliente.numeroTransacciones << endl;

    file.close();
}