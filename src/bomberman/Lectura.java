package bomberman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lectura {
    char mapa[][];
    int filas,columnas;
    int bombermanI, bombermanJ, enemigoI, enemigoJ;
    
    public Lectura(){
        try {
            String ruta = "mapa.txt";
            BufferedReader br = getBuffered(ruta);
            String linea =  br.readLine();
            filas=filasTotales(ruta);
            columnas=columnasTotales(ruta);
            mapa = new char[filas][columnas];
            int contador = 0;
            while(linea != null){
                String[] values = linea.split(" ");
                for (int i = 0; i<values.length; i++) {
                    mapa[contador][i] = values[i].charAt(0);
                }
                contador++;
                linea = br.readLine();
            }
            for (int x=0; x < mapa.length; x++) {
                    System.out.print("|");
                    for (int y=0; y < mapa[x].length; y++) {
                      System.out.print (mapa[x][y]);
                    }
                    System.out.println("|");
                  }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedReader getBuffered(String link){
        FileReader lector  = null;
        BufferedReader br = null;
        try {
             File Arch=new File(link);
            if(!Arch.exists()){
               System.out.println("No existe el archivo");
            }else{
               lector = new FileReader(link);
               br = new BufferedReader(lector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }
    
    public int filasTotales(String link) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(link);
        BufferedReader bf = new BufferedReader(fr);
        int filas = 0;
        String sCadena;
        while ((sCadena = bf.readLine())!=null) {
            filas++;
        }
        return filas;
    }
    
    public int columnasTotales(String link) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(link);
        BufferedReader br = new BufferedReader(fr);
        String linea =  br.readLine();
        String[] columna=linea.split(" ");
        return columna.length;
    }
    
}
