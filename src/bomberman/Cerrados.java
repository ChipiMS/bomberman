package bomberman;
class Cerrados {
    Paso nodos[][][];
    Cerrados(char[][] mapa){
        nodos = new Paso[mapa.length][mapa[0].length][3];
    }
    void cierra(int bombermanI, int bombermanJ, char estado, int pasos, Ataque ataque, int g, int f, Paso anterior){
        nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] = new Paso(bombermanI, bombermanJ, estado, pasos, ataque, g, f, anterior);
    }

    int esta(int bombermanI, int bombermanJ, char estado){
        if(nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] != null){
            return nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)].g;
        }
        return -1;
    }

    void abre(int bombermanI, int bombermanJ, char estado){
        nodos[bombermanI][bombermanJ][Busqueda.llaveEstado(estado)] = null;
    }
}
