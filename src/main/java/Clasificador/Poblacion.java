package Clasificador;

import java.util.ArrayList;

public class Poblacion {
    private ArrayList<Individuo> poblacion;
    
    public Poblacion() {
        this.poblacion = new ArrayList<>();
    }
    
    //Creacion aleatoria de la poblacion
    public Poblacion( int numIndividuos ) {
        this.poblacion = new ArrayList<>();
        inicializarAleatoriamente( numIndividuos );
    }
    
    //Crear una nueva poblacion en base a otra
    public Poblacion( Poblacion nueva ) {
        this.poblacion = new ArrayList<>();
        for(Individuo aux: nueva.getPoblacion())
            this.poblacion.add( new Individuo( aux ) );
    }
    
    private void inicializarAleatoriamente( int numIndividuos ) {
        //Un proceso iterativo con respecto al numero de Individuos
        for(int i = 0; i < numIndividuos; i++)
            poblacion.add( new Individuo() );
    }
    
    /* Getters & Setters */

    public ArrayList<Individuo> getPoblacion() {
        return poblacion;
    }
}