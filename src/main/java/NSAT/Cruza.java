/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NSAT;

/**
 *
 * @author valdo
 */
public class Cruza {
    private static int[] mascara;

    public static Individuo porMascara(Individuo padre, Individuo madre) {
        // Si aun no se instancia la mascara
        if( mascara == null ) {
            mascara = new int[ padre.getGenotipo().length ];
            for (int i = 0; i < mascara.length; i++) {
                if( i%2 == 0 || i%3 == 0 )
                    mascara[i] = 1; //Padre, mayoria porque es el elegido porTorneo
                else
                    mascara[i] = 0; //Madre
            }
        }

        boolean genotipo1 [] = new boolean[padre.getGenotipo().length];
        boolean genotipo2 [] = new boolean[madre.getGenotipo().length];
        
        //Recorremos la mascara de cruza
        for(int i = 0; i < mascara.length; i++) {
            if( mascara[i] == 1 ){ // Padre
                genotipo1[i] = padre.getGenotipo()[i];
                genotipo2[i] = madre.getGenotipo()[i];
            }else{ // Madre
                genotipo1[i] = madre.getGenotipo()[i];
                genotipo2[i] = padre.getGenotipo()[i];              
            }
        }
        
        Individuo hijo1 = new Individuo(genotipo1);
        Individuo hijo2 = new Individuo(genotipo2);
        
        if( hijo1.getFitness() > hijo2.getFitness() )
            return hijo1;
        return hijo2;
    }
}
