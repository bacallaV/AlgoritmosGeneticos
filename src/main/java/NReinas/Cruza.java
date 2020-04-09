/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

/**
 *
 * @author valdo
 */
public class Cruza {
    public static Individuo porMascara( int[] mascara, Individuo padre, Individuo madre ) {
        int[] genPadre = padre.getGenotipo();
        int[] genMadre = madre.getGenotipo();
        
        int[] genHijo1 = new int[genPadre.length];
        int[] genHijo2 = new int[genPadre.length];
        // Creamos el genotipo del hijo de forma que no se repita ningun valor en su genotipo
        for (int i = 0; i < genPadre.length; i++) {
            genHijo1[i] = i;
            genHijo2[i] = i;
        }
        
        // Aqui se hace la cruza, de manera que no se repita ninguna posicion de las reinas
        int aux, pos;
        for (int i = 0; i < genPadre.length; i++) {
            if ( mascara[i] == 0 ) {
                // Intercambiamos la posicion de las reinas
                // Padre - 0, Madre - 1
                aux = genHijo1[i];
                genHijo1[i] = genPadre[i];
                pos = buscarPos(genHijo1, genHijo1[i], i);
                genHijo1[pos] = aux;
                // Madre - 0, Padre - 1
                aux = genHijo2[i];
                genHijo2[i] = genMadre[i];
                pos = buscarPos(genHijo2, genHijo2[i], i);
                genHijo2[pos] = aux;
            }else {
                // Intercambiamos la posicion de las reinas
                // Madre - 1, Padre - 0
                aux = genHijo1[i];
                genHijo1[i] = genMadre[i];
                pos = buscarPos(genHijo1, genHijo1[i], i);
                genHijo1[pos] = aux;
                // Padre - 1, Madre - 0
                aux = genHijo2[i];
                genHijo2[i] = genPadre[i];
                pos = buscarPos(genHijo2, genHijo2[i], i);
                genHijo2[pos] = aux;
            }
        }
        // Creamos a los hijos
        Individuo hijo1 = new Individuo( genHijo1 );
        Individuo hijo2 = new Individuo( genHijo2 );
        
        // Retornamos el hijo con menor fitness (o ataques)
        if ( hijo1.getFitness() < hijo2.getFitness() ) {
            return hijo1;
        }
        
        return hijo2;
    }

    private static int buscarPos(int[] genHijo, int nuevo, int nuevoPos) {
        int aux = -1;
        for (int i = 0; i < genHijo.length; i++) {
            if ( genHijo[i] == nuevo )
                if( i != nuevoPos )
                    return i;
                else
                    aux = i;
        }
        
        return aux;
    }
}
