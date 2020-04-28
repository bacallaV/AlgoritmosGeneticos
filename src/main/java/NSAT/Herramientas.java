package NSAT;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Herramientas {
    public static ArrayList<int[]> clausulas = null;
    public static int nElementos = -1;
    public static int rangoVal = -1;

    public static void cargarClausulas() {
        clausulas = new ArrayList<>();
        nElementos = 0;
        rangoVal = 0;

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
                        rangoVal = Integer.parseInt(tokens.nextToken());
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
        rangoVal = 0;

        String linea;
        ArrayList<String> lineas = new ArrayList<>();
        
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
            for (int l = 0; l < lineas.size(); l++) {
                String aux = lineas.get(l);
                StringTokenizer tokens = new StringTokenizer(aux, " ");

                if( l == 0 ) {
                    // Ej. 100 550 3
                    // Obtenemos el rango de los numeros (100)
                    rangoVal = Integer.parseInt(tokens.nextToken());
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
}