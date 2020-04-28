package NSAT;

public class Individuo {
    private boolean genotipo[];
    private int fitness;


    /* Contructores */
    // Inicializar aleatoriamente
    public Individuo( int tamGenotipo ) {
        this.genotipo = new boolean[tamGenotipo+1];
        this.fitness = 0;
        inicializarAleatoriamente();
        calcularFitness();
    }

    // Inicializar a partir de un genotipo dado
    public Individuo( boolean[] genotipo ) {
        this.genotipo = new boolean[genotipo.length];

        for (int i = 0; i < genotipo.length; i++)
            this.genotipo[i] = genotipo[i];
    }

    // Inicializar a partir de otro Individuo
    public Individuo( Individuo ind ) {
        this.genotipo = new boolean[ind.genotipo.length];

        for (int i = 0; i < ind.genotipo.length; i++)
            this.genotipo[i] = ind.genotipo[i];
    }

    private void inicializarAleatoriamente() {
        double ran = Math.random();
        // 50/50 de false o true
        for (int i = 0; i < this.genotipo.length; i++) {
            if( ran < 0.5 )
                this.genotipo[i] = false;
            else
                this.genotipo[i] = true;
                
            ran = Math.random();
        }
    }

    public void calcularFitness() {
        if( Herramientas.clausulas == null )
            return;
        this.fitness = 0;
        // Recorremos cada clausula para evaluarlas una a una
        for (int[] clausula : Herramientas.clausulas) {
            for (int i = 0; i < clausula.length; i++) {
                if( clausula[i] >= 0 ) { // Si el numero es positivo
                    // Si el elemento nos da true, toda la clausula se valida
                    // por lo que ya no importa evaluar los demas elementos.
                    if( this.genotipo[ clausula[i] ] ) {
                        this.fitness++;
                        break;
                    }
                } else { // Si el numero es negativo
                    // Si el elemento negado (por ser numero negativo) nos da true,
                    // toda la clausula se valida.
                    int elemento = clausula[i] * -1;
                    if( !this.genotipo[ elemento ] == true ) {
                        this.fitness++;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String aux = "[";

        for (int i = 0; i < this.genotipo.length; i++) {
            if( this.genotipo[i] )
                aux += "1,";
            else
                aux += "0,";
        }
        // Para eliminar la ultima coma
        aux = aux.substring( 0, aux.length()-1 );

        aux += "]\n" + this.fitness;
        
        return aux;
    }

    /* Getters & Setters */
    /**
     * @return the genotipo
     */
    public boolean[] getGenotipo() {
        return genotipo;
    }

    /**
     * @return the fitness
     */
    public int getFitness() {
        return fitness;
    }

    /**
     * @param genotipo the genotipo to set
     */
    public void setGenotipo(boolean[] genotipo) {
        for (int i = 0; i < genotipo.length; i++)
            this.genotipo[i] = genotipo[i];
    }
}