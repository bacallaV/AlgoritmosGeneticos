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
public class Genetico {
    private int numG;
    private double pMuta;
    private Poblacion pobActual;
    private Individuo mejor;
    private int[] mascara;
    
    public Genetico(int numG, double pMuta, int tamPob, int nReinas) {
        this.numG = numG;
        this.pMuta = pMuta;
        //Generamos la poblacion inicial de manera aleatoria
        this.pobActual = new Poblacion(tamPob, nReinas);
        this.mejor = Seleccion.porTorneo(this.pobActual);
        generarMascara( nReinas );
    }
    
    public void evolucionar(){
        //Se generan las nuevas poblaciones
        for(int g = 0; g < this.numG; g++){
            Poblacion nueva = new Poblacion();
            for(int i=0; i < this.pobActual.getPoblacion().size(); i++){
                //Proceso de seleccion
                Individuo madre = Seleccion.porTorneo(pobActual);
                Individuo padre = Seleccion.aleatoria(pobActual);
                //Proceso de cruza
                Individuo hijo;
                    hijo = Cruza.porMascara(this.mascara, padre, madre);
                //Proceso de mutacion
                if(Math.random()*100<this.pMuta)
                    Muta.aleatoria(hijo, 2);
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if( hijo.getFitness() < this.mejor.getFitness() ){
                    this.mejor.setGenotipo(hijo.getGenotipo());
                }
            }
            
            System.out.println(
                    "Generacion: "+g+
                    " Casi Mejor: "+Seleccion.porTorneo(nueva).getFitness()+
                    " Mejor: "+this.mejor.getFitness()
                );
            
            if ( !nueva.getPoblacion().contains(this.mejor) ) {
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

    private void generarMascara( int tam ) {
        this.mascara = new int[tam];
        for (int i = 0; i < tam; i++) {
            if ( i%2 == 0 ) 
                this.mascara[i] = 0;
            else
                this.mascara[i] = 1;
        }
    }
}
