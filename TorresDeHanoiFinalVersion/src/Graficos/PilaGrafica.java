/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import TorresDeHanoi.Mapa;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Stack;
import javax.swing.JFrame;

/**
 *  El metodo paint pintara la representacion de la pila pasado por parametro
 * @author Leonardo
 */
public class PilaGrafica  extends JFrame {
    Stack st;
    public PilaGrafica() {
        super("TORRES DE HANOI COMPORTAMIENTO DE LA PILA");
        setSize(400, 400);
        setVisible(true);
        st=new Stack();
    }
    
    @Override
    public void paint(Graphics g) {
        Container content = this.getContentPane();
        content.setLayout(new FlowLayout());
        // call superclass's paint method
        super.paint(g);
        // set new drawing color using integers
        g.setColor(new Color(255, 0, 0));
        //(x,y,z,t)= x:Jtable x to left rectangle , y=Jtable y to top rectangle , zt= dimensiones rectangle 
        Stack aux = (Stack) st.clone();
        Stack aux2= new Stack();
        int x=20;
        int y=50;
        while (!aux.empty()) {
            aux2.push(aux.pop());
        }
        while (!aux2.empty()) {
            Mapa m = (Mapa) aux2.pop();
            g.fillRect(x, y, 10, 10);
            g.drawString(m.getNivelmapa() + " + toma el camino : " + (m.getContsubmapas()+1) + " + su numero de caminos es:" + (m.getMaxsubmapas()+1), x+15, y+10);
            if(!aux2.empty()){
                g.drawLine(x+5, y, x+5, y+20);
            }
            y=y+20;
        }       
        content.paintComponents(g);
    }
    
    
 
    public void repaint(Stack st){
        this.st=st;
        repaint();
    }
    

}
