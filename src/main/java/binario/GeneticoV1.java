/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

/**
 *
 * @author valdo
 */
public class GeneticoV1 {
    private int numG;
    private double pMuta;
    private Poblacion pobActual;
    private int tamPob;
    private boolean tipo; //true=maximizacion, false=minimizacion

    public GeneticoV1(int numG, double pMuta, int tamPob, boolean tipo) {
        this.numG = numG;
        this.pMuta = pMuta;
        this.tamPob = tamPob;
        this.tipo = tipo;
        //Generamos la poblacion inicial de manera aleatoria
        this.pobActual = new Poblacion(this.tamPob);
    }
    
    public void evolucionar(){
        Individuo mejor = new Individuo(new int[this.pobActual.getPoblacion().get(0).getGenotipo().length]);
        int mascara[] = new int[]{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1};
        //Se generan las nuevas poblaciones
        for(int g=0; g<this.numG; g++){
            Poblacion nueva = new Poblacion();
            for(int i=0; i<this.tamPob; i++){
                //Proceso de seleccion
                Individuo madre = Seleccion.aleatoria(pobActual);
                Individuo padre = Seleccion.aleatoria(pobActual);
                //Proceso de cruza
                Individuo hijo;
                if (this.tipo)
                    hijo = Cruza.porMascara(mascara, padre, madre, Cruza.MAXIMIZACION);
                else
                    hijo = Cruza.porMascara(mascara, padre, madre, Cruza.MINIMIZACION);
                //Proceso de mutacion
                if(Math.random()*100<this.pMuta)
                    Muta.aleatoria(hijo);
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if(hijo.getFitness()<mejor.getFitness()) //FALTA MAX Y MIN
                    mejor = new Individuo(hijo.getGenotipo());
            }
                System.out.println("Generacion: "+g+" - Mejor:"+mejor);
            //Actualizar la poblacion actual
            this.pobActual = new Poblacion(nueva);
        }
    }

    /* Getters & Setters */
    
    public int getNumG() {
        return numG;
    }

    public double getpMuta() {
        return pMuta;
    }

    public Poblacion getPobActual() {
        return pobActual;
    }

    public int getTamPob() {
        return tamPob;
    }
    
    public void setNumG(int numG) {
        this.numG = numG;
    }

    public void setpMuta(double pMuta) {
        this.pMuta = pMuta;
    }

    public void setPobActual(Poblacion pobActual) {
        this.pobActual = pobActual;
    }

    public void setTamPob(int tamPob) {
        this.tamPob = tamPob;
    }
    
    /* Fin */

}
