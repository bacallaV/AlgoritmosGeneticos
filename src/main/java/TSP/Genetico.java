/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;


/**
 *
 * @author valdo
 */
public class Genetico {
    private int numG;
    private double pMuta;
    private Poblacion pobActual;
    private Individuo mejor;
    
    public Genetico(int numG, double pMuta, int tamPob, int ciudadInicial, int numCiudades) {
        this.numG = numG;
        this.pMuta = pMuta;
        //Generamos la poblacion inicial de manera aleatoria
        this.pobActual = new Poblacion(tamPob, ciudadInicial, numCiudades);
    }
    
    public void evolucionar(){
        this.mejor = Seleccion.porTorneo(this.pobActual);
        //Se generan las nuevas poblaciones
        for(int g = 0; g < this.numG; g++){
            Poblacion nueva = new Poblacion();
            for(int i=0; i<this.pobActual.getPoblacion().size(); i++){
                //Proceso de seleccion
                Individuo madre = Seleccion.porTorneo(pobActual);
                Individuo padre = Seleccion.aleatoria(pobActual);
                //Proceso de cruza
                Individuo hijo;
                    hijo = Cruza.asexual(padre, madre);
                //Proceso de mutacion
                if(Math.random()*100<this.pMuta)
                    Muta.aleatoria(hijo);
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if(hijo.getFitness() < this.mejor.getFitness()){
                    this.mejor.setGenotipo(hijo.getGenotipo());
                    this.mejor.calcularFitness();
                }
            }
            //Actualizar la poblacion actual
            this.pobActual = new Poblacion(nueva);
            
            System.out.println("Generacion: "+g+" Mejor: "+this.mejor);
        }
    }
    
    public void evolucionar(Individuo mejor){
        //Seteamos al mejor y también dentro de la población
        this.mejor = mejor;
        this.pobActual.getPoblacion().set(0, this.mejor);
        //Se generan las nuevas poblaciones
        for(int g = 0; g < this.numG; g++){
            Poblacion nueva = new Poblacion();
            for(int i = 0; i < this.pobActual.getPoblacion().size(); i++){
                //Proceso de seleccion
                Individuo madre = Seleccion.porTorneo(pobActual);
                Individuo padre = Seleccion.aleatoria(pobActual);
                //Proceso de cruza
                Individuo hijo;
                    hijo = Cruza.asexual(padre, madre);
                //Proceso de mutacion
                if(Math.random()*100<this.pMuta)
                    Muta.aleatoria(hijo);
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if(hijo.getFitness() < this.mejor.getFitness()){
                    this.mejor.setGenotipo(hijo.getGenotipo());
                    this.mejor.calcularFitness();
                }
            }
            //Actualizar la poblacion actual
            this.pobActual = new Poblacion(nueva);
            
            System.out.println("Generacion: "+g+" Mejor: "+this.mejor);
        }
    }

    public Individuo getMejor() {
        return mejor;
    }
    
}
