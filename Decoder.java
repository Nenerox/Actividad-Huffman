public class Decoder {

    public static String convertirABinario(String texto) {
        StringBuilder binario = new StringBuilder();

        for (char c : texto.toCharArray()) {
            String bin = String.format("%8s", Integer.toBinaryString(c))
                    .replace(' ', '0');
            binario.append(bin);
        }

        return binario.toString();
    }
}