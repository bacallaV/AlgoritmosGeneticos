/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import Herramientas.GeneradorInstanciasTSP;

/**
 *
 * @author valdo
 */
public class Main {
    public static void main(String[] args) {
        GeneradorInstanciasTSP.cargarMatriz("20.txt");
        
        Genetico g = new Genetico(25000, 45, 500, 4, GeneradorInstanciasTSP.INSTANCIAS.length);
//        g.evolucionar(Herramientas.cargarIndividuo("20_Mejores/20_4_i_08-03-2020.txt"));
        g.evolucionar();
        System.out.println(g.getMejor());
        Herramientas.guardarMejor(g.getMejor());
        
        System.out.println();
    }
}
