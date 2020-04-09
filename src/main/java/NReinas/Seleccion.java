/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import java.util.Random;

/**
 *
 * @author valdo
 */
public class Seleccion {
    public static Individuo aleatoria( Poblacion pob ) {
        Random ran = new Random();
        int pos = ran.nextInt( pob.getPoblacion().size() );
        
        return new Individuo( pob.getPoblacion().get(pos).getGenotipo() );
    }
    
    public static Individuo porTorneo( Poblacion pob ) {
        // Asumimos que el primero es el mejor
        Individuo mejor = new Individuo( pob.getPoblacion().get(0).getGenotipo() );
        
        for( Individuo i: pob.getPoblacion() ) {
            //Si el individuo en la población tiene menor fitness se hace un intercambio del mejor
            if ( i.getFitness() < mejor.getFitness() )
                mejor = new Individuo( i.getGenotipo() );
        }
        
        return mejor;
    }
}
