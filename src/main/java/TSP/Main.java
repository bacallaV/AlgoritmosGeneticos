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
        GeneradorInstancias.cargarMatriz("20.txt");
        
        Genetico g = new Genetico(15000, 40, 5000, 17, GeneradorInstancias.INSTANCIAS.length);
        g.evolucionar();
        
        System.out.println();
    }
}
