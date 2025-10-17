#include <iostream>
#include <fstream>
#include <ctime>
#include <cstdlib>

//Profe, ya somos poderosos porque aprendímos a portear códigos a Linux con chatGPT :), mira

#ifdef _WIN32 //Si el compilador lee que es Windows pues
    #include <windows.h>
    #define erase(); system("cls");
    #define slip(milisegundos); Sleep(milisegundos * 1000);

#elif _linux_ //AHHH pero si lee Linux
    #include <unistd.h>
    #define erase(); system("clear");
    #define slip(segundos); sleep(segundos);

#endif

using namespace std;

struct Games{
    int pMove[3];
    int sMove[3];
};

struct Tournament{
    int mScore;
    int sScore;
    int numGames;
    char pName[100];
    char sName[100];
};

void imprimirMenu();

void setColor(int);

void wait(int);

void frames(int); 

void playPvP();

void playMachine();

bool question();

int main(){
    imprimirMenu();
}

void setColor(int n){
    switch(n){
        case 0: cout << "\033[38;2;139;184;214m"; break; //Azul cielo
        
        case 1: cout << "\033[38;2;202;190;207m"; break; //Rosado
        
        case 2: cout << "\033[38;2;139;214;174m"; break; //Verde
        
        case 3: cout << "\033[38;2;213;20;13m"; break; //Rojo
        
        case 4: cout << "\033[38;2;171;149;196m"; break; //Cian
    }
}

void imprimirMenu(){
    
    for(int i = 1; i < 10; i++){
        erase();
        setColor(2);
        cout << R"(
         ____ ___ _____ ____  ____      _      ____   _    ____  _____ _        ___  
        |  _ \_ _| ____|  _ \|  _ \    / \    |  _ \ / \  |  _ \| ____| |      / _ \ 
        | |) | ||  _| | | | | |) |  / _ \   | |) / _ \ | |) |  _| | |     | | | |
        |  _/| || || || |  _ <  / ___ \  |  __/ ___ \|  __/| || |___  | |_| |
        |||||/||\\// _ \\ || //   \\|   |||  \_/ 

                           _____ ___    ____________      _
                          |_   | _|  | | ____|  _ \    / \                                           
                            | |  | |_  | |  | | |) |  / _ \                                          
                            | |  | | || | ||  _ <  / ___ \                                         
                            || |\/||| \\//   \\                                        
               )";
    
        setColor(i % 5);
        cout << R"(

        $$\   $$\ $$\    $$$$$$$$\ $$$$$$\ $$\      $$\  $$$$$$\ $$$$$$$$\ $$$$$$$$\ 
        $$ |  $$ |$$ |   \$$  _|\$$  |$$$\    $$$ |$$  __$$\\_$$  __|$$  _____|
        $$ |  $$ |$$ |      $$ |     $$ |  $$$$\  $$$$ |$$ /  $$ |  $$ |   $$ |      
        $$ |  $$ |$$ |      $$ |     $$ |  $$\$$\$$ $$ |$$$$$$$$ |  $$ |   $$$$$\    
        $$ |  $$ |$$ |      $$ |     $$ |  $$ \$$$  $$ |$$  __$$ |  $$ |   $$  __|   
        $$ |  $$ |$$ |      $$ |     $$ |  $$ |\$  /$$ |$$ |  $$ |  $$ |   $$ |      
        \$$$$$$  |$$$$$$$$\ $$ |   $$$$$$\ $$ | \_/ $$ |$$ |  $$ |  $$ |   $$$$$$$$\ 
         \/ \|\|   \|\|     \|\|  \|  \|   \|
                                                                                    
                                                                                    
                )";
            slip(0.8);
        }
    cout << endl << endl << "\t\t | Estamos cargando todos los objetos |";

    do{
        wait(6);
        erase();
        setColor(0);

        cout << endl
            << "\t\t|+--- SUPER PIEDRA PAPEL TIJERA LAGARTO SPOK Z ---+" << endl
            << "\t\t|              -> MENU PRINCIPAL <-               |" << endl
            << "\t\t+-------------------------------------------------+" << endl
            << "\t\t|                                                 |" << endl
            << "\t\t| 1)      JUGAR                                   |" << endl
            << "\t\t| 2)      MIRAR TORNEOS ANTERIORES                |" << endl
            << "\t\t| 3)      SALIR DEL JUEGO                         |" << endl
            << "\t\t|                                                 |" << endl
            << "\t\t+-------------------------------------------------+" << endl
            << "\t\t|    TU RESPUESTA ----> ";

    } while(question());

    setColor(3);
    cout << endl << endl << "\t\tGRACIAS POR JUGAR!";
}

void wait(int n){

    for(int i = 0; i < n; i++){
        slip(0.35);
        cout << " . ";
    }
}

bool question(){
    int option;
    cin >> option;

    switch(option){
        case 1:{ //JUGAR
            setColor(1);
            int option;
            erase();
            cout << endl
                << "\t\t|+--- SUPER PIEDRA PAPEL TIJERA LAGARTO SPOCK  ---+" << endl
                << "\t\t|                  -> JUGAR <-                    |" << endl
                << "\t\t+-------------------------------------------------+" << endl
                << "\t\t|                                                 |" << endl
                << "\t\t| 1)      JUGAR CONTRA LA MAQUINA                 |" << endl
                << "\t\t| 2)      JUGAR PVP                               |" << endl
                << "\t\t| 3)      SALIR AL MENU PRINCIPAL                 |" << endl
                << "\t\t|                                                 |" << endl
                << "\t\t+-------------------------------------------------+" << endl
                << "\t\t|    TU RESPUESTA ----> ";
            cin >> option;

            switch(option){
                case 1: playMachine(); break;
                case 2: playPvP(); break;
                default: return false; break;
            }


        } break;

        case 2: //mirar_puntuaciones
            break; 
        case 3: return false; break;

        default:

        while(option > 3 or option < 0){

            setColor(3);
            cout << endl << endl << "Valor incorrecto. Ingrese su valor --> ";
            setColor(0);
            cin >> option;
            if(option == 3) break;
        }
    }
}

void playPvP(){
    setColor(1);
    char jugada[10] = "         ";
    erase();
    int numGames;
    Tournament tournament;

    cout << endl
         << "\t\t+-------------------------------------------------+" << endl
         << "\t\t|      INDICA EL NUMERO DE PARTIDAS A JUGAR       |" << endl
         << "\t\t+-------------------------------------------------+" << endl
         << "\t\t|    TU RESPUESTA ----> ";
    cin >> numGames;

    tournament.numGames = numGames;
    Games games[numGames];

    for(int i = 0; i < numGames; i++){

        for(int j = 0; j < 3; j++){
            
            cout << endl
                 << "\t\t+-------------------------------------------------+" << endl
                 << "\t\t|     PARA JUGAR RECUERDA PONER EN MINUSCULA      |" << endl
                 << "\t\t|                    'piedra'                     |" << endl
                 << "\t\t|                    'papel'                      |" << endl
                 << "\t\t|                    'tijera'                     |" << endl
                 << "\t\t|                    'lagarto'                    |" << endl
                 << "\t\t|                    'spock'                      |" << endl
                 << "\t\t+-------------------------------------------------+" << endl
                 << "\t\t|    TU RESPUESTA ----> ";
            cin >> jugada;
            wait(3);

            while(jugada != "piedra" && jugada != "papel" &&
                  jugada != "tijera" && jugada != "lagarto" && jugada != "spock"){
                setColor(3);
                cout << "\t\t |Respuesta incorrecta." << endl
                     << "\t\t |TU RESPUESTA ----> ";
                cin >> jugada;
            }
        }
    }
}

void playMachine(){

}

void frames(int n){

    switch (n){
    case 0: 
        cout << R"(

         @@@@@@@@@@@@@@@@@@@@@@@@
       @@@*====================+%@
      @*                        :#@
      @-  *@@@@@@@@@@@@@@@@@@@%:  +@
     @@#  :=*@@@@@=  -@
      @@%=:                :%@@@@+  :%@
       @@@@@@@@@@@@@@@@@*  =@@@@@@#:  *@
           @@@@%%%%%%%%#  :%@@@@@@@@:  +@
          @@*             %@@@@@@@@@@-  -@
         @@-  -%@@@@@@@@@@@@@@@@@@@@@@#  :%@
         @%: :@@@@@@@@@@@@@@@@@@@@@@@@@%: :#@
         @@=             :=*@@@@@@@@@@@@#: =@
          @@@%#########*=:    :+@@@@@@@@%: -@
            @@@@@@@@@@@@@@@%*-  =@@@@@@@%: -@
                          @@@*  -+++++++=: -@
                           @@*             -@
                            @@@@@@@@@@@@@@@@@

        )";
        break;

    case 1:
        cout << R"(          
          
                 @@@@@@@@@@@@@@@@                 
             @@@@@@@%#*    *#%@@@@@@@             
          @@@@%*                  #%@@@@          
        @@@@                         *@@@@        
      @@@%                              %@@@      
    @@@@               *                  @@@@    
   %@@*             ##*@@                  #@@%   
  @@@*             %@@ @@#      @@#         *@@@  
 @@@*              %@@%@%     @@@          #@@@ 
 @@%               #@@##@@    #@@##@#         @@@@
@@@                #@@%#@@*   @@@*@@           @@@
@@#                @@%@@#  @@@ %@%           %@@
@@*                @@@%@% %@@*#@@            #@@
@@*                *@@%@@@@@@@# @@%            #@@
@@*                @@@@@@@@@@@@@@            #@@
@@%      @@@@%     *@@@@@@@@@@@@@@             %@@
@@@        @@@@@#  %@@@@@@@@@@@@@@             @@@
@@@%         @@@@@@@@@@# %@@@@@@@%            @@@@
 @@@*         %@@@@@@@@@@ #@@@@@@%           #@@@ 
  @@@*         @@@@@@@@@@@@@@@@*          #@@@  
   @@@#          %@@@@@@@@%@@@@@           %@@@   
    @@@@           %@@@@@@@@@@@           @@@@    
      @@@%          @@@@@@@@@@%         %@@@      
        @@@@*       %@@@@@@@@@%      *@@@@@       
         @@@@@@#    %@@@@@@@@@%   #@@@@@@         
             @@@@@@@@@@@@@@@@@@@@@@@@@            
                %@@@@@@@@@@@@@@@@%    
                                                  
        )";
        break;

    case 2:
        cout << R"(

       @@@@@@@@@@                                 
     @@@@@@@@@@@@@@@@%                            
    @@@@@@    @@@@@@@@@@@@@                       
    @@@@          @@@@@@@@@@@@@@@@@@@@@@@@@@      
    @@@@%              @@@@@@@@@@@@@@@@@@@@@@@@@  
     @@@@@@                 %@@@@@@@@@@@@@@@@@@@@@
        @@@@@@@              @@@%           @@@@@
            @@@@@@@@@        @@@              @@@@
                @@@@@@@@@   @@                @@@@
  @@@@@@@@@@@@@@@@@@@@@@@   @@                @@@@
@@@@@                       @@     @@         @@@@
@@@@                        @@     @@         @@@@
@@@@@                   @@@@@@     @@         @@@@
@@@@@@@@@@@@@@@@@@@@@@@@@%  @@     @@         @@@@
  @@@@@@@@@@@@@@@@@@@@       @@   @@@         @@@@
     @@@@@@@@@@@@@@          @@@@@%@@@        @@@@
              @@@@%      %@@@@%      @@       @@@@
              @@@@@@%%@@@@@          @@       @@@@
               %@@@@@@@           @@@%        @@@@
                 @@@@@        @@@@@@     %@@@@@@@@
                  @@@@@   %@@@@@  %@@@@@@@@@@@@@@ 
                  %@@@@@@@@@@@@@@@@@@@@@@@@@@%    
                    @@@@@@@@@@@@@@@@@@@           

        )";
        break;
    
    case 3:
        cout << R"(

                        @@@@@@@
                      @@@+   *@@@@@@@@
                @@@@@@@-       -    *@@@
               @@%-      -@@%         #@@@@@@
               @+        =@@@   #@@=       +@@
               @   #@@=  =@@@   #@@+         @@
               @   %@@+  =@@@   #@@+   @@@   *@
               @   %@@+  =@@@   #@@+   @@@   *@
               @   %@@+  =@@@   #@@+   @@@   *@
               @   %@@+  =@@@   #@@+   @@@   *@
               @   %@@+  =@@@   #@@+   @@@   *@
       @@@@@@@@@   %@@@@@@@@@@@@@@@@@@@@@@   *@
     @@@     =@@   %@@@@@@@@@@@@@@@@@@@@@@   *@
    @@*            %@@@@@@@@@@@@@@@@@@@@@@   *@
    @%   %@@@-     %@@@@@@@@@@@@@@@@@@@@@@   *@
    @@    %@@@@=   %@@@@@@@@@@@@@@@@@@@@@@   *@
     @@-    @@@@@# %@@@@@@@@@@@@@@@@@@@@@*   @@
      @@@    -@@@@@@@@@@@@@@@@@@@@@@@@@@@   +@
        @@@     @@@@@@@@@@@@@@@@@@@@@@@%   -@@
          @@@     *@@@@@@@@@@@@@@@@@@@-   =@@
            @@@-      +@@@@@@@@@@@%-     %@@
              @@@@=                   +@@@@
                 @@@@@%-        -@@@@@
                     @@@@@@@@@@@@@@@

        )";
        break;

    case 4: 
        cout << R"(

          @@@@@@@@@
        @@@       @@@@@@@@@@@@@@
       @@   @@@@@          @@%%@@@@
       @   @@@@@@   @@@@@         @@
      @@  @@@@@@%  @@@@@@  %@@@@   @@@@@@@
      @%  @@@@@@  @@@@@@%  @@@@@%        @@
      @%  @@@@@%  @@@@@@  @@@@@@   @@@@   @
      @   @@@@@   %% %%@  @@@@@%  @@@@@@  @
      @   @%              @@@@@  %@@@@@@  @
    @@@      @@@@@@@@@@@   @@@   @@@@@    @
   @@%   @@@@@@@@@@@@@@@%  @@@  %@@@@     @
   @   @@@@@@@@@@@@@@@@%        @@@@@    @@
   @  %@@@@@@@@@@@%      %   %    %      @
   @  %@@@@@@@@     %%@@@@@@@@@@%   %@  %@
   @   @@@@@@@  %@@@@@@@@@@@@@@@@@@@@@  @@
   @@  %@@@@@@%@@@@@@@@@@@@@@@@@@@@@@  %@
    @@   @@@@@@@@@@@@@@@@@@@@@@@@@@@@  @@
     @@   @@@@@@@@@@@@@@@@@@@@@@@@@@  %@
      @@%   @@@@@@@@@@@@@@@@@@@@@@@  %@@
       @@@%   @@@@@@@@@@@@@@@@@@@   @@@
         @@@%  @@@@@@@@@@@@@@@@   %@@
           @@  @@@@@@@@@@@@@@@@  @@@
           @@  @@@@@@@@@@@@@@@@  @
           @%  %%%%%%%%%%%%%%%%  @
           @@                    @
            @@@@@@@@@@@@@@@@@@@@@@

    )";
        break;
    }
}