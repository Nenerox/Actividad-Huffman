import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Leer archivo
        String rutaArchivo = "DR.zeus.txt";
        TXTReader lector = new TXTReader();
        String contenido = lector.leerArchivoComoString(new File(rutaArchivo));

        if (contenido.isEmpty()) {
            System.out.println("El archivo está vacío");
            return;
        }

        System.out.println("=== CONTENIDO ORIGINAL ===");
        System.out.println(contenido);
        System.out.println("\n");

        // 2. Obtener caracteres únicos y frecuencias
        TablaFrecuencias tablaFrecuencias = new TablaFrecuencias();
        String simbolos = tablaFrecuencias.ordenarCaracteresUnicos(contenido);
        
        double[] frecuenciasDouble = new double[256];
        tablaFrecuencias.calcularFrecuencia(frecuenciasDouble, contenido);

        // Convertir a frecuencias absolutas (enteras)
        int[] frecuencias = new int[simbolos.length()];
        for (int i = 0; i < simbolos.length(); i++) {
            char c = simbolos.charAt(i);
            int contador = 0;
            for (char ch : contenido.toCharArray()) {
                if (ch == c) contador++;
            }
            frecuencias[i] = contador;
        }

        System.out.println("=== CARACTERES ÚNICOS ===");
        System.out.println(simbolos);
        System.out.println("\n");

        // 3. Generar códigos Huffman
        ValoresBinarios valoresBinarios = new ValoresBinarios();
        ArrayList<String> codigos = valoresBinarios.valores(simbolos, frecuencias);

        // Crear mapa de caracteres a códigos
        Map<Character, String> huffmanCodes = new HashMap<>();
        for (int i = 0; i < simbolos.length(); i++) {
            huffmanCodes.put(simbolos.charAt(i), codigos.get(i));
        }

        System.out.println("=== CÓDIGOS HUFFMAN ===");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' -> " + entry.getValue());
        }
        System.out.println("\n");

        // =============== ENCRIPTACIÓN ===============
        System.out.println("============ ENCRIPTACIÓN ============\n");

        // 4. Codificar el texto a binario
        Encoder encoder = new Encoder(huffmanCodes);
        String textoCodificado = encoder.textToBinary(contenido);

        System.out.println("=== TEXTO EN BINARIO ===");
        System.out.println(textoCodificado);
        System.out.println("\n");

        // 5. Agrupar de 8 en 8
        String textoCodificadoSeparado = encoder.separateByEight(textoCodificado);
        System.out.println("=== BINARIO AGRUPADO DE 8 EN 8 ===");
        System.out.println(textoCodificadoSeparado);
        System.out.println("\n");

        // 6. Convertir a ASCII (ENCRIPTADO)
        String bitsSimple = textoCodificado;
        String textoEncriptado = ToASCII.convertirATexto(bitsSimple);

        System.out.println("=== TEXTO ENCRIPTADO (EN ASCII) ===");
        System.out.println(textoEncriptado);
        System.out.println("\n");

        // =============== DESENCRIPTACIÓN ===============
        System.out.println("============ DESENCRIPTACION ============\n");

        // 7. Convertir ASCII de vuelta a binario
        String bitsRecuperados = String.format("%8s", Integer.toBinaryString(textoEncriptado.charAt(0) & 0xFF)).replace(' ', '0');
        for (int i = 1; i < textoEncriptado.length(); i++) {
            bitsRecuperados += String.format("%8s", Integer.toBinaryString(textoEncriptado.charAt(i) & 0xFF)).replace(' ', '0');
        }

        System.out.println("=== BINARIO RECUPERADO ===");
        System.out.println(bitsRecuperados);
        System.out.println("\n");

        // 8. Decodificar de vuelta al texto original
        Decoder decoder = new Decoder(huffmanCodes);
        String textoDesencriptado = decoder.binaryToText(bitsRecuperados);

        System.out.println("=== TEXTO DESENCRIPTADO (ORIGINAL) ===");
        System.out.println(textoDesencriptado);
        System.out.println("\n");

        // Verificar si coincide
        System.out.println("=== VERIFICACION ===");
        System.out.println("¿Original == Desencriptado? " + contenido.equals(textoDesencriptado));
    }
}