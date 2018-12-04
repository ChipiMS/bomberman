package bomberman;
class Paso {
    int bombermanI, bombermanJ, pasos, g, f;
    char estado;
    Ataque ataque;
    boolean actual;
    Paso anterior;
    Paso(int bombermanI, int bombermanJ, char estado, int pasos, Ataque ataque, int g, int f, Paso anterior){
        actual = true;
        this.anterior = anterior;
        this.ataque = ataque;
        this.bombermanI = bombermanI;
        this.bombermanJ = bombermanJ;
        this.estado = estado;
        this.f = f;
        this.g = g;
        this.pasos = pasos;
    }
}
