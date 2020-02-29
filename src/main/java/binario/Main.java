/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

import java.util.LinkedList;

/**
 *
 * @author valdo
 */
public class Main {
    public static void main(String args[]){
        GeneticoV1 gen = new GeneticoV1(40,30,500, true);
        gen.evolucionar();
        System.out.println();
    }
}
