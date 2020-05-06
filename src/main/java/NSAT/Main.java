package NSAT;

public class Main {
    public static void main(String[] args) {
        Herramientas.cargarClausulas( "G2-100-550-5.txt" );
        
        Genetico genetico = new Genetico(500, 80, 700);
        genetico.evolucionar( Herramientas.cargarIndividuo("G2-100-550-5_mejor_06-05-2020.txt") );

        Herramientas.guardarMejor( genetico.getMejor() );

        System.out.println( genetico.getMejor() );
    }
}