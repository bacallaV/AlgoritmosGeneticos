package NSAT;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Herramientas {
    public static ArrayList<int[]> clausulas = null;
    public static int nElementos = -1;
    public static int tamGenotipo = -1;
    public static String nombreArchivo;

    public static void cargarClausulas() {
        clausulas = new ArrayList<>();
        nElementos = 0;
        tamGenotipo = 0;

        String linea;
        ArrayList<String> lineas = new ArrayList<>();
        
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

                // Guardamos el nombre del archivo
                nombreArchivo = abre.getName();

                //Agregamos cada renglon a la coleccion "lineas"
                while ((linea = lee.readLine()) != null) {
                    lineas.add(linea);
                }
                lee.close();

                //Tokenizar cada una de las lineas
                for (int l = 0; l < lineas.size(); l++) {
                    String aux = lineas.get(l);
                    StringTokenizer tokens = new StringTokenizer(aux, " ");

                    if( l == 0 ) {
                        // Ej. 100 550 3
                        // Obtenemos el rango de los numeros (100)
                        tamGenotipo = Integer.parseInt(tokens.nextToken());
                        // Ignoramos el numero de clausulas
                        tokens.nextToken();
                        // Obtenemos cuantos elementos tiene cada clausula (3)
                        nElementos = Integer.parseInt(tokens.nextToken());
                    }
                    // Obtenemos clausula por clausula, con una variable temporal del arreglo
                    // del txt. Despues lo agergamos a la coleccion de clausulas.
                    int x = 0;
                    int[] temp = new int[nElementos];
                    while( tokens.hasMoreTokens() ) {
                        int valor = Integer.parseInt(tokens.nextToken());
                        temp[x] = valor;
                        x++;
                    }
                    clausulas.add( temp );
                }
          
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
    }
    
    public static void cargarClausulas( String ruta ) {
        clausulas = new ArrayList<>();
        nElementos = 0;
        tamGenotipo = 0;

        String linea;
        ArrayList<String> lineas = new ArrayList<>();
        
        try {

            //Abrimos el archivo seleccionado
            File abre = new File(ruta);
            FileReader archivos = new FileReader(abre);
            BufferedReader lee = new BufferedReader(archivos);

            // Guardamos el nombre del archivo
            nombreArchivo = abre.getName();

            //Agregamos cada renglon a la coleccion "lineas"
            while ((linea = lee.readLine()) != null) {
                lineas.add(linea);
            }
            lee.close();

            //Tokenizar cada una de las lineas
            for (int l = 0; l < lineas.size(); l++) {
                String aux = lineas.get(l);
                StringTokenizer tokens = new StringTokenizer(aux, " ");

                if( l == 0 ) {
                    // Ej. 100 550 3
                    // Obtenemos el rango de los numeros (100)
                    tamGenotipo = Integer.parseInt(tokens.nextToken());
                    // Ignoramos el numero de clausulas
                    tokens.nextToken();
                    // Obtenemos cuantos elementos tiene cada clausula (3)
                    nElementos = Integer.parseInt(tokens.nextToken());
                }else {
                    // Obtenemos clausula por clausula, con una variable temporal del arreglo
                    // del txt. Despues lo agergamos a la coleccion de clausulas.
                    int x = 0;
                    int[] temp = new int[nElementos];
                    while( tokens.hasMoreTokens() ) {
                        int valor = Integer.parseInt(tokens.nextToken());
                        temp[x] = valor;
                        x++;
                    }
                    clausulas.add( temp );
                }
            }
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
            
        }
    }

    public static void guardarMejor(Individuo mejor){
        File f;
        FileWriter w;
        BufferedWriter bw;
        PrintWriter wr;
        
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    
        try{
            f=new File( nombreArchivo.substring(0, nombreArchivo.length()-4) + "_mejor_" + formato.format(fecha) + ".txt");
            w=new FileWriter(f);
            bw=new BufferedWriter (w);
            wr=new PrintWriter(bw);
            
            //RANGO DE VALORES DE LAS CLAUSULAS
            wr.println( mejor.getGenotipo().length - 1 );
            
            //NUMERO DE CLAUSULAS
            wr.println( nElementos );
            
            //FITNESS
            wr.println( mejor.getFitness() );
            
            //GENOTIPO
            for (int i = 0; i < mejor.getGenotipo().length - 1; i++) {
                if( mejor.getGenotipo()[i] )
                    wr.print( "1," );
                else
                    wr.print( "0," );
            }
            if( mejor.getGenotipo()[ mejor.getGenotipo().length-1 ] )
                wr.println( "1" );
            else
                wr.println( "0" );
            
            wr.close();
            bw.close();
        }catch(Exception e){
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
            
            //RANGO DE VALORES DE LAS CLAUSULAS
            String aux = lineas.get(0);
            int tamGenotipo = Integer.parseInt(aux);
            boolean genotipo[] = new boolean[tamGenotipo+1];

            //NUMERO DE CLAUSULAS 
            //no son importantes para instanciar al individuo

            //FITNESS
            //se calculara

            //GENOTIPO
            //Tokenizar cada una de las lineas
            aux = lineas.get(3);
            StringTokenizer tokens = new StringTokenizer(aux, ",");

            int c = 0;
            while( tokens.hasMoreTokens() ){
                int valor = Integer.parseInt( tokens.nextToken() );
                if( valor == 1 )
                    genotipo[c] = true;
                else
                    genotipo[c] = false;
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