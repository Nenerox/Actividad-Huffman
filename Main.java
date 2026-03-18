import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("El archivo esta vacio");
            return;
        }

        System.out.println("=== CONTENIDO ORIGINAL ===");
        System.out.println(contenido);
        System.out.println("\n");

        // 2. Obtener caracteres unicos y frecuencias
        TablaFrecuencias tablaFrecuencias = new TablaFrecuencias();
        String simbolos = tablaFrecuencias.ordenarCaracteresUnicos(contenido);
        
        int[] frecuencias = new int[simbolos.length()];
        for (int i = 0; i < simbolos.length(); i++) {
            char c = simbolos.charAt(i);
            int contador = 0;
            for (char ch : contenido.toCharArray()) {
                if (ch == c) contador++;
            }
            frecuencias[i] = contador;
        }

        System.out.println("=== CARACTERES UNICOS ===");
        System.out.println("Total de caracteres unicos: " + simbolos.length());
        System.out.println();

        for (int i = 0; i < simbolos.length(); i++) {
            char c = simbolos.charAt(i);
            if (c == '\n') System.out.println((i+1) + ". [SALTO DE LINEA]");
            else if (c == ' ') System.out.println((i+1) + ". [ESPACIO]");
            else System.out.println((i+1) + ". '" + c + "'");
        }
        System.out.println();

        // 3. Generar codigos Huffman
        ValoresBinarios valoresBinarios = new ValoresBinarios();
        ArrayList<String> codigos = valoresBinarios.valores(simbolos, frecuencias);

        Map<Character, String> huffmanCodes = new HashMap<>();
        for (int i = 0; i < simbolos.length(); i++) {
            huffmanCodes.put(simbolos.charAt(i), codigos.get(i));
        }

        System.out.println("=== CODIGOS HUFFMAN ===");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char c = entry.getKey();
            if (c == '\n') System.out.println("[SALTO DE LINEA] -> " + entry.getValue());
            else if (c == ' ') System.out.println("[ESPACIO] -> " + entry.getValue());
            else System.out.println("'" + c + "' -> " + entry.getValue());
        }
        System.out.println("\n");

        System.out.println("============ ENCRIPTACION ============\n");

        // 4. Codificar el texto a binario
        Encoder encoder = new Encoder(huffmanCodes);
        String textoCodificado = encoder.textToBinary(contenido);

        System.out.println("=== BINARIO (primeros 100 caracteres) ===");
        System.out.println(textoCodificado.substring(0, Math.min(100, textoCodificado.length())) + "...");
        System.out.println("Longitud total: " + textoCodificado.length() + " bits\n");

        // 5. Guardar binario encriptado en archivo
        try {
            FileWriter fw = new FileWriter("encriptado.bin");
            fw.write(textoCodificado);
            fw.close();
            System.out.println("Archivo encriptado guardado en: encriptado.bin\n");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        System.out.println("============ DESENCRIPTACION ============\n");

        // 6. Leer binario del archivo
        TXTReader lectorBin = new TXTReader();
        String bitsRecuperados = lectorBin.leerArchivoComoString(new File("encriptado.bin"));

        System.out.println("=== BINARIO RECUPERADO (primeros 100 caracteres) ===");
        System.out.println(bitsRecuperados.substring(0, Math.min(100, bitsRecuperados.length())) + "...");
        System.out.println("Longitud: " + bitsRecuperados.length() + " bits\n");

        // 7. Decodificar
        Decoder decoder = new Decoder(huffmanCodes);
        String textoDesencriptado = decoder.binaryToText(bitsRecuperados);

        System.out.println("=== TEXTO DESENCRIPTADO ===");
        System.out.println(textoDesencriptado);
        System.out.println("\n");

        // Verificacion
        System.out.println("=== VERIFICACION ===");
        boolean esIgual = contenido.equals(textoDesencriptado);
        System.out.println("Original == Desencriptado? " + esIgual);
        
        if (!esIgual) {
            System.out.println("Longitud original: " + contenido.length());
            System.out.println("Longitud desencriptado: " + textoDesencriptado.length());
        }
    }
}