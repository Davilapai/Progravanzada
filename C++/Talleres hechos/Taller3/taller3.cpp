#include <iostream>
#include <string.h>
#include <fstream>

using namespace std;

struct Empleado {
    char nombre[20];
    int edad;
    bool nocturno;
    int indicestrabajo[5];
};

struct Trabajo{
    int id;
    char nombre[20];
    int pago;
};

struct Resumen{
    char nombre[20];
    long pagoTotal;
    int totalTrabajos;
};

//Prototipos
int cantidadEmpleadosArchivo(string nombreArchivo);
int cantidadTrabajosArchivo(string nombreArchivo);
void leerArchivoEmpleados(string nombreArchivo, Empleado arr[], int n);
void leerArchivoTrabajos(string nombreArchivo, Trabajo arr[], int n);
void imprimirEmpleados(Empleado arr[], int n);
void imprimirTrabajos(Trabajo arr[], int n);
void imprimirResumen(Resumen resumen);
int cantidadTrabajos(Empleado emp);
Trabajo buscarTrabajo(int id, Trabajo trabajos[], int n);
long calcularPagoTotal(Empleado emp, Trabajo arr[], int n);
Resumen generarResumen(Empleado emp, Trabajo arr[], int n);
void agregarResumen(string nombreArchivo, Resumen res);

int main() {
    Empleado empleados[50];
    Trabajo trabajos[50];

    int cantidadEmp = cantidadEmpleadosArchivo("empleados.dat");
    int cantidadTrab = cantidadTrabajosArchivo("trabajos.dat");

    leerArchivoEmpleados("empleados.dat", empleados, cantidadEmp);
    leerArchivoTrabajos("trabajos.dat", trabajos, cantidadTrab);

    Resumen resumenes[cantidadEmp];

    for(int i = 0; i < cantidadEmp; i++){
        resumenes[i] = generarResumen(empleados[i], trabajos, cantidadTrab); //generar resumen para cada empleado
    }

    for(int i = 0; i < cantidadEmp; i++){
        if(empleados[i].nocturno){
            agregarResumen("nocturno.dat", resumenes[i]);
        } else {
            agregarResumen("diurno.dat", resumenes[i]);
        }
    }
    return 0;
}

//La funcion retorna la cantidad de empleados en el archivo
int cantidadEmpleadosArchivo(string nombreArchivo){
    ifstream file(nombreArchivo, ios::binary | ios::ate);

    if (!file.is_open()){
        exit(1);
    }

    int tamanioArchivo = file.tellg();

    file.close();

    return tamanioArchivo / sizeof(Empleado);

}

//La funcion retorna la cantidad de trabajos en el archivo
int cantidadTrabajosArchivo(string nombreArchivo){
    ifstream file(nombreArchivo, ios::binary | ios::ate);
    
    if (!file.is_open()){
        exit(1);
    }

    int tamanioArchivo = file.tellg();

    file.close();

    return tamanioArchivo / sizeof(Trabajo);
}

//La funcion lee los empleados del archivo y los guarda en el arreglo
void leerArchivoEmpleados(string nombreArchivo, Empleado arr[], int n){
    ifstream file(nombreArchivo, ios::binary);

    if (!file.is_open()){
        exit(1);
    }

    for(int i = 0; i < n; i++){
        file.read((char*)& arr[i], sizeof(Empleado));
    }

    file.close();

}

//La funcion lee los trabajos del archivo y los guarda en el arreglo
void leerArchivoTrabajos(string nombreArchivo, Trabajo arr[], int n){
    ifstream file(nombreArchivo, ios::binary);
    
    if (!file.is_open()){
        exit(1);
    }

    for(int i = 0; i < n; i++){
        file.read((char*)& arr[i], sizeof(Trabajo));
    }
    file.close();
}

//La funcion imprime los empleados del arreglo y ademas si son nocturnos o no
void imprimirEmpleados(Empleado arr[], int n){
    for(int i = 0; i < n; i++){
        cout<<"Empleado " << i+1 << endl;
        cout<<"Nombre: " << arr[i].nombre << endl;
        cout<<"Edad: " << arr[i].edad << endl;
        cout<<"Nocturno: " << (arr[i].nocturno ? "Si" : "No") << endl;
        cout<<"Indices de trabajo: ";
    }
}

//La funcion imprime los trabajos del arreglo
void imprimirTrabajos(Trabajo arr[], int n){
    for(int i = 0; i < n; i++){
        cout<<"Trabajo " << i+1 << endl;
        cout<<"Nombre: " << arr[i].nombre << endl;
        cout<<"ID: " << arr[i].id << endl;
        cout<<"Pago: " << arr[i].pago << endl;
    }
}

//La funcion imprime el resumen
void imprimirResumen(Resumen res){
        cout<<"Nombre: "<<res.nombre<<endl;
        cout<<"Pago Total: "<<res.pagoTotal<<endl;
        cout<<"Total Trabajos: "<<res.totalTrabajos<<endl;
}

//La funcion retorna la cantidad de trabajos que tiene un empleado, sin contar los -1 que serian invalidos
int cantidadTrabajos(Empleado emp){
    int contador = 0;

    for(int i = 0; i < 5; i++){
        if(emp.indicestrabajo[i] != -1){
            contador++;
        }
    }
    return contador;
}

//La funcion busca un trabajo por su id y lo retorna, si no lo encuentra retorna un trabajo vacio
Trabajo buscarTrabajo(int id, Trabajo trabajos[], int n){
    for(int i = 0; i < n; i++){
        if(trabajos[i].id == id){
            return trabajos[i];
        }
    }
    Trabajo nanai{}; // Retorna un trabajo vacio si no se encuentra
    return nanai;
}

//La funcion calcula el pago total de un empleado, sumando los pagos de los trabajos que tiene asignados
//y aplicando el recargo del 50% si es nocturno
long calcularPagoTotal(Empleado emp, Trabajo arr[], int n){
    long total = 0;

    for(int i = 0; i < 5; i++){
        if(emp.indicestrabajo[i] != -1){
            Trabajo trabajito=buscarTrabajo(emp.indicestrabajo[i], arr, n);
            total+=trabajito.pago;
        }
    }

    if(emp.nocturno){
        total=total * 1.5;
    }

    return total;
}

//La funcion genera un resumen para un empleado, calculando su pago total y la cantidad de trabajos que realizó
Resumen generarResumen(Empleado emp, Trabajo arr[], int n){
    Resumen resumensito;

    strcpy(resumensito.nombre,emp.nombre);

    resumensito.pagoTotal=calcularPagoTotal(emp, arr, n);

    resumensito.totalTrabajos=cantidadTrabajos(emp);
    
    return resumensito;
}

//La funcion agrega un resumen al archivo correspondiente, ya sea diurno o nocturno
void agregarResumen(string nombreArchivo, Resumen res){
    ofstream file(nombreArchivo, ios::binary | ios::app);

    if (!file.is_open()){
		exit(1);
    }

    file.write((char*)& res, sizeof(Resumen));

    file.close();
}






