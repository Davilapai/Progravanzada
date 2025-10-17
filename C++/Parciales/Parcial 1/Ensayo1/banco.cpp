#include <iostream>
#include <string.h>
#include <fstream>

using namespace std;

struct Telefono{
    char* numeroTelefonico;
};

struct TarjetaCredito{
    char numeroTarjeta[17];
    char fechaVencimiento[6];
    int codigoSeguridad;
    double saldoDisponible;
};

struct Titular{
    char cedula[20];
    char nombreApellido[40];
    Telefono* telefonos;
    int cantidadTelefonos;
    TarjetaCredito* tarjetasCredito;
    int cantidadTarjetas;
};

struct Banco{
    char nombre[40];
    char direccion[45];
    Titular* titulares;
    int cantidadTitulares;
};

void agregarTelefonoTitular(Titular &titularsito, char arr[]){
    int longitud = (*(arr)=='+') ? 14:11;
    Telefono* nuevos = new Telefono[titularsito.cantidadTelefonos + 1];

    for(int i=0; i<titularsito.cantidadTelefonos; i++){
        *(nuevos+i)=*(titularsito.telefonos+i);
    }

    (nuevos+(titularsito.cantidadTelefonos))->numeroTelefonico = new char[longitud];
    strcpy(nuevos[titularsito.cantidadTelefonos].numeroTelefonico,arr);

    delete[] titularsito.telefonos;
    titularsito.telefonos = nuevos;
    titularsito.cantidadTelefonos++;
}

void agregarTarjetaCreditoTitular(Titular &titularsito, TarjetaCredito &tarjetita){
    TarjetaCredito* nuevas = new TarjetaCredito[titularsito.cantidadTarjetas + 1];

    for(int i=0; i<titularsito.cantidadTarjetas; i++){
        *(nuevas+i) = *(titularsito.tarjetasCredito+i);
    }

    nuevas[titularsito.cantidadTarjetas] = tarjetita;

    delete[] titularsito.tarjetasCredito;
    titularsito.tarjetasCredito = nuevas;
    titularsito.cantidadTarjetas++;
}

Banco cargarArchivoTexto(string nombre){
    Banco banquito;
    banquito.cantidadTitulares = 0;
    banquito.titulares = nullptr;

    ifstream file(nombre);
    if(!file) exit(1);

    char temp[256];

    file.getline(temp,256);

    char* token = strtok(temp, ",");
    strcpy(banquito.nombre,token);
    
    token = strtok(NULL, ",");
    strcpy(banquito.direccion, token);
    
    while(file.getline(temp,256)){
        Titular* nuevos = new Titular[banquito.cantidadTitulares+1];
        
        for(int i=0; i<banquito.cantidadTitulares; i++){
            *(nuevos+i) = *(banquito.titulares+i);
        }

        nuevos[banquito.cantidadTitulares].telefonos = nullptr;
        nuevos[banquito.cantidadTitulares].cantidadTelefonos = 0;
        nuevos[banquito.cantidadTitulares].tarjetasCredito = nullptr;
        nuevos[banquito.cantidadTitulares].cantidadTarjetas = 0;


        token = strtok(temp,",");
        strcpy(nuevos[banquito.cantidadTitulares].cedula,token);
        
        token = strtok(NULL, ",");
        strcpy(nuevos[banquito.cantidadTitulares].nombreApellido,token);

        while((token = strtok(NULL, ","))!=NULL){
            agregarTelefonoTitular(nuevos[banquito.cantidadTitulares],token);
        }

        file.getline(temp,256);

        if((strstr(temp,"CC"))!=nullptr){
            TarjetaCredito tarjetita;

            token = strtok(temp,",");
            strcpy(tarjetita.numeroTarjeta,token);

            token = strtok(NULL, ",");
            strcpy(tarjetita.fechaVencimiento,token);

            token = strtok(NULL, ",");
            tarjetita.codigoSeguridad = atoi(token);

            token = strtok(NULL, ",");
            tarjetita.saldoDisponible = atof(token);

            agregarTarjetaCreditoTitular(nuevos[banquito.cantidadTitulares], tarjetita);
        }
        delete[]banquito.titulares;
        banquito.titulares = nuevos;
        banquito.cantidadTitulares++;
    }
    return banquito;
}

void generarReporte(Banco &banquito){
    ofstream file("martesVisa.txt");
    if(!file)exit(1);

    for(int i=0; i<banquito.cantidadTitulares; i++){
        file << "Nombre: " <<(banquito.titulares+i)->nombreApellido << endl;
        file << "Celular: ";

        for(int j=0; j<(banquito.titulares+i)->cantidadTelefonos; j++){
            if(*(((banquito.titulares+i)->telefonos+j)->numeroTelefonico)=='+'){
            file << ((banquito.titulares+i)->telefonos+j)->numeroTelefonico << ", ";
            }
        }

        file << endl << "Fijo: ";

        for(int j=0; j<(banquito.titulares+i)->cantidadTelefonos; j++){
            if(*(((banquito.titulares+i)->telefonos+j)->numeroTelefonico)!='+'){
            file << ((banquito.titulares+i)->telefonos+j)->numeroTelefonico << ", ";
            }
        }

        for(int j=0; j<(banquito.titulares+i)->cantidadTarjetas; j++){
            TarjetaCredito* tarjetita = ((banquito.titulares+i)->tarjetasCredito+j);
            if(*(tarjetita->numeroTarjeta)=='4'){
                file << endl << tarjetita->numeroTarjeta << "," << tarjetita->codigoSeguridad << "," << tarjetita->saldoDisponible;
            }
        
        }
        
        file << endl << "#" << endl;

    }
    
    file.close();

}

void guardarBinario(Banco &banquito){
    ofstream file("tarjetas.bat", ios::binary);
    if(!file)exit(1);

    for(int i=0; i<banquito.cantidadTitulares; i++){
        for(int j=0; j<(banquito.titulares+i)->cantidadTarjetas; j++){
            file.write((char*)((banquito.titulares+i)->tarjetasCredito+j), sizeof(TarjetaCredito));
        }
    }

    file.close();
}