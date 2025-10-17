#include <iostream>

using namespace std;

//Declaracion prototipo de las funciones
void llenarArray(double arr[], int n);
double calcularNotasEstudiantes(double a, double b, double c, double d, double f);
double encontrarPeorNota(double arr[], int n);
double encontrarMejorNota(double arr[], int n);
double promedioGeneral(double arr[], int n);
int estudiantesPerdieron(double arr[], int n);
void mensajeFinal(double arr[], int n);
void ordenarArreglo(double arr[], int n);


int main(){

        int maximoEstudiantes=50;

        cout<<"Bienvenido al sistema de notas de la universidad Javeriana\n";
        cout<<"Porfavor ingrese las notas en este orden: \n";
        cout<<"\t 1) Primer parcial (10%)\n";
        cout<<"\t 2) Segundo parcial (15%)\n";
        cout<<"\t 3) Tercer parcial (25%)\n";
        cout<<"\t 4) Taller (5%)\n";
        cout<<"\t 5) Trabajo en clase (45%)\n";



        double calificaciones[maximoEstudiantes];

        llenarArray(calificaciones, maximoEstudiantes);
        double menorNota= encontrarPeorNota(calificaciones, maximoEstudiantes);
        double mejorNota= encontrarMejorNota(calificaciones, maximoEstudiantes);
        double promedio= promedioGeneral(calificaciones, maximoEstudiantes);
        int perdieron= estudiantesPerdieron(calificaciones, maximoEstudiantes);
        mensajeFinal(calificaciones, maximoEstudiantes);

        //Mostramos primero el mensaje final y luego las ordenamos, de este modo las notas se muestran en el orden que se ingresaron.
        ordenarArreglo(calificaciones, maximoEstudiantes);

        cout<<endl;

        cout<<"Calificaciones organizadas: ";
        for(int i=0; i<maximoEstudiantes; i++){
            if(calificaciones[i]>-1){
                cout<<endl<<calificaciones[i];
            }
        }
        cout<<endl;
        cout<<endl<<"Mejor calificacion: "<<mejorNota;
        cout<<endl<<"Peor calificacion: "<<menorNota;
        cout<<endl<<"Promedio general: "<<promedio;
        cout<<endl<<"Estudiantes que perdieron la materia:"<<perdieron;

}

/*
    La funcion solicita las notas correspondientes a cada uno de los puntos, posteriormente
    los pasa por otra funcion que calcula el promedio con los parametros establecidos
    y finalmente pregunta si agrega otro estudiante, en caso de que no termina el ciclo.

*/
void llenarArray(double arr[], int n){
    for(int i=0; i<n; i++){

        double primerParcial=-1, segundoParcial=-1, tercerParcial=-1, taller=-1, trabajoClase=-1;
        int seguir;

        while(primerParcial<0.0 || 5.0<primerParcial){
            cout<<endl<<"\t 1) Primer parcial:";
            cin>>primerParcial;
        }

        while(segundoParcial<0.0 || 5.0<segundoParcial){
            cout<<endl<<"\t 2) Segundo parcial:";
            cin>>segundoParcial;
            }

        while(tercerParcial<0.0 || 5.0<tercerParcial){
            cout<<endl<<"\t 3) Tercer parcial:";
            cin>>tercerParcial;
        }

        while(taller<0.0 || 5.0<taller){
            cout<<endl<<"\t 4) Taller:";
            cin>>taller;
        }

        while(trabajoClase<0.0 || 5.0<trabajoClase){
            cout<<endl<<"\t 5) Trabajo en clase:";
            cin>>trabajoClase;
        }

        arr[i]=calcularNotasEstudiantes(primerParcial, segundoParcial, tercerParcial, taller, trabajoClase);

        cout<<endl<<"Quiere agregar otro estudiante? (1) si   (2) no  :";
        cin>>seguir;

        if (seguir==2){
            for (int j=i+1; j<n; j++){
                arr[j]=-1;
            }
            break;
        }
    }
}


//Funcion para calcular el promedio de cada estudiante considerando el valor porcentual de cada nota.
double calcularNotasEstudiantes(double a, double b, double c, double d, double f){
    double notaFinal= a*0.1+b*0.15+c*0.25+d*0.05+f*0.45;
    return notaFinal;
}

//Esta funcion encuentra la peor nota sin contar las casillas vacioas.
double encontrarPeorNota(double arr[], int n){
    double peorNota=arr[0];
    for (int i=1; i<n; i++){
        if(-1<arr[i]){
            if(arr[i]<peorNota){
                peorNota=arr[i];
            }
        }
    }
    return peorNota;
}

//Esta funcion encuentra la mejor nota sin contar las casillas vacias.
double encontrarMejorNota(double arr[], int n){
    double mejorNota=arr[0];
    for (int i=1; i<n; i++){
        if(mejorNota<arr[i]){
            mejorNota=arr[i];
            }
        }
    return mejorNota;
}

//Esta funcion calcula el promedio global de todos los estudiantes (sin contar casillas vacias)
//suma las notas de todos los estudiantes y a su vez los cuenta para poder obtener el promedio
double promedioGeneral(double arr[], int n){
    int cantidadEstudiantes=0;
    double promedio=0.0;
    for (int i=0; i<n; i++){
        if(-1<arr[i]){
            cantidadEstudiantes++;
            promedio+=arr[i];
        }
    }
    promedio/=cantidadEstudiantes;
    return promedio;
}

//Esta funcion cuenta la cantidad de estudiantes que perdieron la materia, estableciendo 3 como nota minima para pasar
int estudiantesPerdieron(double arr[], int n){
    int perdieron=0;
    for (int i=0; i<n; i++){
        if(-1<arr[i] && arr[i]<3){
                perdieron++;
        }
    }
    return perdieron;
}

//Muestra el mensaje final para no sobrecargar el main de cosas :D
void mensajeFinal(double arr[], int n){
    for (int i = 0; i < n; i++) {
        if(-1<arr[i]){
        double nota = arr[i];
        cout << "Nota: " << nota << " - ";

        if (nota >= 0.0 && nota < 1.0)
            cout<<"MUY MAL";
        else if (nota >= 1.0 && nota < 2.0)
            cout<<"Toca esforzarse más";
        else if (nota >= 2.0 && nota < 3.0)
            cout<<"Casi pasas";
        else if (nota >= 3.0 && nota < 4.0)
            cout<<"Felicitaciones";
        else if (nota >= 4.0 && nota <= 5.0)
            cout<<"Perfecto";
        cout<<endl;
        }
    }
}

//Un bubblesort sencillo.
void ordenarArreglo(double arr[], int n){
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] < arr[j + 1]) {
                double temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
