#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;

struct Vertice {
    float x;
    float y;
};

struct Poligono {
    Vertice* vertices;   // arreglo dinámico de vértices
    int cantidad;        // número de vértices
    bool** matriz;       // matriz dinámica de conexiones
};

// Declaracion de funciones
Poligono* cargarArchivo();
void mostrarVertices(Poligono* poligono);
void mostrarMatriz(Poligono* poligono);
void unirSecuencial(Poligono* poligono);
void unirManual(Poligono* poligono);
void generarArchivo(Poligono* poligono);
void salir(Poligono* poligono);


int main(){
    int opcion; 
    Poligono* poligono = nullptr;
    
    do {
        cout << "\n--- MENU PRINCIPAL ---\n";
        cout << "1. Cargar archivo de vertices\n";
        cout << "2. Mostrar vertices\n";
        cout << "3. Mostrar matriz\n";
        cout << "4. Unir vertices secuencialmente\n";
        cout << "5. Unir vertices manualmente\n";
        cout << "6. Generar archivo .obj\n";
        cout << "7. Salir\n";
        cout << "Seleccione una opcion: ";
        cin >> opcion;

        if (cin.fail()) {
            cin.clear();                // limpia el estado de error
            cin.ignore(1000, '\n');     // descarta lo que metieron mal
            cout << "Eso no se puede hacer -.-.\n";
            opcion = 0;                 // forzamos un valor inválido
        }

        switch (opcion) {
            case 1: {
                if(poligono!=nullptr){
                    cout<<"Ya cargaste un poligono, sal y vuelve a entrar para cargar otro";
                    break;
                }
                poligono = cargarArchivo(); 
                break;
            }
            case 2: {
                mostrarVertices(poligono); 
                break;
            }
            case 3: {
                mostrarMatriz(poligono); 
                break;
            }
            case 4: {
                unirSecuencial(poligono); 
                break;
            }
            case 5: {
                unirManual(poligono); 
                break;
            }
            case 6: {
                generarArchivo(poligono); 
                break;
            }
            case 7: {
                salir(poligono);
                cout << "Chaolin" << endl;
                break;
            }
            default: {
                cout << "Opcion invalida broski, escoje de nuevo" << endl;
            }
        }
    } while (opcion != 7);
}


Poligono* cargarArchivo() {
    cout << ">>> Cargar archivo de vertices" << endl;
    Poligono* poligono = new Poligono;
    poligono->cantidad = 0;
    poligono->vertices = nullptr;
    poligono->matriz = nullptr;

    cout<<"Ingrese el nombre del archivo con su extension (.txt o .bin): ";
    char nombre[100];
    cin>>nombre;

    char temp[100];
    strcpy(temp,nombre);

    if (strstr(temp, ".txt") == NULL && strstr(temp, ".bin") == NULL) { //si no tiene ninguna de las dos extensiones pailas
        cout << endl << "Extension no reconocida, intentalo nuevamente";
        delete poligono; //pa volver si no detecta
        return nullptr;
    }

    if(strstr(temp,".txt")!=NULL){
        cout<<endl<<"Arhivo de texto reconocido :p";

        ifstream file(nombre);
        if(!file)exit(1);

        while(file.getline(temp,100)){
            int cantidad = poligono->cantidad; //variable auxiliar para no copiar tanto
            Vertice* vertices = new Vertice[cantidad+1]; //Creamos nuevo arreglo dinamico de vertices con 1 hueco extra
            for(int i=0; i<cantidad; i++){ //ciclo para copiar vertices
                *(vertices+i)=*(poligono->vertices+i);
            }

            char* token = strtok(temp,": "); //dividimos la linea leida lo q nos da x
            
            token = strtok(NULL,": "); //en esta division tomamos el valor de x
            (vertices+cantidad)->x = atof(token);

            token = strtok(NULL,": "); //en esta division tomamos y
            token = strtok(NULL,": "); //en esta division tomamos el valor de y
            (vertices+cantidad)->y = atof(token);

            delete[]poligono->vertices; //eliminamos el arreglo viejo
            poligono->vertices = vertices; //reemplazamos por el nuevo
            poligono->cantidad++; //incrementamos la cantidad
        }

        file.close();
        cout<<endl<<"Archivo cargado exitosamente :D";

    }else{
        cout<<endl<<"Archivo binario reconocido :p";

        ifstream file(nombre, ios::binary | ios::ate); //abrimos el archivo al final
        if(!file)exit(1);

        int cantidad = file.tellg()/sizeof(Vertice); //calculamos la cantidad con el tamaño del archivo y el de cada vertica
        file.seekg(0); //volvemos al inicio

        poligono->vertices = new Vertice[cantidad]; //creamos el arreglo con la cantidad de vertices

        file.read((char*)(poligono->vertices), sizeof(Vertice)*cantidad); //copiamos para los vertices
        
        poligono->cantidad = cantidad; //definimos la cantidad de vertices del poligono
        
        file.close();
        cout<<endl<<"Archivo cargado exitosamente :D";
    }

    int matrix = poligono->cantidad; //vamos a crear la matriz cuadrada
    poligono->matriz = new bool*[matrix]; //le damos la cantidad de filas

    for(int i=0; i<matrix; i++){
        *(poligono->matriz+i) = new bool[matrix]; //asignamos la cantidad de columnas
        
        for(int j=0; j<matrix; j++){
            *(*(poligono->matriz+i)+j) = false; //asignamos falso a cada valor
            //va con doble apuntador, el primero me deja acceder a j y el segundo me hace indireccion para definir valor
        }
    }

    return poligono;
}

void mostrarVertices(Poligono* poligono) {
    cout << ">>> Mostrar vertices poligono " << endl;

    if (poligono == nullptr || poligono->cantidad == 0) {
        cout << "No hay vertices pa mostrar" << endl;
        return; //Si es nulo o no hay vertices no muestra nada puej
    }

    for(int i=0; i<poligono->cantidad; i++){
        Vertice* vertice = poligono->vertices+i; //pa facilitar escritura
        cout << endl << "Vertice " << i+1 << ": " << vertice->x << ", " <<vertice->y;
    } //Mostraria tipo Vertice 1: 1.5,2.0
}

void mostrarMatriz(Poligono* poligono) {
    cout << ">>> Mostrar matriz" << endl;

    if(poligono == nullptr){
        cout<<"No hay matriz :c";
        return; //si no hay poligono menos matriz JAJA
    }

    int cantidad = poligono->cantidad;
    for(int i=0; i<cantidad; i++){
        for(int j=0; j<cantidad; j++){
            cout<<((*(*(poligono->matriz+i)+j)) ? 1:0)<<" "; //ternario para imprimir 1 o 0 si es verdadero o falso
        }
        cout<<endl;
    }
}

void unirSecuencial(Poligono* poligono) {
    if(poligono == nullptr){
        cout << "Aun no has cargado ningun poligono";
        return;//lo regresa al menu si no tiene poligono
    }
    cout << ">>> Unir vertices secuencialmente" << endl;
    int cantidad = poligono->cantidad; //simplificar

    for(int i=0; i<cantidad; i++){
        int j=(i+1)%cantidad; //cuando j=cantidad va a servir para conectar el ultimo con el primero
            (*(*(poligono->matriz+i)+j))=true; //convierte la posicion [i][j]
            (*(*(poligono->matriz+j)+i))=true; //convierte la posicion [j][i]
    }
    cout << endl << "Ya quedaron unidos secuencialmente";
}

void unirManual(Poligono* poligono) {
    cout << ">>> Unir vertices manualmente" << endl;

    if(poligono == nullptr){
        cout << "Aun no has cargado ningun poligono";
        return; //esto es pa q no se cierre el programa de una, sino q lo regrese al menu
    }

    cout << endl << "Hola que tal, vamos a conectar vertices (porfavor ingrese el indice empezando en 1(1,2,3....n)) :D";
    int cantidad = poligono->cantidad;
    cout << endl << "Su cantidad actual de vertices es: " <<cantidad;
    cout << endl << "Recuerde que no puede poner un numero mayor a la cantidad actual.";
    
    if(cantidad < 2){
        cout<<endl<<"Lo sentimos, necesitas por lo menos 2 vertices para conectar :c";
        return; //Si no tiene minimo 2 vertices no se puede conectar nada
    }
    
    while(true){
        int ar1 = 0, ar2 = 0;
        do {
            cout << "Ingrese el primer vertice: ";
            cin >> ar1;
            cout << "Ingrese el segundo vertice: ";
            cin >> ar2;

            if (ar1 < 1 || ar2 < 1 || ar1 > cantidad || ar2 > cantidad || ar2 == ar1) {
                cout << "Indices invalidos, intente de nuevo." << endl;
            }
        } while (ar1 < 1 || ar2 < 1 || ar1 > cantidad || ar2 > cantidad || ar2 == ar1);

        (*(*(poligono->matriz+(ar1-1))+(ar2-1)))=true; //convierte la posicion [i][j]
        (*(*(poligono->matriz+(ar2-1))+(ar1-1)))=true; //convierte la posicion [i][j]

        int opcion;
        cout << endl << "Si desea salir presione 1, si quiere conectar otros vertices pulse cualquier tecla: ";
        cin >> opcion;
        if(opcion == 1) break;
         if (cin.fail()) {
            cin.clear();                // limpia el estado de error
            cin.ignore(1000, '\n');     // descarta lo que metieron mal
            opcion = 2;                 // forzamos un valor inválido
        } //rompe el ciclo
   }
}

void generarArchivo(Poligono* poligono) {
    cout << ">>> Generar archivo .obj" << endl;

    if(poligono == nullptr){
        cout << "Aun no has cargado ningun poligono";
        return;//lo regresa al menu si no tiene poligono
    }

    ofstream file("salida.obj");
    if(!file)exit(1);

    file << "# Poligono con vertces y aristas";
    for(int i=0; i<poligono->cantidad; i++){ //escribe los vertices
        file << endl << "v " << (poligono->vertices+i)->x << " " << (poligono->vertices+i)->y << " 0";
    }

    file << endl; //salto de linea para q quede igual a la foto :P

    //aqui tomamos solo la mitad superior sin contar la diagonal porque son paralelas sobre la diagonal
    //evaluamos si es true y si lo es imprimimos la posicion de la fila y de la columna
    for(int i=0; i<poligono->cantidad; i++){
        for(int j=i+1;j<poligono->cantidad;j++){
            if((*(*(poligono->matriz+i)+j))){ 
                file << endl << "l " << i+1 << " " << j+1;
            }
        }
    }
    cout << endl << "Archivo generado";
    file.close();
}

void salir(Poligono* poligono) {
    if (poligono == nullptr) return; //Seguridad segun chatgpt

    delete []poligono->vertices; //borramos arreglo de vertices
    
    for(int i=0; i<poligono->cantidad; i++){
        delete[]*(poligono->matriz+i); //esto es pa borrar los arreglos internos de la matriz
    }

    delete[]poligono->matriz; //pa borrar el arreglo de punteros de filas
    delete poligono; //borramos el poligono y chaolin
}
