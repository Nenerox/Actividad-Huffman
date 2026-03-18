import java.io.File;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "DR.zeus.txt";
        TXTReader lector = new TXTReader();
        String contenido = lector.leerArchivoComoString(new File(rutaArchivo));

        double[] frecuencias = new double[256];
        TablaFrecuencias tablaFrecuencias = new TablaFrecuencias();

        String contenidoEnString = tablaFrecuencias.ordenarCaracteresUnicos(contenido);

        tablaFrecuencias.calcularFrecuencia(frecuencias, contenido);

    }
}