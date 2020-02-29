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
        Individuo.instancias = GeneradorInstancias.cargarMatriz("5.txt");
//        Individuo i = new Individuo(new int[]{0,3,4,1,2});
        Individuo i1 = new Individuo(1, 5);
        Individuo i2 = new Individuo(1, 5);
        Individuo i = Cruza.asexual(i1, i2);
        
        System.out.println(i);
    }
}
