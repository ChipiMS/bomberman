package bomberman;
public class Busqueda {
    Lectura entrada;
    Abiertos abiertos;
    Cerrados cerrados;
    boolean metaLograda;
    int n, m, factorPasos, factorManhattan;
    Paso ea;
    char camino[][][];
    Busqueda(Lectura l){
        entrada = l;
        n = entrada.filas-2;
        m = entrada.columnas-2;
        factorPasos = entrada.filas*entrada.columnas*3;
        factorManhattan = entrada.filas+entrada.columnas-6;
        
        metaLograda = false;
        abiertos = new Abiertos(entrada.mapa);
        cerrados = new Cerrados(entrada.mapa);
        abiertos.abre(entrada.bombermanI, entrada.bombermanJ, 's', 0, null, g(0), f(entrada.bombermanI, entrada.bombermanJ, 's', g(0)), null);
        while(!abiertos.estaVacio() && !metaLograda){
            ea = abiertos.sacaPrimero();
            if(ea != null){
                if(esMeta(ea)){
                    metaLograda = true;
                    generaCamino(ea);
                }
                else{
                    analizaSucesor(ea.bombermanI+1, ea.bombermanJ, ea.estado, ea.pasos+1, ea.ataque, ea);
                    analizaSucesor(ea.bombermanI-1, ea.bombermanJ, ea.estado, ea.pasos+1, ea.ataque, ea);
                    analizaSucesor(ea.bombermanI, ea.bombermanJ+1, ea.estado, ea.pasos+1, ea.ataque, ea);
                    analizaSucesor(ea.bombermanI, ea.bombermanJ-1, ea.estado, ea.pasos+1, ea.ataque, ea);
                }
            }
        }
        if(camino != null){
            for(int k = 0; k < camino.length; k++){
                for(int i = 0; i < entrada.filas; i++){
                    for(int j = 0; j < entrada.columnas; j++){
                        System.out.print(camino[k][i][j]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }

    private void analizaSucesor(int bombermanI, int bombermanJ, char estado, int pasos, Ataque ataque, Paso anterior){
        if(entrada.mapa[bombermanI][bombermanJ] != '#'){
            if(estado == 's'){
                if(bombermanI == 1){
                    if(bombermanJ == 1 || bombermanJ == m){
                        estado = 'b';
                    }
                }
                else if(bombermanI == n){
                    if(bombermanJ == 1 || bombermanJ == m){
                        estado = 'b';
                    }
                }
            }
            if(estado == 'b'){
                if(puedeAtacar(bombermanI, bombermanJ)){
                    estado = 'a';
                    ataque = new Ataque(bombermanI, bombermanJ);
                }
            }
            int estaCerrado = cerrados.esta(bombermanI, bombermanJ, estado), estaAbierto = abiertos.esta(bombermanI, bombermanJ, estado), nuevoG;
            if(estaCerrado != -1){
                nuevoG = g(pasos);
                if(nuevoG > estaCerrado){
                    cerrados.abre(bombermanI, bombermanJ, estado);
                    abiertos.abre(bombermanI, bombermanJ, estado, pasos, ataque, nuevoG, f(bombermanI, bombermanJ, estado, nuevoG), anterior);
                }
            }
            else if(estaAbierto != -1){
                nuevoG = g(pasos);
                if(nuevoG > estaCerrado){
                    abiertos.reemplaza(bombermanI, bombermanJ, estado, pasos, ataque, nuevoG, f(bombermanI, bombermanJ, estado, nuevoG), anterior);
                }
            }
            else{
                abiertos.abre(bombermanI, bombermanJ, estado, pasos, ataque, g(pasos), f(bombermanI, bombermanJ, estado, g(pasos)), anterior);
            }
        }
    }

    private boolean esMeta(Paso ea){
        if(ea.estado == 'a' && ea.bombermanI != ea.ataque.i && ea.bombermanJ != ea.ataque.j){
            return true;
        }
        return false;
    }
    
    int f(int bombermanI, int bombermanJ, char estado, int g){
        int valor = 0, bombaCercana, distanciaBomba;
        if(estado == 's'){
            bombaCercana = factorManhattan;
            distanciaBomba = bombermanI-1+bombermanJ-1;
            if(distanciaBomba < bombaCercana){
                bombaCercana = distanciaBomba;
            }
            distanciaBomba = bombermanI-1+m-2-bombermanJ;
            if(distanciaBomba < bombaCercana){
                bombaCercana = distanciaBomba;
            }
            distanciaBomba = n-2-bombermanI+bombermanJ-1;
            if(distanciaBomba < bombaCercana){
                bombaCercana = distanciaBomba;
            }
            distanciaBomba = n-2-bombermanI+m-2-bombermanJ;
            if(distanciaBomba < bombaCercana){
                bombaCercana = distanciaBomba;
            }
            valor = factorManhattan-bombaCercana;
        }
        else if(estado == 'b'){
            valor = factorManhattan*2-Math.abs(entrada.enemigoI-bombermanI)-Math.abs(entrada.enemigoJ-bombermanJ);
        }
        else{
            valor = factorManhattan*2;
        }
        return valor+g;
    }
    
    int g(int pasos){
        return factorPasos-pasos;
    }

    private void generaCamino(Paso ea){
        int pasoActual = ea.pasos, i, j;
        camino = new char[ea.pasos+1][entrada.filas][entrada.columnas];
        while(ea != null){
            for(i = 0; i < entrada.filas; i++){
                for(j = 0; j < entrada.columnas; j++){
                    camino[pasoActual][i][j] = entrada.mapa[i][j];
                }
            }
            camino[pasoActual][entrada.enemigoI][entrada.enemigoJ] = 'E';
            camino[pasoActual][ea.bombermanI][ea.bombermanJ] = 'B';
            if(ea.ataque != null){
                camino[pasoActual][ea.ataque.i][ea.ataque.j] = 'Q';
            }
            ea = ea.anterior;
            pasoActual--;
        }
    }
    
    final static int llaveEstado(char estado){
        switch (estado) {
            case 's':
                return 0;
            case 'b':
                return 1;
            default:
                return 2;
        }
    }

    private boolean puedeAtacar(int bombermanI, int bombermanJ){
        boolean puede;
        int i, j;

        if(bombermanI == entrada.enemigoI){
            puede = true;
            for(j = Math.min(bombermanJ, entrada.enemigoJ)+1; j < Math.max(bombermanJ, entrada.enemigoJ); j++){
                if(entrada.mapa[bombermanI][j] == '#'){
                    puede = false;
                }
            }
            if(puede){
                return true;
            }
        }
        if(bombermanJ == entrada.enemigoJ){
            puede = true;
            for(i = Math.min(bombermanI, entrada.enemigoI)+1; i < Math.max(bombermanI, entrada.enemigoI); i++){
                if(entrada.mapa[i][bombermanJ] == '#'){
                    puede = false;
                }
            }
            if(puede){
                return true;
            }
        }
        return false;
    }
}
