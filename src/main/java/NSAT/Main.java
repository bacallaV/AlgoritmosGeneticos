package NSAT;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Herramientas.cargarClausulas( "G2-100-550-1.txt" );
        ArrayList<int[]> hola = Herramientas.clausulas;

        Individuo ind = new Individuo( Herramientas.rangoVal );
        System.out.println( ind );
    }
}