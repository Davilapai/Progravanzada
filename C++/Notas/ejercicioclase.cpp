#include <iostream>
#include <conio.h>
#include <windows.h>
using namespace std;

const int width = 20;
const int height = 20;
int x, y, fruitX, fruitY, score;
int tailX[100], tailY[100];
int nTail;
enum eDirection { STOP = 0, LEFT, RIGHT, UP, DOWN };
eDirection dir;
bool gameOver;

HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

void gotoxy(int x, int y) {
    COORD pos = { (SHORT)x, (SHORT)y };
    SetConsoleCursorPosition(hConsole, pos);
}

void Setup() {
    gameOver = false;
    dir = STOP;
    x = width / 2;
    y = height / 2;
    fruitX = rand() % width;
    fruitY = rand() % height;
    score = 0;
}

void DrawWalls() {
    for (int i = 0; i <= width + 1; i++) {
        gotoxy(i, 0); cout << "#";
        gotoxy(i, height + 1); cout << "#";
    }
    for (int i = 1; i <= height; i++) {
        gotoxy(0, i); cout << "#";
        gotoxy(width + 1, i); cout << "#";
    }
}

void DrawInitial() {
    DrawWalls();
    gotoxy(x + 1, y + 1); cout << "O"; // cabeza
    gotoxy(fruitX + 1, fruitY + 1); cout << "F"; // fruta
}

void Input() {
    if (_kbhit()) {
        switch (_getch()) {
            case 'a': dir = LEFT; break;
            case 'd': dir = RIGHT; break;
            case 'w': dir = UP; break;
            case 's': dir = DOWN; break;
            case 'x': gameOver = true; break;
        }
    }
}

void Logic() {
    int prevX = tailX[0], prevY = tailY[0];
    int prev2X, prev2Y;
    tailX[0] = x;
    tailY[0] = y;

    for (int i = 1; i < nTail; i++) {
        prev2X = tailX[i]; prev2Y = tailY[i];
        tailX[i] = prevX; tailY[i] = prevY;
        prevX = prev2X; prevY = prev2Y;
    }

    // borrar última cola
    if (nTail > 0) {
        gotoxy(prevX + 1, prevY + 1);
        cout << " ";
    }

    // mover cabeza
    switch (dir) {
        case LEFT: x--; break;
        case RIGHT: x++; break;
        case UP: y--; break;
        case DOWN: y++; break;
        default: break;
    }

    // rebote en paredes
    if (x >= width) x = 0; else if (x < 0) x = width - 1;
    if (y >= height) y = 0; else if (y < 0) y = height - 1;

    // colisión con cola
    for (int i = 0; i < nTail; i++)
        if (tailX[i] == x && tailY[i] == y)
            gameOver = true;

    // dibujar nueva cabeza
    gotoxy(x + 1, y + 1);
    cout << "O";

    // actualizar cola
    for (int i = 0; i < nTail; i++) {
        gotoxy(tailX[i] + 1, tailY[i] + 1);
        cout << "o";
    }

    // comer fruta
    if (x == fruitX && y == fruitY) {
        score += 10;
        fruitX = rand() % width;
        fruitY = rand() % height;
        nTail++;
        gotoxy(fruitX + 1, fruitY + 1);
        cout << "F";
    }

    // mostrar puntuación
    gotoxy(0, height + 3);
    cout << "Score: " << score << "    ";
}

int main() {
    Setup();
    DrawInitial();
    while (!gameOver) {
        Input();
        Logic();
        Sleep(100);
    }
    gotoxy(0, height + 5);
    cout << "Game Over!" << endl;
    return 0;
}
