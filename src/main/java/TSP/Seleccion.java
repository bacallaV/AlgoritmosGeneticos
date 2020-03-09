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
public class Seleccion {
    public static Individuo aleatoria(Poblacion poblacion){
        //Retorna un individuo aleatorio
        Random ran = new Random();
        int pos = ran.nextInt(poblacion.getPoblacion().size()-1)+1;
        
        return new Individuo(poblacion.getPoblacion().get(pos).getGenotipo());
    }

    public static Individuo porTorneo(Poblacion poblacion){
        Individuo ind = new Individuo(poblacion.getPoblacion().get(0).getGenotipo());
        //Retorna el mejor individuo de la poblacion
        for (Individuo i: poblacion.getPoblacion()) {
            if(i.getFitness() < ind.getFitness())
                ind = new Individuo(i.getGenotipo());
        }
        return ind;
    }
}
