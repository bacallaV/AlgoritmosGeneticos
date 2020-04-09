/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author valdo
 */
public class Individuo {
    private int[] genotipo;
    private int fitness;
    
    public Individuo(int reinas) {
        this.genotipo = new int[reinas];
        this.fitness = 0;
        inicializarAleatoriamente();
        calcularFitness();
    }
    
    public Individuo(int genotipo[]){
        this.genotipo = genotipo.clone();
        calcularFitness();
    }
    
    private void inicializarAleatoriamente() {
        Random ran = new Random();
        LinkedList<Integer> reinas = new LinkedList<>();
        for (int i = 0; i < this.genotipo.length; i++)
            reinas.add(i);
        for (int i = 0; i < this.genotipo.length; i++) {
            int pos = ran.nextInt( reinas.size() );
            this.genotipo[i] = reinas.get(pos);
            reinas.remove(pos);
        }
    }

    public void calcularFitness() {
        // Ataques diagonales
        int atkD = 0;
        
        for (int i = 0; i < this.genotipo.length; i++) {
            int reinaActual = this.genotipo[i];
            for (int j = i+1; j < this.genotipo.length; j++) {
                int reinaNueva = this.genotipo[j];
                int posComp = j-i;
                if( reinaActual ==  reinaNueva-posComp || reinaActual ==  reinaNueva+posComp )
                    atkD++;
            }
        }
        
        this.fitness = atkD;
    }
    
    @Override
    public String toString() {
        String aux = Arrays.toString(this.genotipo) + "\n" + this.fitness;
        
        return aux;
    }
    
    /* Getters & Setters */
    
    public int[] getGenotipo() {
        return this.genotipo;
    }
    
    public int getFitness() {
        return this.fitness;
    }
    
    public void setGenotipo(int genotipo[]) {
        this.genotipo = genotipo.clone();
        calcularFitness();
    }
}
