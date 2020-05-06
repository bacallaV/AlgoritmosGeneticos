/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NSAT;

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
    
    //Creacion aleatoria de la poblacion
    public Poblacion(int numIndividuos) {
        this.poblacion = new ArrayList<>();
        inicializarAleatoriamente(numIndividuos);
    }
    
    //Crear una nueva poblacion en base a otra
    public Poblacion(Poblacion nueva){
        this.poblacion = new ArrayList<>();
        for(Individuo aux: nueva.getPoblacion())
            this.poblacion.add( new Individuo( aux ) );
    }
    
    private void inicializarAleatoriamente(int numIndividuos) {
        //Un proceso iterativo con respecto al numero de Individuos
        for(int i=0; i<numIndividuos; i++)
            poblacion.add( new Individuo() );
    }
    
    //Método para ordenar una población en base a una Población
    public Poblacion ordenar() {
        Poblacion ordenado = new Poblacion(this);
        ordenado.getPoblacion().sort( (ind1, ind2) -> ( Individuo.compare(ind1, ind2) ) );
        
        return ordenado;
    }
    
    /* Getters & Setters */

    public ArrayList<Individuo> getPoblacion() {
        return poblacion;
    }
    
    
}
