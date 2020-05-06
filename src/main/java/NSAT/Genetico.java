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
public class Genetico {
    private int numG;
    private double pMuta;
    private Poblacion pobActual;
    private Individuo mejor;
    
    public Genetico(int numG, double pMuta, int tamPob) {
        this.numG = numG;
        this.pMuta = pMuta;
        //Generamos la poblacion inicial de manera aleatoria
        this.pobActual = new Poblacion(tamPob);
        this.mejor = Seleccion.porTorneo(this.pobActual);
    }
    
    public void evolucionar() {
        //Se generan las nuevas poblaciones
        for(int g = 0; g < this.numG; g++) {
            Poblacion nueva = new Poblacion();
            for(int i = 0; i < this.pobActual.getPoblacion().size(); i++){
                //Proceso de seleccion
                Individuo padre = Seleccion.aleatoria(pobActual);
                Individuo madre = Seleccion.porTorneo(pobActual);
                //Proceso de cruza
                Individuo hijo;
                    hijo = Cruza.porMascara(padre, madre);
                //Proceso de mutacion
                double numRan = Math.random() * 100;
                if(numRan < this.pMuta) {
                    Muta.aleatoria(hijo);
                }
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if(hijo.getFitness() > this.mejor.getFitness()) {
                    this.mejor.setGenotipo(hijo.getGenotipo());
                    this.mejor.calcularFitness();
                }
            }
            
            System.out.println(
                    "Generacion: " + g +
                    " Mejor: " + this.mejor.getFitness()
                    );
            
            if (!nueva.getPoblacion().contains(this.mejor)) {
                nueva.getPoblacion().set(0, this.mejor);
            }
            
            //Actualizar la poblacion actual
            this.pobActual = new Poblacion(nueva);
        }
    }
    
    public void evolucionar(Individuo mejor){
        //Seteamos al mejor y también dentro de la población
        this.mejor = mejor;
        this.pobActual.getPoblacion().set(0, this.mejor);
        //Llamamos al método evolucionar
        evolucionar();
    }

    public Individuo getMejor() {
        return mejor;
    }
    
}
