/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NReinas;

import java.util.ArrayList;

/**
 *
 * @author valdo
 */
public class Poblacion {
    private ArrayList<Individuo> poblacion;
    
    public Poblacion() {
        this.poblacion = new ArrayList<>();
    }
    
    // Creación de una población aleatoria
    public Poblacion( int numIndividuos, int nReinas ) {
        this.poblacion = new ArrayList<>();
        for (int i = 0; i < numIndividuos; i++) {
            this.poblacion.add( new Individuo(nReinas) );
        }
    }
    
    // Creación de una población de partir de otra
    public Poblacion( Poblacion pob ) {
        this.poblacion = new ArrayList<>();
        for (Individuo i : pob.getPoblacion()) {
            this.poblacion.add( new Individuo(i.getGenotipo()) );
        }
    }
    
    /* Getter */

    public ArrayList<Individuo> getPoblacion() {
        return poblacion;
    }
    
}
