import java.util.HashMap;
import java.util.Map;

public class Decoder {
    private Map<String, Character> reverseMap;

    public Decoder(Map<Character, String> huffmanCodes) {
        reverseMap = new HashMap<>();

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
    }
    public static String convertirABinario(String texto) {
        StringBuilder binario = new StringBuilder();

        for (char c : texto.toCharArray()) {
            String bin = String.format("%8s", Integer.toBinaryString(c))
                    .replace(' ', '0');
            binario.append(bin);
        }

        return binario.toString();
    }

    public String binaryToText(String bits) {
        StringBuilder resultado = new StringBuilder();
        StringBuilder actual = new StringBuilder();

        for (char bit : bits.toCharArray()) {
            actual.append(bit);

            if (reverseMap.containsKey(actual.toString())) {
                resultado.append(reverseMap.get(actual.toString()));
                actual.setLength(0);
            }
        }

        return resultado.toString();
    }
}