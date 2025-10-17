#include <iostream>

using namespace std;

void encontrarTonteras(int arr[], int n);
void encontrarArreglados(int arr[], int n) ;

int main() {
    int arr[] = {0,1,2,4,5};
    int n = sizeof(arr) / sizeof(arr[0]);
    encontrarArreglados(arr, n);
    return 0;
}

void encontrarTonteras(int arr[], int n) {
    int mayor = arr[0];
    int menor = arr[0];
    
    for (int i = 0; i < n; i++) {
        if (arr[i] > mayor) {
            mayor = arr[i];
        }
        if (arr[i] < menor) {
            menor = arr[i];
        }
    }
    cout << "Los números que faltan entre " << menor << " y " << mayor << " son: ";
    for (int i = menor; i <= mayor; i++) {
        bool encontrado = false;
        for (int j = 0; j < n; j++) {
            if (arr[j] == i) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            cout << i << " ";
        }
    }
}    

void encontrarArreglados(int arr[], int n) {
    for (int i = 1; i < n-1; i++) {
        if(arr[i + 1] != arr[i] + 1) {
            cout << i + 1 << " ";
        }  
    }
}
