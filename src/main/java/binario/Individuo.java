/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

import java.util.Random;

/**
 *
 * @author valdo
 */
public class Individuo {
    private int genotipo[];
    private long fenotipo;
    private long fitness;
    
    public Individuo(int n){
        this.genotipo = new int[n];
        inicializarAleatoriamente();
        calcularFitness();
    }
    
    public Individuo(int genotipo[]){
        this.genotipo = genotipo.clone();
        calcularFitness();
    }
    
    private void inicializarAleatoriamente() {
        Random ran = new Random();
        for(int i=0; i<this.genotipo.length;i++){
            this.genotipo[i] = ran.nextInt(2);
        }
    }
    
    public void calcularFitness(){
        calcularFenotipo();
        //Evaluar el fenotipo en la funcion deseada (2x^2+x+1)
        this.fitness = (2*this.fenotipo*this.fenotipo)+this.fenotipo+1;
    }

    private void calcularFenotipo() {
        //Decodificacion del genotipo (En este caso, de binario a decimal)
        int aux=0;
        for(int i=genotipo.length-1; i>=0; i--){
            if(genotipo[i]==1)
                this.fenotipo+=(int)Math.pow(2, aux);
            aux++;
        }
    }
    
    public static int compare(Individuo ind1, Individuo ind2){
        return Long.compare(ind1.getFitness(), ind2.getFitness());
    }
    
    /* Getters y Setters */

    public int[] getGenotipo() {
        return genotipo;
    }

    public long getFenotipo() {
        return fenotipo;
    }

    public long getFitness() {
        return fitness;
    }

    public void setGenotipo(int[] genotipo) {
        this.genotipo = genotipo;
    }

    public void setFenotipo(long fenotipo) {
        this.fenotipo = fenotipo;
    }

    public void setFitness(long fitness) {
        this.fitness = fitness;
    }
    
    /* Fin */

    @Override
    public String toString() {
        String aux = "";
//        String aux = "Genotipo: ";
//        
//        for(int i=0; i<this.genotipo.length; i++){
//            aux+=String.valueOf(this.genotipo[i]);
//            if(i!=this.genotipo.length-1)
//                aux+=" - ";
//        }
        aux+="\nFenotipo: "+this.fenotipo+"\nFitness: "+this.fitness+"\n";
        
        return aux;
    }
    
    
    
}
