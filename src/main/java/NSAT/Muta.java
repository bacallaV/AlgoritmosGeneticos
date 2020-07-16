package NSAT;

import java.util.Random;

public class Muta {
    public static void aleatoria( Individuo ind ) {
        Random ran = new Random();

        int pos = ran.nextInt( ind.getGenotipo().length );
        ind.getGenotipo()[ pos ] = !ind.getGenotipo()[ pos ];

        ind.calcularFitness();
    }
}