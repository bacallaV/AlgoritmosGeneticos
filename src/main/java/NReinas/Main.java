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
public class Main {
    public static void main(String[] args) {
        Genetico g = new Genetico(800, 65, 500, 1000);
        g.evolucionar();
        Herramientas.guardarMejor( g.getMejor() );
        System.out.println( g.getMejor() );
    }
}
