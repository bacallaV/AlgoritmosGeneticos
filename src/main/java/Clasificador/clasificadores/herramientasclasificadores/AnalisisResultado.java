/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clasificador.clasificadores.herramientasclasificadores;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Valdo
 */
public class AnalisisResultado {
    private ArrayList<String> clases;
    private double [][] eficiencia;
    private ArrayList<Patron> instancias;
    private int good;
    
    public AnalisisResultado(ArrayList<Patron> instancias){
        this.clases = new ArrayList<>();
        this.eficiencia = null;
        this.instancias = instancias;
        this.good = 0;
        calcularEficiencia();
    }
    
    public void calcularEficiencia(){        
        for(Patron p: this.instancias){
            if(!this.clases.contains(p.getClase()))
                clases.add(p.getClase());
        }
        
        int m = this.clases.size();
        this.eficiencia = new double[m][m+1];
        
        int r,c;
        for(Patron p: this.instancias){
            r = clases.indexOf(p.getClase());
            c = clases.indexOf(p.getClaseResultante());
            this.eficiencia[r][c]++;
        }
        
        int num;
        for(int x=0; x<this.eficiencia.length; x++){
            num=0;
            for(Patron p: this.instancias){
                if(this.clases.get(x).contains(p.getClase()))
                    num++;
            }
            good += this.eficiencia[x][x];
            this.eficiencia[x][x]/=num;
            this.eficiencia[x][x]*=100;
        }
        
    }

    @Override
    public String toString() {
        double a = ((double)this.good/(double)this.instancias.size())*100;
        
        DecimalFormat f = new DecimalFormat("#.00");
        String aux = "";
        
        for(int r=0; r<this.eficiencia.length; r++){
            for(int c=0; c<this.eficiencia.length; c++){
                aux+=String.format("%.2f", this.eficiencia[r][c]);
                if(r==c)
                    aux+="%";
                aux+="\t";
            }
            aux+="\n";
        }
        aux+="Eficiencia del metodo: "+String.format("%.2f", a)+"%\n";
        return aux;
    }

    public ArrayList<Patron> getInstancias() {
        return instancias;
    }

    public int getGood() {
        return good;
    }

    public double[][] getEficiencia() {
        return eficiencia;
    }
    
    
    
}
