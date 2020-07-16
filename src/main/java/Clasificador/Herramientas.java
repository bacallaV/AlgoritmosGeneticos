/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clasificador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Clasificador.clasificadores.MinimaDistancia;
import Clasificador.clasificadores.herramientasclasificadores.Patron;

/**
 *
 * @author CRUZLEIJA
 */
public class Herramientas {
    
    public static MinimaDistancia md;
    public static ArrayList<Patron> instancias;
    public static String nombreArchivo;
    
    public static void leerDatos() {
        md = new MinimaDistancia();
        instancias = new ArrayList<>();
        String texto, aux;
        LinkedList<String> lista = new LinkedList<>();
        
        try {
            //llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode( JFileChooser.FILES_ONLY );
            file.showOpenDialog(file);
            //abrimos el archivo seleccionado
            File abre = file.getSelectedFile();

            //recorremos el archivo y lo leemos
            if (abre != null) {
                // Guardamos el nombre del archivo
                nombreArchivo = abre.getName();

                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                while ((aux = lee.readLine()) != null) {
                    texto = aux;
                    lista.add(texto);
                }
                lee.close();
                //System.out.println(lista.size());

                ArrayList<String> lista2 = new ArrayList<>();
                String clase = "";
                for (int i = 0; i < lista.size(); i++) {
                    StringTokenizer st = new StringTokenizer(lista.get(i), ",");

                    while (st.hasMoreTokens()) {
                        lista2.add(st.nextToken());
                    }

                    double[] vector = new double[lista2.size() - 1];

                    for (int x = 0; x < lista2.size() - 1; x++) {
                        vector[x] = Double.parseDouble(lista2.get(x));
                    }

                    clase = lista2.get(lista2.size()-1);
                    // a la coleccion de patrones se agrega un nuevo patron
                    instancias.add(new Patron(vector, clase));
                   // patrones.add();
                    lista2.clear();

                }
          
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
       
       
     
    }
    
    public static double calcularDistanciaEuclidiana(Patron a, Patron b){
        double aux = 0;
        for (int x=0;x<a.getVector().length;x++){
            aux+=Math.pow((a.getVector()[x]-b.getVector()[x]),2);
        }
        return Math.sqrt(aux);
        
        
    }

    public static double calcularDistanciaEuclidiana(Patron a, Patron b, int[] mascara) {
        double aux = 0;

        for (int x = 0; x < a.getVector().length; x++) {
            if( mascara[x] == 1 )
                aux += Math.pow( (a.getVector()[x]-b.getVector()[x]), 2 );
        }
        
        return Math.sqrt(aux);
    }
    
    public static void guardarMejor(Individuo mejor) {
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
    
        try{
            f=new File( nombreArchivo.substring(0, nombreArchivo.length()-4) + "_mejor_" + mejor.getFitness() + ".txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            
            //TAMAÑO DEL VECTOR
            wr.println( mejor.getGenotipo().length );
            
            //FITNESS
            wr.println( mejor.getFitness() );
            
            //GENOTIPO
            for (int i = 0; i < mejor.getGenotipo().length - 1; i++)
                wr.print( mejor.getGenotipo()[i] + "," );
            
            wr.println( mejor.getGenotipo()[ mejor.getGenotipo().length-1 ] );
            
            wr.close();
            bw.close();
        } catch( Exception e ) {
            JOptionPane.showMessageDialog(null,"Ha sucedido un error. "+ e);
        } 
    }
    
    public static Individuo cargarIndividuo(String ruta){
        String linea;
        ArrayList<String> lineas = new ArrayList<>();
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
            
            //TAMAÑO DEL GENOTIPO
            String aux = lineas.get(0);
            int tamGenotipo = Integer.parseInt(aux);
            int genotipo[] = new int[ tamGenotipo ];

            //FITNESS
            //se calculara

            //GENOTIPO
            //Tokenizar cada una de las lineas
            aux = lineas.get(2);
            StringTokenizer tokens = new StringTokenizer(aux, ",");

            int c = 0;
            while( tokens.hasMoreTokens() ){
                int valor = Integer.parseInt( tokens.nextToken() );
                genotipo[ c ] = valor;
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
