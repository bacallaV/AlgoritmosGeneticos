package NSAT;

import java.util.Random;

public class Muta {
    public static void aleatoria( Individuo ind ) {
        // Se alterara una veinteava parte del genetico del individuo, por referencia
        int numPos = (int) ( ind.getGenotipo().length / 20 );

        Random ran = new Random();

        for (int i = 0; i < numPos; i++) {
            int pos = ran.nextInt( ind.getGenotipo().length );
            ind.getGenotipo()[ pos ] = !ind.getGenotipo()[ pos ];
        }

        ind.calcularFitness();
    }
}