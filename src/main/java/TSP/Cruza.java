/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.util.Random;

/**
 *
 * @author valdo
 */
public class Cruza {
    public static Individuo asexual(Individuo i1, Individuo i2){
        int genotipo1[] = i1.getGenotipo().clone();
        int genotipo2[] = i2.getGenotipo().clone();
        
        int aux;
        Random ran = new Random();
        int pos1, pos2;
        
        //Creamos dos posiciones aleatorias entre el 1 y la longitud del genotipo, que no sean iguales
        do{
            pos1 = ran.nextInt(genotipo2.length-1)+1;
            pos2 = ran.nextInt(genotipo2.length-1)+1;
        }while(pos1==pos2);
        
        //Hacemos el intercambio de ciudades en el genotipo
        aux = genotipo1[pos1];
        genotipo1[pos1] = genotipo1[pos2];
        genotipo1[pos2] = aux;
        
        aux = genotipo2[pos1];
        genotipo2[pos1] = genotipo2[pos2];
        genotipo2[pos2] = aux;
        
        //Creamos a los individuos
        Individuo hijo1 = new Individuo(genotipo1);
        Individuo hijo2 = new Individuo(genotipo2);
        
        //Seleccionamos al mejor (al de menor ditancia recorrida)
        if(hijo1.getFitness() < hijo2.getFitness())
            return hijo1;
        return hijo2;
    }
}
