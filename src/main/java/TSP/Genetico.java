/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author valdo
 */
public class Genetico {
    private int numG;
    private double pMuta;
    private Poblacion pobActual;
    private Individuo mejor;
    
    public Genetico(int numG, double pMuta, int tamPob, int ciudadInicial, int numCiudades) {
        this.numG = numG;
        this.pMuta = pMuta;
        //Generamos la poblacion inicial de manera aleatoria
        this.pobActual = new Poblacion(tamPob, ciudadInicial, numCiudades);
        this.mejor = new Individuo(this.pobActual.getPoblacion().get(0).getGenotipo());
    }
    
    public void evolucionar(){
        //Se generan las nuevas poblaciones
        for(int g = 0; g < this.numG; g++){
            Poblacion nueva = new Poblacion();
            for(int i=0; i<this.pobActual.getPoblacion().size(); i++){
                //Proceso de seleccion
                Individuo madre = Seleccion.aleatoria(pobActual);
                Individuo padre = Seleccion.aleatoria(pobActual);
                //Proceso de cruza
                Individuo hijo;
                    hijo = Cruza.asexual(padre, madre);
                //Proceso de mutacion
                if(Math.random()*100<this.pMuta)
                    Muta.aleatoria(hijo);
                //El hijo generado se agrega a la nueva poblacion
                nueva.getPoblacion().add(hijo);
                
                if(hijo.getFitness() < this.mejor.getFitness()){
                    this.mejor.setGenotipo(hijo.getGenotipo());
                    this.mejor.calcularFitness();
                }
            }
                System.out.println("Generacion: " + g + " - Mejor:" + this.mejor);
            //Actualizar la poblacion actual
            this.pobActual = new Poblacion(nueva);
        }
        guardarMejor();
    }
    
    private void guardarMejor(){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
    
        try{
            f=new File( this.mejor.getGenotipo().length + "_" + this.mejor.getGenotipo()[0] + "_mejorIndividuo.txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            
            for (int i = 0; i < this.mejor.getGenotipo().length; i++) {
                    if(i==this.mejor.getGenotipo().length-1)
                        wr.println(this.mejor.getGenotipo()[i]);
                    else
                        wr.print(this.mejor.getGenotipo()[i]+",");
            }
            
            wr.print(this.mejor.getFitness());
            
            wr.close();
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ha sucedido un error. "+ e);
        } 
    }
    
}
