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