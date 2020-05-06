package NSAT;

import java.util.Random;

public class Seleccion {
    public static Individuo porTorneo( Poblacion pob ) {
        // Asumimos que el primero es el mejor
        Individuo mejor = new Individuo( pob.getPoblacion().get(0) );

        for ( Individuo i: pob.getPoblacion() ) {
            if( i.getFitness() > mejor.getFitness() )
                mejor = new Individuo( i );
        }

        return mejor;
    }

    public static Individuo aleatoria( Poblacion pob ) {
        Individuo aleatorio;

        Random ran = new Random();
        int pos = ran.nextInt( pob.getPoblacion().size() );

        aleatorio = pob.getPoblacion().get( pos );
        // No importa que regrese por referencia, pues no se manipula la instancia
        return aleatorio;
    }
}