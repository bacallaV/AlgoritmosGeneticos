package NSAT;

public class Main {
    public static void main(String[] args) {
        Herramientas.cargarClausulas( "G2-100-550-1.txt" );
        
        Genetico genetico = new Genetico(8000, 25, 1000);
        genetico.evolucionar(  );

        Herramientas.guardarMejor( genetico.getMejor() );

        System.out.println( genetico.getMejor() );
    }
}