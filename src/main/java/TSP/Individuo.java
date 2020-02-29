/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import Herramientas.GeneradorInstancias;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author valdo
 */
public class Individuo {
    private int[] genotipo;
    private int fitness;
    public static int instancias[][];
    
    public Individuo(int ciudadInicial, int numeroCiudades){
        this.genotipo = new int[numeroCiudades];
        this.genotipo[0] = ciudadInicial;
        this.fitness = 0;
        inicializarAleatoriamente();
        calcularFitness();
    }
    
    public Individuo(int genotipo[]){
        this.genotipo = genotipo.clone();
        this.fitness=0;
        calcularFitness();
    }

    private void inicializarAleatoriamente() {
        ArrayList<Integer> ciudades = new ArrayList<>();
        
        for (int i = 0; i < this.genotipo.length; i++)
            ciudades.add(i);
        ciudades.remove(this.genotipo[0]);
        
        //Llenar los espacios vacios restantes del genotipo
        Random ran = new Random();
        for (int i = 1; i < genotipo.length; i++) {
            int pos = ran.nextInt(ciudades.size());
            int c = ciudades.get(pos);
            this.genotipo[i] = c;
            //Eliminamos de las ciudades
            ciudades.remove(pos);
        }
    }

    public void calcularFitness() {
        if(instancias==null){
            return;
        }
        //Recorremos el genotipo para recuperar las posiciones dentro de las instancias y acumularlas
        for (int i = 0; i < this.genotipo.length-1 ; i++)
            this.fitness+=instancias[this.genotipo[i]][this.genotipo[i+1]];
        //Sumamos el camino de regreso a casa
        this.fitness+=instancias[this.genotipo[this.genotipo.length-1]][this.genotipo[0]];
    }
    
    @Override
    public String toString() {
//        String aux = "";
        String aux = "Genotipo: ";
        
        for(int i=0; i<this.genotipo.length; i++){
            aux+=String.valueOf(this.genotipo[i]);
            if(i!=this.genotipo.length-1)
                aux+=" - ";
        }
        aux+="\nFitness: "+this.fitness+"\n";
        
        return aux;
    }

    public int[] getGenotipo() {
        return genotipo;
    }

    public int getFitness() {
        return fitness;
    }
    
}
