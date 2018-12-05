package bomberman;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Lectura {
    char mapa[][];
    int filas,columnas;
    int bombermanI, bombermanJ, enemigoI, enemigoJ;
    
    public Lectura(){
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            int result = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            String ruta = file.getPath();
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
            posicionBomberman();
            posicionEnemigo();
            System.out.println(bombermanI+" "+bombermanJ);
            System.out.println(enemigoI+" "+enemigoJ);
            //imprime atriz en consola
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
    
    public void posicionBomberman(){
        for (int x = 0; x < mapa.length;x++) {
            for (int y = 0; y < mapa[x].length; y++) {
                if(mapa[x][y]=='B'){
                    mapa[x][y]='.';
                    bombermanI=x;
                    bombermanJ=y;
                }
            }   
	}
    }
    
    public void posicionEnemigo(){
        for (int x = 0; x < mapa.length;x++) {
            for (int y = 0; y < mapa[x].length; y++) {
                if(mapa[x][y]=='E'){
                    mapa[x][y]='#';
                    enemigoI=x;
                    enemigoJ=y;
                }
            }   
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
    public static void main(String[] args) {
        Lectura l=new Lectura();
    }
}
