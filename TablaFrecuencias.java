import java.util.HashMap;

public class TablaFrecuencias {
    private HashMap<Character, Double> frecuencias;

    public TablaFrecuencias() {
        frecuencias = new HashMap<>();
    }

    public String ordenarCaracteresUnicos(String contenido) {
        boolean[] visto = new boolean[256];
        StringBuilder clases = new StringBuilder();

        for (int i = 0; i < contenido.length(); i++) {
            char c = contenido.charAt(i);
            if (c < 256 && !visto[c]) {
                visto[c] = true;
                clases.append(c);
            }
        }
        return clases.toString();
    }

    public double[] calcularFrecuencia(double[] frecuencias, String contenido) {
        double longitud = contenido.length();
        if (longitud == 0) return frecuencias;

        for (int i = 0; i < frecuencias.length; i++) {
            char caracter = (char) i;
            int contador = 0;

            for (char c : contenido.toCharArray()) {
                if (c == caracter) contador++;
            }
            frecuencias[i] = (contador / longitud) * 100.0;
        }
        return frecuencias;
    }

}