package Clasificador;

import java.util.Random;

public class Muta {
    public static void aleatoria( Individuo ind ) {
        Random ran = new Random();

        int pos = ran.nextInt( ind.getGenotipo().length );
        if( ind.getGenotipo()[ pos ] == 0 )
            ind.getGenotipo()[ pos ] = 1;
        else
            ind.getGenotipo()[ pos ] = 0;

        ind.calcularFitness();
    }
}