package Clasificador;

public class Cruza {
    private static int[] mascara;

    public static Individuo porMascara(Individuo padre, Individuo madre) {
        if( mascara == null )
            generarMascaraAleatoria();

        int genotipo1 [] = new int[ padre.getGenotipo().length ];
        int genotipo2 [] = new int[ madre.getGenotipo().length ];
        
        //Recorremos la mascara de cruza
        for(int i = 0; i < mascara.length; i++) {
            if( mascara[i] == 1 ){ // Padre
                genotipo1[i] = padre.getGenotipo()[i];
                genotipo2[i] = madre.getGenotipo()[i];
            } else { // Madre
                genotipo1[i] = madre.getGenotipo()[i];
                genotipo2[i] = padre.getGenotipo()[i];              
            }
        }
        
        Individuo hijo1 = new Individuo(genotipo1);
        Individuo hijo2 = new Individuo(genotipo2);
        
        if( hijo1.getFitness() > hijo2.getFitness() )
            return hijo1;
        return hijo2;
    }

    public static void generarMascaraAleatoria() {
        mascara = new int[ Herramientas.instancias.get(0).getVector().length ];

        for (int i = 0; i < mascara.length; i++) {
            double ran = Math.random();

            if( ran > 0.5 )
                mascara[i] = 1;
        }
    }
}