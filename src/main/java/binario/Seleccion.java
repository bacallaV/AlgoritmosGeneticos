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
public class Seleccion {
    public static Individuo aleatoria(Poblacion poblacion){
        Random ran = new Random();
        int pos = ran.nextInt(poblacion.getPoblacion().size());
        
        return new Individuo(poblacion.getPoblacion().get(pos).getGenotipo());
    }
    public static Individuo porTorneo(Poblacion poblacion){
        Poblacion aux = new Poblacion(poblacion.ordenar());
        Individuo ind = new Individuo((aux.getPoblacion().get(aux.getPoblacion().size()-1).getGenotipo()));
        return ind;
    }
}
