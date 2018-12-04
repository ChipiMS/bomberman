package bomberman;
public class Monticulo {
    int fin;
    Paso nodos[];
    Monticulo(int i) {
        fin = 0;
        nodos = new Paso[i];
    }
    
    Paso elimina(){
        int posicion = 0, nuevoLugar;
        Paso primero = nodos[0], auxiliar;
        boolean puedeBajar = true;
        fin--;
        nodos[0] = nodos[fin];
        while(2*posicion+1<fin && puedeBajar){
            if(2*posicion+2 < fin && nodos[2*posicion+2].g > nodos[2*posicion+1].g){
                nuevoLugar = 2*posicion+2;
            }
            else{
                nuevoLugar = 2*posicion+1;
            }
            if(nodos[nuevoLugar].g > nodos[posicion].g){
                posicion = nuevoLugar;
                auxiliar = nodos[(posicion-1)/2];
                nodos[(posicion-1)/2] = nodos[posicion];
                nodos[posicion] = auxiliar;
            }
            else{
                puedeBajar = false;
            }
        }
        return primero;
    }

    boolean estaVacio(){
        return fin == 0;
    }

    void inserta(Paso paso){
        int posicion = fin;
        Paso auxiliar;
        fin++;
        nodos[posicion] = paso;
        while(posicion > 0 && nodos[(posicion-1)/2].g < nodos[posicion].g){
            auxiliar = nodos[(posicion-1)/2];
            nodos[(posicion-1)/2] = nodos[posicion];
            nodos[posicion] = auxiliar;
            posicion = (posicion-1)/2;
        }
    }

    Paso saca(){
        Paso primero = null;
        while(fin != 0 && primero == null){
            primero = elimina();
            if(!primero.actual){
                primero = null;
            }
        }
        return primero;
    }
}
