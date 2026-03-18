import java.io.*;

public class TXTReader {
    public String leerArchivoComoString(File archivo) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")) continue;
                sb.append(linea).append(' ');
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return sb.toString();
    }
}