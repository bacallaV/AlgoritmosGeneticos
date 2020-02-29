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
public class Cruza {
    public static boolean MAXIMIZACION = true;
    public static boolean MINIMIZACION = false;
    
    public static Individuo porMascara(int mascara[], Individuo padre, Individuo madre, boolean maxmin){
        int genotipo1 [] = new int[mascara.length];
        int genotipo2 [] = new int[mascara.length];
        
        //Recorremos la mascara de cruza
        for(int b=0; b<mascara.length; b++){
            if(mascara[b]==1){
                genotipo1[b]=madre.getGenotipo()[b];
                genotipo2[b]=padre.getGenotipo()[b];
            }else{
                genotipo1[b]=padre.getGenotipo()[b];
                genotipo2[b]=madre.getGenotipo()[b];              
            }
        }
        
        Individuo hijo1 = new Individuo(genotipo1);
        Individuo hijo2 = new Individuo(genotipo2);
        
        if(maxmin){
            if(hijo1.getFitness()>hijo2.getFitness())
                return hijo1;
            else
                return hijo2;
        }else{
            if(hijo1.getFitness()<hijo2.getFitness())
                return hijo1;
            else
                return hijo2;
        }
    }
}
