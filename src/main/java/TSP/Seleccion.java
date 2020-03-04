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
        //Retorna el mejor individuo de la poblacion
        Poblacion aux = new Poblacion(poblacion.ordenar());
        Individuo ind = new Individuo((aux.getPoblacion().get(aux.getPoblacion().size()-1).getGenotipo()));
        return ind;
    }
}
