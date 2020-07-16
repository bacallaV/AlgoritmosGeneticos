package Clasificador;

import java.util.Arrays;

public class Individuo {
    private int [] genotipo;
    private int fitness;

    public Individuo() {
        this.genotipo = new int[ Herramientas.instancias.get(0).getVector().length ];
        inicializarAleatoriamente();
        calcularFitness();
    }

    public Individuo( int[] genotipo ) {
        this.genotipo = new int[ genotipo.length ];
        for( int i = 0; i < genotipo.length; i++ )
            this.genotipo[i] = genotipo[i];
            
        this.calcularFitness();
    }
        
    public Individuo( Individuo ind ) {
        this.genotipo = new int[ ind.getGenotipo().length ];
        for( int i = 0; i < ind.getGenotipo().length; i++ )
            this.genotipo[i] = ind.getGenotipo()[i];

        this.fitness = ind.getFitness();
    }

    private void inicializarAleatoriamente() {
        boolean hayUnos = false;
        for (int i = 0; i < genotipo.length; i++) {
            double ran = Math.random();

            if( ran > 0.5 ) {
                genotipo[i] = 1;
                hayUnos = true;
            }
            else
                genotipo[i] = 0;
        }

        if( !hayUnos )
            genotipo[0] = 1;
    }
    
    public void calcularFitness() {
        this.fitness = 0;
        Herramientas.md.clasificar( Herramientas.instancias, this.genotipo );
        this.fitness = Herramientas.md.getAr().getGood();
    }

    @Override
    public String toString() {
        String aux = Arrays.toString(this.genotipo) + "\n" + this.fitness;
        
        return aux;
    }
    
    /* Getters & Setters */
    
    public int[] getGenotipo() {
        return this.genotipo;
    }
    
    public int getFitness() {
        return this.fitness;
    }
    
    public void setGenotipo(int genotipo[]) {
        this.genotipo = genotipo.clone();
        calcularFitness();
    }

}