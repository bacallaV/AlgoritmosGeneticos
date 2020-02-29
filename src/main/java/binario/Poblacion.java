/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

import java.util.LinkedList;
import java.util.Random;

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
    public Poblacion(int numIndividuos){
        this.poblacion = new LinkedList<>();
        inicializarAleatoriamente(numIndividuos);
        
    }
    
    //Creacion de poblacion a partir de una muestra
    public Poblacion(LinkedList<Individuo> muestra, int tamano){
        this.poblacion = new LinkedList<>();
        for(int i=0; i<muestra.size(); i++)
            this.poblacion.add(new Individuo(muestra.get(i).getGenotipo()));
        
    }
    
    //Crear una nueva poblacion en base a otra
    public Poblacion(Poblacion nueva){
        this.poblacion = new LinkedList<>();
        for(Individuo aux: nueva.getPoblacion())
            this.poblacion.add(new Individuo(aux.getGenotipo()));
    }

    public void inicializarAleatoriamente(int numIndividuos) {
        //Un proceso iterativo con respecto a tamano
        for(int i=0; i<numIndividuos; i++)
            poblacion.add(new Individuo(31));
    }
    
    //Genera una muestra aleatoria de la poblacion en base a un porcentaje designado
    public LinkedList<Individuo> generarMuestraAleatoria(double porcentaje){
        int c = (int)(this.poblacion.size()*porcentaje)/100;
        
        LinkedList<Individuo> muestra = new LinkedList<>();
        
        Random ran = new Random();
        int pa = ran.nextInt(this.poblacion.size()-1);
                
        for(int i=0; i<c; i++){
            muestra.add(this.poblacion.get(pa));
            pa = ran.nextInt(this.poblacion.size()-1);
        }
        
        return muestra;
    }
    
    //Genera una muestra en base a un porcentaje, desde los individuos con valor de fitness más alto
    public LinkedList<Individuo> generarMuestraMaximizacion(double porcentaje){
        int c = (int)(this.poblacion.size()*porcentaje)/100;
        
        LinkedList<Individuo> ordenado = (LinkedList<Individuo>) this.poblacion.clone();
        LinkedList<Individuo> muestra = new LinkedList<>();
        
        ordenado.sort( (ind1, ind2) -> (Individuo.compare(ind1, ind2)) );
                
        for(int i=0; i<c; i++){
            Individuo aux = new Individuo(ordenado.get(ordenado.size()-1-i).getGenotipo());
            muestra.add(aux);
        }
        
        return muestra;
    }
    
    //Genera una muestra en base a un porcentaje, desde los individuos con valor de fitness más bajo
    public LinkedList<Individuo> generarMuestraMinimizacion(double porcentaje){
        int c = (int)(this.poblacion.size()*porcentaje)/100;
        
        LinkedList<Individuo> ordenado = (LinkedList<Individuo>) this.poblacion.clone();
        LinkedList<Individuo> muestra = new LinkedList<>();
        
        ordenado.sort( (ind1, ind2) -> (Individuo.compare(ind1, ind2)) );
                
        for(int i=0; i<c; i++){
            Individuo aux = new Individuo(ordenado.get(i).getGenotipo());
            muestra.add(aux);
        }
        
        return muestra;
    }
    
    //Método para ordenar una población en base a una Población
    public Poblacion ordenar(){
        Poblacion ordenado = new Poblacion(this);
        ordenado.getPoblacion().sort( (ind1, ind2) -> (Individuo.compare(ind1, ind2)) );
        
        return ordenado;
    }
    
    /* Getters y Setters */
    
    public LinkedList<Individuo> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(LinkedList<Individuo> poblacion) {
        this.poblacion = poblacion;
    }
        
    /* Fin */

    
}
