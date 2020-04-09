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
public class Muta {
    public static void aleatoria( Individuo ind ) {
        Random ran = new Random();
        int pos1;
        int pos2;
        
        do {
            pos1 = ran.nextInt( ind.getGenotipo().length );
            pos2 = ran.nextInt( ind.getGenotipo().length );
        }while( pos1 == pos2 );
        
        int aux = ind.getGenotipo()[pos1];
        ind.getGenotipo()[pos1] = ind.getGenotipo()[pos2];
        ind.getGenotipo()[pos2] = aux;
        
        ind.calcularFitness();
    }
    
    public static void aleatoria( Individuo ind, int numPosisiones ) {
        if ( numPosisiones >= ind.getGenotipo().length-1  ) 
            return;
        // Obtenemos las posiciones aleatorias sin repetir alguna
        Random ran = new Random();
        int pos [] = new int[numPosisiones];
        while( posicionRepetida( pos ) ) {
            for (int i = 0; i < numPosisiones; i++) {
                pos[i] = ran.nextInt( ind.getGenotipo().length );
            }
        }
        // Intercambiamos las posiciones
        int aux = ind.getGenotipo()[ pos[0] ];
        for (int i = 0; i < numPosisiones-1; i++) {
            ind.getGenotipo()[ pos[i] ] = ind.getGenotipo()[ pos[i+1] ];
        }
        // A la ultima posicion de POS en el genotipo le asginamos el valor que
        // tenia el genotipo en la primera posicion de POS
        ind.getGenotipo()[ pos[numPosisiones-1] ] = aux;
        
        ind.calcularFitness();
    }
    
    private static boolean posicionRepetida( int[] pos ) {
        for (int i = 0; i < pos.length; i++) {
            for (int j = i+1; j < pos.length; j++) {
                if( pos[i] == pos[j] )
                    return true;
            }
        }
        
        return false;
    }
}
