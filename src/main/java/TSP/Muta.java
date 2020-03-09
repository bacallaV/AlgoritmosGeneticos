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
public class Muta {
    public static void aleatoria(Individuo i){
        int aux;
        Random ran = new Random();
        int pos1, pos2;
        
        //Creamos dos posiciones aleatorias entre el 1 y la longitud del genotipo, que no sean iguales
        do{
            pos1 = ran.nextInt(i.getGenotipo().length-1)+1;
            pos2 = ran.nextInt(i.getGenotipo().length-1)+1;
        }while(pos1==pos2);
        //Hacemos el intercambio de ciudades en el genotipo
        aux = i.getGenotipo()[pos1];
        i.getGenotipo()[pos1] = i.getGenotipo()[pos2];
        i.getGenotipo()[pos2] = aux;
        
        i.calcularFitness();
    }
}
