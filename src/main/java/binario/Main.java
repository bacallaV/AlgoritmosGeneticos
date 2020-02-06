/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binario;

/**
 *
 * @author valdo
 */
public class Main {
    public static void main(String args[]){
        Individuo i1 = new Individuo(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1});
        Individuo i2 = new Individuo(31);
                
//        Individuo ind = new Individuo(new int[]{1,0,0,0,0,0});
//        ind.calcularFitness();
        
        System.out.println(i1+"\n"+i2);
    }
}
