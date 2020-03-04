/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.util.LinkedList;

/**
 *
 * @author valdo
 */
public class Poblacion {
    private LinkedList<Individuo> poblacion;
    
    public Poblacion(){
        this.poblacion = new LinkedList<>();
    }
    
    //Creacion aleatoria de la poblacion
    public Poblacion(int numIndividuos, int ciudadInicial, int numCiudades){
        this.poblacion = new LinkedList<>();
        inicializarAleatoriamente(numIndividuos, ciudadInicial, numCiudades);
    }
    
    //Crear una nueva poblacion en base a otra
    public Poblacion(Poblacion nueva){
        this.poblacion = new LinkedList<>();
        for(Individuo aux: nueva.getPoblacion())
            this.poblacion.add(new Individuo(aux.getGenotipo()));
    }
    
    private void inicializarAleatoriamente(int numIndividuos, int ciudadInicial, int numCiudades) {
        //Un proceso iterativo con respecto al numero de Individuos
        for(int i=0; i<numIndividuos; i++)
            poblacion.add(new Individuo(ciudadInicial, numCiudades));
    }
    
    //Método para ordenar una población en base a una Población
    public Poblacion ordenar(){
        Poblacion ordenado = new Poblacion(this);
        ordenado.getPoblacion().sort( (ind1, ind2) -> (Individuo.compare(ind1, ind2)) );
        
        return ordenado;
    }
    
    /* Getters & Setters */

    public LinkedList<Individuo> getPoblacion() {
        return poblacion;
    }
    
    
}
