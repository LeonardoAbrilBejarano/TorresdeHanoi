
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TorresDeHanoi;

import Graficos.GeneradorPasos;
import Graficos.PilaGrafica;
import Graficos.Resultado;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author leillo
 */
public class Juego {

    private Stack st;
    private Stack stinv;
    private int numdiscos;

    /*
    Variables para los graficos
     */
    private PilaGrafica pg;
    private boolean stop = false;
    private GeneradorPasos gp;
    
    /*
    Atributos añadidos para realizar el backtracking inverso. Los caminos encontrados en el backtracking inverso se almacenaran 
    en el arraylist y para comparar un camino en el backtracking normal lo compararemos.
    
    No hace falta guardar toda la pila con las posiciones, ya que la ultima posicion es la que cuenta y la ultima posicion 
    tendra todo el registro de los movimientos
     */
    private ArrayList minv;
    private int nb;
    private int maxmov;

    /*
    Constructor del juego.Se organizara en: menu(pide discos al usuario);arranquedejuego(inicializa el primer mapa con los discos y genera los submapas);
    motorjuego(cojera de la pila un submapa y generara mas submapas que se añadiran a la pila,si un submapa resulta ser el resultado, se informara con los
    movimientos).
     */

    public Juego(int numd, int nb) {

        st = new Stack();
        stinv = new Stack();
        minv = new ArrayList();
        pg = new PilaGrafica();

        //menu();
        this.setNumdiscos(numd);
        this.setNb(nb);

        ArranqueJuegoInverso();
        MotorJuegoInverso();

        ArranqueJuego();
        MotorJuego();

    }
    public Juego(int numd, int nb,GeneradorPasos gp) {

        st = new Stack();
        stinv = new Stack();
        minv = new ArrayList();
        pg = new PilaGrafica();
        this.gp=gp;
        
        this.setNumdiscos(numd);
        this.setNb(nb);

        ArranqueJuegoInverso();
        MotorJuegoInverso();

        ArranqueJuego();

    }

    /*
    Maxea el nivel de mapas que tiene que llegar el motor para que se optimize mas la aplicacion
     */
    private void initmaxMovimientos(int numdiscos) {
        int max = 1;
        for (int i = 0; i < numdiscos; i++) {
            max = max * 2;
        }
        max = max - 1;
        this.maxmov = max - this.nb;
        //System.out.print(max);//
    }

    /*
    El arranque de juego consiste en arrancar un mapa con el constructor de mapa de inicio.Se le pondra los discos a la torre 1
    y generara los submapas.
    
    Habra dos arranques, uno para el backtracking normal y otro para el inverso, al igual que casi todos los metodos que necesita
    el motor del juego para resolver el juego.
     */
    private void ArranqueJuego() {
        initmaxMovimientos(this.numdiscos);
        Mapa m = new Mapa(numdiscos);
        st.add(m);
    }

    private void ArranqueJuegoInverso() {
        Mapa m = new Mapa(numdiscos, this.nb);
        if (this.nb == 0) {
            minv.add(m);
        } else {
            Stack asd = m.nextMovimientoInverso();
            Stack aux = (Stack) asd.clone();
            while (!aux.empty()) {
                stinv.push(aux.pop());
            }
        }
        //mirarPila();

    }

    /*
    El motor del juego consiste en cojer un submapa de la pila y, si no es el resultado, genera nuevos submapas 
    y los añade a la pila.Cuando es el resultado, se muestra. Los submapas se mezclan entre ellos en la pila, y los
    que le diferencia entre ellos es el numero de movimientos hechos.
    Se ha añadido checkredundancia,que evita un cambio si la posicion se ha repetido.
     */
    private void MotorJuego() {
        while (!st.empty() && !isResultadoInverso(st)) {
            Mapa m = (Mapa) st.pop();
            if (m.getPosicionmovimiento() < this.maxmov && !checkRedundancia(st, m)) {
                Stack newMaps = m.nextMovimiento();
                Stack aux = (Stack) newMaps.clone();
                while (!aux.empty()) {
                    st.push(aux.pop());
                }
            }
            pg.repaint(st);
            if (this.isResultadoInverso(st)) {
                Resultado r = new Resultado(mostrarResultadoArrayList());
                r.setVisible(true);
                stop = true;
            }
        }

    }
    
    /*
    El motor inverso genera n mapas segun el nivel de backtracking indicado por el usuario y los mete en un arraylist 
    que luego el motor lo utilizara para comparar los mapas
     */

    private void MotorJuegoInverso() {
        int nback = this.nb;
        while (!stinv.empty()) {
            Mapa m = (Mapa) stinv.pop();
            if (m.getPosicionmovimiento() == nback && !checkRedundancia(stinv, m)) {
                Mapa aux = new Mapa(m.getT().clone(), m.getMovimientos().clone(), m.getMaxmov(), m.getPosicionmovimiento(), m.getContsubmapas(), m.getMaxsubmapas());
                minv.add(aux);
            } else if (m.getPosicionmovimiento() < nback && !checkRedundancia(stinv, m)) {
                Stack newMaps = m.nextMovimientoInverso();
                Stack aux = (Stack) newMaps.clone();
                while (!aux.empty()) {
                    stinv.push(aux.pop());
                }
            }
        }
    }

    /*
    Motor para el seguimiento de la pila
    */
    public void MotorJuegoPasitos() {
        int mantes=0;
        int mdespues=0;
        if (stop == false) {
            if (!isResultadoInverso(st) && !st.empty()) {
                Mapa m = (Mapa) st.pop();
                mantes=m.nivelmapa;
                if (m.getPosicionmovimiento() < this.maxmov && !checkRedundancia(st, m)) {
                    Stack newMaps = m.nextMovimiento();
                    Stack aux = (Stack) newMaps.clone();
                    while (!aux.empty()) {
                        st.push(aux.pop());
                    }
                    aux=(Stack) st.clone();
                    m=(Mapa) aux.pop();
                    mdespues=m.nivelmapa;
                }
                if(mdespues<=mantes){
                        gp.setBckpaint(true);
                }
                pg.repaint(st);
            }
            if (this.isResultadoInverso(st)) {
                Resultado r = new Resultado(mostrarResultadoArrayList());
                r.setVisible(true);
                stop = true;
            }
        }
    }


    /*
    Mira si el submapa es igual a alguno de sus mapas padres.
    Si es asi, significa que hay un trozo de camino que es redundante, al ser redundante no es el mas corto
    y al no ser el mas corto no es la solucion
     */
    private boolean checkRedundancia(Stack st, Mapa m) {
        Stack saux = (Stack) st.clone();
        Mapa maux = new Mapa(m.getT().clone(), m.getMovimientos().clone(), m.getMaxmov(), m.getPosicionmovimiento(), m.getContsubmapas(), m.getNivelmapa());
        Mapa msaux;
        while (!saux.empty()) {
            msaux = (Mapa) saux.pop();
            if (matchTorres(maux, msaux)) {
                return true;
            }
        }
        return false;
    }

    /*
    Mira si dos torres son iguales
     */
    private boolean matchTorres(Mapa m, Mapa m2) {
        //Mapa son
        Torre[] to = new Torre[3];
        to[0] = new Torre();
        to[1] = new Torre();
        to[2] = new Torre();
        to[0].setTorre((ArrayList) m.getT()[0].getTorre().clone());
        to[1].setTorre((ArrayList) m.getT()[1].getTorre().clone());
        to[2].setTorre((ArrayList) m.getT()[2].getTorre().clone());

        //Mapa father
        Torre[] to2 = new Torre[3];
        to2[0] = new Torre();
        to2[1] = new Torre();
        to2[2] = new Torre();
        to2[0].setTorre((ArrayList) m2.getT()[0].getTorre().clone());
        to2[1].setTorre((ArrayList) m2.getT()[1].getTorre().clone());
        to2[2].setTorre((ArrayList) m2.getT()[2].getTorre().clone());

        Disco aux;
        Disco aux2;
        //Controlador Clasico
        for (int n = 0; n < 3; n++) {
            if (to[n].getTorre().size() == to2[n].getTorre().size()) {
                for (int i = 0; i < to[n].getTorre().size(); i++) {//2
                    aux = (Disco) to[n].getTorre().get(i);
                    aux2 = (Disco) to2[n].getTorre().get(i);
                    if (aux.getTamaño() != aux2.getTamaño()) {//3
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        return true;
    }

    private void mostrarResultado() {
        Mapa resultado = (Mapa) st.pop();
        Mapa resultadoinv;
        Torre[] t = resultado.getT();
        String[] movresulinv;
        String[] movresul = resultado.getMovimientos();
        for (int i = 0; i < movresul.length; i++) {
            if (movresul[i] != null) {
                System.out.println(movresul[i]);
            }
        }

        for (int i = 0; i < minv.size(); i++) {
            if (matchTorres(resultado, (Mapa) minv.get(i))) {
                resultadoinv = (Mapa) minv.get(i);
                movresulinv = resultadoinv.getMovimientos();
                for (int k = movresulinv.length - 1; k >= 0; k--) {
                    if (movresulinv[k] != null) {
                        System.out.println(movresulinv[k]);
                    }
                }
            }
        }
    }

    public ArrayList mostrarResultadoArrayList() {
        ArrayList datos = new ArrayList();
        Stack aux= (Stack) st.clone();
        Mapa resultado = (Mapa) aux.pop();
        Mapa resultadoinv;
        Torre[] t = resultado.getT();
        String[] movresulinv;
        String[] movresul = resultado.getMovimientos();
        for (int i = 0; i < movresul.length; i++) {
            if (movresul[i] != null) {
                datos.add(movresul[i]);
            }
        }

        for (int i = 0; i < minv.size(); i++) {
            if (matchTorres(resultado, (Mapa) minv.get(i))) {
                resultadoinv = (Mapa) minv.get(i);
                movresulinv = resultadoinv.getMovimientos();
                for (int k = movresulinv.length - 1; k >= 0; k--) {
                    if (movresulinv[k] != null) {
                        datos.add(movresulinv[k]);
                    }
                }
            }
        }
        return datos;
    }

    private boolean isResultadoInverso(Stack st) {
        Stack global = new Stack();
        global = (Stack) st.clone();
        Mapa m = (Mapa) global.pop();
        Torre[] to = new Torre[3];
        for (int i = 0; i < minv.size(); i++) {
            if (matchTorres(m, (Mapa) minv.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void setNumdiscos(int numdiscos) {
        this.numdiscos = numdiscos;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public Stack getSt() {
        Stack aux = (Stack) st.clone();
        return aux;
    }

}
