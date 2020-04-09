/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author valdo
 */
public class GeneradorInstanciasTSP {
    public static int INSTANCIAS[][];
        
    public static int[][] generarMatriz(int numCiudades, int Ran){
        int matriz[][] = new int[numCiudades][numCiudades];
        
        Random ran = new Random();
        
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if(i==j)
                    break;
                else{
                    int val = ran.nextInt(Ran)+1;
                    matriz[i][j]=val;
                    matriz[j][i]=val;
                }
            }
        }
        
        return matriz;
    }
    
    public static void guardarMatriz(int matriz[][], String archivo){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
    
        try{
            f=new File(archivo+".txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if(j==matriz.length-1)
                        wr.println(matriz[i][j]);
                    else
                        wr.print(matriz[i][j]+",");
                }
            }
            
            wr.close();
            bw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Ha sucedido un error. "+ e);
        } 
    }
    
    public static int[][] cargarMatriz(){
        int matriz[][] = null;
        String linea;
        LinkedList<String> lineas = new LinkedList<>();
        
        try {
            //Llamamos el metodo que permite cargar la ventana
            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file.setDialogTitle("Seleccione el archivo");
            int returnVal = file.showOpenDialog(file);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //Abrimos el archivo seleccionado
                File abre = file.getSelectedFile();
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);

                //Agregamos cada renglon a la coleccion "lineas"
                while ((linea = lee.readLine()) != null) {
                    lineas.add(linea);
                }
                lee.close();

                //Tokenizar cada una de las lineas
                matriz = new int[lineas.size()][lineas.size()];
                for (int l = 0; l < lineas.size(); l++) {
                    String aux = lineas.get(l);
                    StringTokenizer tokens = new StringTokenizer(aux, ",");
                    
                    int c = 0;
                    while(tokens.hasMoreTokens()){
                        int valor = Integer.parseInt(tokens.nextToken());
                        matriz[l][c]=valor;
                        c++;
                    }
                }
          
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
        
        INSTANCIAS = matriz;
        
        return matriz;
    }
    
    public static int[][] cargarMatriz(String ruta){
        int matriz[][] = null;
        String linea;
        LinkedList<String> lineas = new LinkedList<>();
        
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

            //Tokenizar cada una de las lineas
            matriz = new int[lineas.size()][lineas.size()];
            for (int l = 0; l < lineas.size(); l++) {
                String aux = lineas.get(l);
                StringTokenizer tokens = new StringTokenizer(aux, ",");

                int c = 0;
                while(tokens.hasMoreTokens()){
                    int valor = Integer.parseInt(tokens.nextToken());
                    matriz[l][c]=valor;
                    c++;
                }
            }
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
        
        INSTANCIAS = matriz;
        
        return matriz;
    }
}
