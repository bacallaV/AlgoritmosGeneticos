/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import Herramientas.GeneradorInstancias;

/**
 *
 * @author valdo
 */
public class Main {
    public static void main(String[] args) {
        GeneradorInstancias.cargarMatriz("1000.txt");
        
        Genetico g = new Genetico(10000, 50, 250, 4, GeneradorInstancias.INSTANCIAS.length);
//        g.evolucionar(Herramientas.cargarIndividuo("500_130_i_08-03-2020.txt"));
        g.evolucionar();
        System.out.println(g.getMejor());
        Herramientas.guardarMejor(g.getMejor());
        
        Genetico g3 = new Genetico(10000, 50, 250, 997, GeneradorInstancias.INSTANCIAS.length);
        g3.evolucionar();
        System.out.println(g3.getMejor());
        Herramientas.guardarMejor(g3.getMejor());
        
        System.out.println();
    }
}
