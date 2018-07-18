/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TorresDeHanoi;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author leillo
 */
public class Mapa {
    //Atributos añadidos para que la generacion de mapas se resuelva por backtracking
    int nivelmapa;
    int maxsubmapas;
    int contsubmapas;
    //
    Torre[] t;
    String[] movimientos;
    int posicionmovimiento;
    int maxmov;
    /*
    Este constructor es utilizado para el motor de juego
    */

    /*
    Metodos necesarios para mirar el comportamiento de los mapas con backtracking
    */
    public int getMaxsubmapas() {
        return maxsubmapas;
    }

    
    public int getNivelmapa() {
        return nivelmapa;
    }

    public int getContsubmapas() {
        return contsubmapas;
    }
    
    
    public Mapa(Torre[] t, String[] movimientos, int maxmov,int posicionmovimiento,int submapas,int nivelmapa) {
        this.t=t;
        this.movimientos = movimientos;
        this.maxmov = maxmov;
        this.posicionmovimiento = posicionmovimiento;
        this.contsubmapas=submapas;
        this.nivelmapa=nivelmapa;
        generarMAXSUBMAPAS();
    }
    /*
    Este constructor es utilizado para el arranque de juego
    */
    
    public Mapa(int numdiscos) {
        initTorres(numdiscos);
        initmaxMovimientos(numdiscos);
        this.movimientos = new String[maxmov];
        posicionmovimiento=0;
        contsubmapas=0;
        nivelmapa=0;
        generarMAXSUBMAPAS();
    }
        /*
    Constructorr para crear mapas inversos
    */
    public Mapa(int numdiscos,int nback) {
        initTorresInverso(numdiscos);
        this.maxmov=nback;
        this.movimientos = new String[maxmov];
        posicionmovimiento=0;
        contsubmapas=0;
        nivelmapa=0;
        generarMAXSUBMAPAS();
    }
    /*
    Metodo para calcular el max de submapas que puede generar este mapa.
    */
    
    private void generarMAXSUBMAPAS(){
        maxsubmapas=0;
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                if(k!=i){//(*1)
                    if(!t[i].getTorre().isEmpty()){//(*2)
                        if(t[k].getTorre().isEmpty()||t[i].showTopDisco().getTamaño()<t[k].showTopDisco().getTamaño()){//(*3)
                                maxsubmapas++;
                        }
                    }
                }   
            }
        }
        maxsubmapas=maxsubmapas-1;
    }
    
    /*
    Algoritmo que recorre los n nuevos movimientos que se puede hacer en este mapa.
    El algoritmo funciona de la siguiente manera:
    (*1)No se puede mover un disco a la misma posicion
    (*2)Si la torre desde que la que se va a mover el disco no esta vacia
    (*3)Si la torre a la que se va a mover esta vacia o el disco que hay en la torre a la que se va a mover es mayor que el disco que se esta moviendo
    (*4)Si el contador es igual al camino que debe tomar el mapa
    (+5)Si ha recorrido todos los caminos del mapa no necesitamos que cree un nuevo mapa padre para la pila
    */
   
    public Stack nextMovimiento(){
        Stack newMaps=new Stack();
        int contadoractualsubmapas=0;
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                if(k!=i){//(*1)
                    if(!t[i].getTorre().isEmpty()){//(*2)
                        if(t[k].getTorre().isEmpty()||t[i].showTopDisco().getTamaño()<t[k].showTopDisco().getTamaño()){//(*3)
                            if(contadoractualsubmapas==this.contsubmapas){//(*4)
                                newMaps.push(nextMap(i,k));
                                contadoractualsubmapas++;
                            }else{
                                contadoractualsubmapas++;
                            }
                        }
                    }
                }   
            }
        }
        if(contsubmapas<this.maxsubmapas){//(*5)
        newMaps.push(nextMapFather());
        }
        return newMaps;
    }
    

    /*
    La diferencia entre nextMovimiento y nextMovimientoInverso es que nextMovimiento se utilizara para el motor normal y llama al nextMap para 
    que los movimientos sean de i a k y en nextMovimientoInverso se utilizara para el motor inverso y llama al nextMapInverso para que los
    movimientos sean de k a i
    */
        public Stack nextMovimientoInverso(){
        Stack newMaps=new Stack();
        int contadoractualsubmapas=0;
        for(int i=0;i<3;i++){
            for(int k=0;k<3;k++){
                if(k!=i){//(*1)
                    if(!t[i].getTorre().isEmpty()){//(*2)
                        if(t[k].getTorre().isEmpty()||t[i].showTopDisco().getTamaño()<t[k].showTopDisco().getTamaño()){//(*3)
                            if(contadoractualsubmapas==this.contsubmapas){//(*4)
                                newMaps.push(nextMapInverso(i,k));
                                contadoractualsubmapas++;
                            }else{
                                contadoractualsubmapas++;
                            }
                        }
                    }
                }   
            }
        }
        if(contsubmapas<this.maxsubmapas){//(*5)
        newMaps.push(nextMapFather());
        }
        return newMaps;
    }
    /*
    Se genera un nuevo submapa con el cambio de disco y se va guardando el movimiento actual mas los que se ha hecho de los mapas padre
    Se suma mas uno a los movimientos del submapa
    */
    
    private Mapa nextMapFather(){
        Mapa m= new Mapa(this.t,this.movimientos,this.maxmov,this.posicionmovimiento,this.contsubmapas+1,this.nivelmapa);
        return m;
    }
    private Mapa nextMap(int inicio,int fin){
        //Aqui falla en cambiar las posiciones de la torre
        Torre[] to=new Torre[3];
        to[0]=new Torre();
        to[1]=new Torre();
        to[2]=new Torre();
        to[0].setTorre((ArrayList) this.t[0].getTorre().clone());
        to[1].setTorre((ArrayList) this.t[1].getTorre().clone());
        to[2].setTorre((ArrayList) this.t[2].getTorre().clone());
        Disco d=new Disco(to[inicio].showTopDisco().getTamaño());  
        //to[inicio].showTorre();
        to[inicio].removeDisco();
        to[fin].addDisco(d);
        //Falla en los movimientos
        String[] movimientosaux=new String[maxmov];
        movimientosaux=this.getMovimientos().clone();
        movimientosaux[posicionmovimiento]="Movimiento de la torre " + (inicio+1) + " a la torre "+ (fin+1);
      // System.out.println("Mapa"+posicionmovimiento);
      /*
      for(int i=0;i<movimientosaux.length;i++){
        System.out.println(movimientos[i]);
      }
        */
      
        Mapa m=new Mapa(to,movimientosaux,maxmov,posicionmovimiento+1,0,this.nivelmapa+1);
        return m;
    }
    
        private Mapa nextMapInverso(int inicio,int fin){
        //Aqui falla en cambiar las posiciones de la torre
        Torre[] to=new Torre[3];
        to[0]=new Torre();
        to[1]=new Torre();
        to[2]=new Torre();
        to[0].setTorre((ArrayList) this.t[0].getTorre().clone());
        to[1].setTorre((ArrayList) this.t[1].getTorre().clone());
        to[2].setTorre((ArrayList) this.t[2].getTorre().clone());
        Disco d=new Disco(to[inicio].showTopDisco().getTamaño());  
        //to[inicio].showTorre();
        to[inicio].removeDisco();
        to[fin].addDisco(d);
        //Falla en los movimientos
        String[] movimientosaux=new String[maxmov];
        movimientosaux=this.getMovimientos().clone();
        movimientosaux[posicionmovimiento]="Movimiento de la torre " + (fin+1) + " a la torre "+ (inicio+1);    
      // System.out.println("Mapa"+posicionmovimiento);
      /*
      for(int i=0;i<movimientosaux.length;i++){
        System.out.println(movimientos[i]);
      }
        */
      
        Mapa m=new Mapa(to,movimientosaux,maxmov,posicionmovimiento+1,0,this.nivelmapa+1);
        return m;
    }
    /*
    Calcula el max de movimientos que tiene que hacer un mapa
    */
    private void initmaxMovimientos(int numdiscos){
        int max=1;
        for(int i=0;i<numdiscos;i++){
            max=max*2;
        }
        max=max-1;
        this.maxmov=max;
        //System.out.print(max);//
    }
    /*
    Inicia las torre 0 del primer mapa
    */
    private void initTorres(int numdiscos){  
        this.t=new Torre[3];
        t[0]=new Torre();
        t[1]=new Torre();
        t[2]=new Torre();
        for(int i=numdiscos;i>0;i--){
           Disco d=new Disco(i);
           t[0].addDisco(d);
        }
        //a.showTorre();//
    }
    
    private void initTorresInverso(int numdiscos){  
        this.t=new Torre[3];
        t[0]=new Torre();
        t[1]=new Torre();
        t[2]=new Torre();
        for(int i=numdiscos;i>0;i--){
           Disco d=new Disco(i);
           t[2].addDisco(d);
        }
        //a.showTorre();//
    }

    public String[] getMovimientos() {
        return movimientos;
    }

    public Torre[] getT() {
        return t;
    }

    public int getPosicionmovimiento() {
        return posicionmovimiento;
    }

    public int getMaxmov() {
        return maxmov;
    }


    
    
}
