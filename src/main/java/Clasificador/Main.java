package Clasificador;

public class Main {
    public static void main(String[] args) {
        Herramientas.leerDatos();
        Genetico g = new Genetico( 3000, 65, 500 );
        g.evolucionar( Herramientas.cargarIndividuo( "wine_mejor_163.txt" ) );

        Herramientas.guardarMejor( g.getMejor() );

        System.out.println( g.getMejor() );
    }
}