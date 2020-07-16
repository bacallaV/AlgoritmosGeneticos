/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clasificador.clasificadores;

import Clasificador.clasificadores.herramientasclasificadores.PatronRepresentativo;
import Clasificador.clasificadores.herramientasclasificadores.Patron;
import Clasificador.clasificadores.herramientasclasificadores.AnalisisResultado;
import Clasificador.Herramientas;
import java.util.ArrayList;

/**
 *
 * @author CRUZLEIJA
 */
public class MinimaDistancia {

    private ArrayList<PatronRepresentativo> representativos;
    private AnalisisResultado ar;
    
    public MinimaDistancia() {
        this.representativos = new ArrayList<PatronRepresentativo>();
        ar = null;
    }

    
    public void entrenar(ArrayList<Patron> instancias) {
       
        // agregamos el primer representativo 
        representativos.add( new PatronRepresentativo(instancias.get(0)) );
        // recorrer todas las instancias 
        for (int x = 1; x < instancias.size(); x++) {
            Patron aux = instancias.get(x);
            // es verificar la existencia o no el representativo
            for (int i = 0; i < representativos.size(); i++ ) {
                // ACUMULAR AL REPRESENTATIVO QUE LE CORRESPONDA
                if( representativos.get(i).getClase().equals( aux.getClase() ) ) {
                    representativos.get(i).acumular( aux );
                    break;
                }
                // crear un nuevo patron representativo
                // agregamos el primer representativo 
                if( i == representativos.size() - 1 )
                    representativos.add( new PatronRepresentativo(aux) );
            }
        }

        for(PatronRepresentativo aux: representativos)
            aux.actualizar();
        
    }

    public void clasificar(Patron patron, int[] mascara) {
        int iMenor = 0;
        double dMenor = Herramientas.calcularDistanciaEuclidiana( patron, this.representativos.get(0), mascara );
        
        // en proceso iterativo calcular las distancias con respecto a los representativos
        for(int i = 1; i < this.representativos.size();i+=1){
            double dN = Herramientas.calcularDistanciaEuclidiana( patron, this.representativos.get(i), mascara );
            if( dN < dMenor ) {
                dMenor = dN;
                iMenor = i;
            }
        }

        patron.setClaseResultante( this.representativos.get(iMenor).getClase() );
    }

    public void clasificar(ArrayList<Patron> patrones, int[] mascara) {
       for(Patron p: patrones)
           clasificar(p, mascara);

       this.ar = new AnalisisResultado(patrones);
    }

    public AnalisisResultado getAr() {
        return ar;
    }
}
