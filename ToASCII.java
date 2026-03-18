public class ToASCII {
    public static String convertirATexto(String code) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < code.length(); i += 8) {
            String byteString = code.substring(i, Math.min(i + 8, code.length()));
            int asciiValue = Integer.parseInt(byteString, 2);
            resultado.append((char) asciiValue);
        }
        return resultado.toString();
    }
}
