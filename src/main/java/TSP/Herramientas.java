/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import Herramientas.GeneradorInstanciasTSP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author valdo
 */
public class Herramientas {
    public static void guardarMejor(Individuo mejor){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    
        try{
            f=new File( mejor.getGenotipo().length + "_" + mejor.getGenotipo()[0] + "_i_" + formato.format(fecha) + ".txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            
            //NUMERO DE CIUDADES
            wr.println(GeneradorInstanciasTSP.INSTANCIAS.length);
            
            //CIUDAD INICIAL
            wr.println(mejor.getGenotipo()[0]);
            
            //FITNESS
            wr.println(mejor.getFitness());
            
            //GENOTIPO
            for (int i = 0; i < mejor.getGenotipo().length; i++) {
                    if(i==mejor.getGenotipo().length-1)
                        wr.println(mejor.getGenotipo()[i]);
                    else
                        wr.print(mejor.getGenotipo()[i]+",");
            }
            
            wr.close();
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ha sucedido un error. "+ e);
        } 
    }
    
    public static Individuo cargarIndividuo(String ruta){
        String linea;
        LinkedList<String> lineas = new LinkedList<>();
        Individuo individuo = null;
        
        try {

            //Abrimos el archivo seleccionado
            File abre = new File(ruta);
            FileReader archivos = new FileReader(abre);
            BufferedReader lee = new BufferedReader(archivos);

            //Agregamos cada renglon a la coleccion "lineas"
            while ((linea = lee.readLine()) != null) {
                lineas.add(linea);
            }
            lee.close();
            
            //NUMERO DE CIUDADES
            String aux = lineas.get(0);
            int numCiudades = Integer.parseInt(aux);
            int genotipo[] = new int[numCiudades];

            //GENOTIPO
            //Tokenizar cada una de las lineas
            aux = lineas.get(3);
            StringTokenizer tokens = new StringTokenizer(aux, ",");

            int c = 0;
            while(tokens.hasMoreTokens()){
                int valor = Integer.parseInt(tokens.nextToken());
                genotipo[c]=valor;
                c++;
            }
            
            individuo = new Individuo(genotipo);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
        
        return individuo;
    }
}
