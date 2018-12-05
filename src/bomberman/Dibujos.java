/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Color;
import java.awt.Graphics;

public class Dibujos {

    public static void PintarPared(Graphics g, String Dato, int x, int y, int x1, int y1) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, x1, y1);
        g.setColor(new Color(255, 255, 0));
        g.drawRect(x, y, x1, y1);
    }
    
    public static void PintarCamino(Graphics g, String Dato, int x, int y, int x1, int y1) {
        g.setColor(new Color(62, 81, 217));
        g.fillRect(x, y, x1, y1);
        g.setColor(new Color(255, 255, 0));
        g.drawRect(x, y, x1, y1);
    }

    public static void Bomba(Graphics g, String Dato, int x, int y, int x1, int y1) {
        g.setColor(new Color(62, 81, 217));
        g.fillRect(x, y, x1, y1);
        g.setColor(new Color(0, 0, 0));
        g.fillOval(x + 1, y + 1, 29, 29);
        g.setColor(new Color(255, 255, 0));
        g.drawRect(x, y, x1, y1);
    }

    public static void Bomberman(Graphics g, String Dato, int x, int y, int x1, int y1) {
        g.setColor(new Color(62, 81, 217));
        g.fillRect(x, y, x1, y1);
        g.setColor(new Color(255, 175, 175));
        g.fillOval(x + 1, y + 1, 29, 29);
        g.setColor(new Color(255, 255, 0));
        g.drawRect(x, y, x1, y1);
        g.setColor(new Color(0, 0, 0));
        g.fillOval(x + 7, y + 10, 7, 7);
        g.fillOval(x + 17, y + 10, 7, 7);
    }

    public static void Enemigo(Graphics g, String Dato, int x, int y, int x1, int y1) {
        g.setColor(new Color(62, 81, 217));
        g.fillRect(x, y, x1, y1);
        g.setColor(new Color(255, 0, 0));
        g.fillOval(x + 1, y + 1, 29, 29);
        g.setColor(new Color(255, 255, 0));
        g.drawRect(x, y, x1, y1);
        g.setColor(new Color(0, 0, 0));
        g.fillOval(x + 7, y + 10, 7, 7);
        g.fillOval(x + 17, y + 10, 7, 7);
    }

    public static void Borrar(Graphics g, String Dato, int x, int y, int x1, int y1) {
        if (y == 520) {
            y1 = y1 + 1;
        }
        g.setColor(new Color(235, 230, 240));
        g.fillRect(x, y, x1 + 1, y1 + 2);
    }
}
