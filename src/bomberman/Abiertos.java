package bomberman;
class Abiertos{
    Paso nodos[][][];
    Monticulo monticulo;
    Abiertos(char[][] mapa){
        nodos = new Paso[mapa.length][mapa[0].length][3];
        monticulo = new Monticulo(mapa.length*mapa[0].length*3);
    }
    boolean estaVacio() {
        return monticulo.estaVacio();
    }

    Paso sacaPrimero() {
        return monticulo.saca();
    }

    void abre(int bombermanI, int bombermanJ, char estado, int pasos, Ataque ataque, int g, int f, Paso anterior){
        nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] = new Paso(bombermanI, bombermanJ, estado, pasos, ataque, g, f, anterior);
        monticulo.inserta(nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)]);
    }

    int esta(int bombermanI, int bombermanJ, char estado) {
        if(nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] != null){
            return nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)].g;
        }
        return -1;
    }

    void reemplaza(int bombermanI, int bombermanJ, char estado, int pasos, Ataque ataque, int g, int f, Paso anterior){
        nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)].actual = false;
        nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] = new Paso(bombermanI, bombermanJ, estado, pasos, ataque, g, f, anterior);
        monticulo.inserta(nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)]);
    }
}
