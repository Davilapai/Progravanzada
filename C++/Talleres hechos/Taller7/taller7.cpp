#include <iostream>
#include <string.h>
#include <cstdlib>

using namespace std;

struct Direccion {
	int carrera;
	int calle;
	int numero;
};

void reemplazarHTML(char* cadena) {
	char temp[500];
	char* ptr = cadena;
	char* pos;

	while((pos = strstr(ptr, "&euro")) != NULL){
		strncat(temp, ptr, pos-ptr);
		strcat(temp, "$"); //Profe no me cargo el simbolo del euro
		ptr = pos + 5;
	}
	strcat(temp, ptr);
	strcpy(cadena, temp);
}

Direccion* llenarDireccion(char* cadena) {
	Direccion* direccioncita=new Direccion;
	char * token;
	token =  strtok(cadena, "#- ");

	if(strcmp(token,"Carrera") == 0){
		token = strtok(NULL, "#- ");
		direccioncita->carrera=atoi(token);

		token = strtok(NULL, "#- ");
        direccioncita->calle=atoi(token);
		
		token = strtok(NULL," #-");
		direccioncita->numero=atoi(token);
		
	}else{
		token = strtok(NULL, "#- ");
		direccioncita->calle=atoi(token);

		token = strtok(NULL, "#- ");
        direccioncita->carrera=atoi(token);
		
		token = strtok(NULL," #-");
		direccioncita->numero=atoi(token);
	}


	return direccioncita;
}

int encontrarCadenas(char secuencia[200], char  matriz[][100]) {
	int contador=0;
	char* inicio = secuencia;

	while((inicio = strstr(inicio, "AUG")) != NULL){
		char* fin = strstr(inicio, "UAG");
		if(fin != NULL){
			int longitud = (fin+3)-inicio;
			strncpy(matriz[contador], inicio, longitud);
			matriz[contador][longitud]='\0';
			contador++;
			inicio = fin+3;
		}else{
			break;
		}
	}
	return contador;
}

void imprimirDireccion(Direccion* direccion) {
	cout << "Calle:" << direccion->calle << endl;
	cout << "Carrera:" << direccion->carrera << endl;
	cout << "Numero:" << direccion->numero << endl;
}

int main(){
    // EJERCICIO 1. DIRECCIONES
    char direccion1[100] = "Carrera 127#39-61";
	Direccion* respuesta1 = llenarDireccion(direccion1);
	imprimirDireccion(respuesta1);
	delete respuesta1;
	/*
	Calle:39
    Carrera:127
    Numero:61
	*/

	char direccion2[100] = "Calle 12#11-99";
	Direccion* respuesta2 = llenarDireccion(direccion2);
	imprimirDireccion(respuesta2);
	delete respuesta2;
	/*
    Calle:12
    Carrera:11
    Numero:99
	*/

	// EJERCICIO 2. HTML

	char html[200] = "Usted debe pagar un total de &euro15.00 y sus vueltas son &euro1.00";
	reemplazarHTML(html);
	cout << html << endl;
	// Usted debe pagar un total de �15.00 y sus vueltas son �1.00

	// EJERCICIO 3. ADN
	char secuencia[100] = "AUGAAUUGGCCUAGAAACCCCAAAAUGAAAAAUAGG";
	char matriz [20][100];
	int cantidad = encontrarCadenas(secuencia, matriz);
	cout << "Cantidad " << cantidad << endl;
	for(int i = 0; i < cantidad; i++) {
		cout << *(matriz + i) << endl;
	}
	/*
	AUGAAUUGGCCUAG
    AUGAAAAAUAG
	*/

	return 0;
}
