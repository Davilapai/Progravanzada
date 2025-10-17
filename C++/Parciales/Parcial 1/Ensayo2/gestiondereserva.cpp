#include<iostream>  
#include<string.h>
#include<fstream>

using namespace std;

struct Pasajero{
    char nombre[50];
    char apellido[50];
    int documento;
    char nacionalidad[30];
};

struct Asiento{
    int numeroAsiento;
    bool ocupado;
    Pasajero pasajero;
};

struct Vuelo{
    char codigoVuelo[10];
    char origen[50];
    char destino[50];
    char fecha[11];
    Asiento* asientos;
};

struct Avion{
    char nombreEmpresa[50];
    char codigoAvion[10];
    int numeroAsientos;
    Vuelo* vuelos;
    int cantidadVuelos;
};

struct Corp{
    Avion* aviones;
    int numeroAviones;
};

void agregarAvionEmpresa(Corp &empresa, Avion avion){
    int cantidad = empresa.numeroAviones;
    *(empresa.aviones+cantidad) = avion;
    empresa.numeroAviones++;
}

Avion* buscarAvionCodigo(Corp empresa, char* codigo){
    int cantidad = empresa.numeroAviones;
    Avion* retorno = nullptr;
    for(int i=0; i<cantidad; i++){
        if(strcmp((empresa.aviones+i)->codigoAvion,codigo)==0){
            retorno = (empresa.aviones+i);
        }
    }
    return retorno;
}

void agregarVuelo(char* linea, Corp empresa){
    char* token;
    token = strtok(linea,",");

    Avion* avionsito = buscarAvionCodigo(empresa, token);

    Vuelo vuelito;
    
    token = strtok(NULL,",");
    strcpy(vuelito.codigoVuelo,token);

    token = strtok(NULL,",");
    strcpy(vuelito.origen, token);

    token = strtok(NULL,",");
    strcpy(vuelito.destino, token);

    token = strtok(NULL,",");
    strcpy(vuelito.fecha, token);
    
    int cantidad = avionsito->numeroAsientos;
    vuelito.asientos = new Asiento[cantidad];

    *(avionsito->vuelos+avionsito->cantidadVuelos) = vuelito;
}

void leerArchivo(Corp empresa){
    ifstream file("vuelos.txt");
    if(!file)exit(1);

    char linea[256];
    char nombre[256];

    while(file.getline(linea,256)){

        strcpy(nombre,linea); //Extraer nombre empresa
        char* token;
        
        file.getline(linea,256); //Saca linea despues del nombre

        token = strtok(linea,"-,"); //Dividimos linea para sacar codigo

        while(token!=NULL){
            Avion avionsito;
            strcpy(avionsito.nombreEmpresa,nombre); //copia el nombre
            strcpy(avionsito.codigoAvion,token); //copia el codigo del avion

            token = strtok(NULL, "-,"); //da otra pasada para sacar numero de asientos
            avionsito.numeroAsientos = atoi(token); //copia numero de asientos

            agregarAvionEmpresa(empresa,avionsito); //agrega el avion a la empresa

            token = strtok(NULL,"-,"); //hace otro token para tener el nombre o null si terminó
        }

        file.getline(linea,256); //saca la tercera linea donde empiezan vuelos

        while(*(linea)!='#'){ //comprueba si la linea no empieza con # que separa las aerolineas
            agregarVuelo(linea,empresa); //agrega el vuelo a la empresa
            file.getline(linea,256); //lee otra linea
        }
    }

    file.close();

}

void generarReporte(char* origen, Corp empresa){
    ofstream texto("vuelosOrigen.txt");
    ofstream binario("vuelosOrigen.dat", ios::binary);

    if(!texto)exit(1);
    if(!binario)exit(1);
    
    int cantidad = empresa.numeroAviones;

    for(int i=0; i<cantidad; i++){
        int nVuelos = (empresa.aviones+i)->cantidadVuelos;

        for(int j=0; j<nVuelos; j++){
            Vuelo* vuelito = ((empresa.aviones+i)->vuelos+j);
            if(strcmp(vuelito->origen,origen)==0){
                texto << vuelito->codigoVuelo << ", ";
                texto << vuelito->origen << ", ";
                texto << vuelito->destino << ", ";
                texto << vuelito->fecha << ", ";

                binario.write((char*)vuelito,sizeof(Vuelo));
            }
        }
    }
    texto.close();
    binario.close();
}