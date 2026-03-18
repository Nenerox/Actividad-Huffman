import java.util.Map;

class Encoder {

    private Map<Character, String> huffmanCodes;

    public Encoder(Map<Character, String> huffmanCodes) {
        this.huffmanCodes = huffmanCodes;
    }

    // Convertir texto a binario usando los códigos Huffman
    public String textToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            if (huffmanCodes.containsKey(c)) {
                binary.append(huffmanCodes.get(c));
            }
        }
        
        return binary.toString();
    }

    // Separar binario de 8 en 8
    public String separateByEight(String bits) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < bits.length(); i += 8) {
            if (i > 0) {
                result.append(" ");
            }
            if (i + 8 <= bits.length()) {
                result.append(bits.substring(i, i + 8));
            } else {
                result.append(bits.substring(i));
            }
        }
        
        return result.toString();
    }

    // Proceso completo
    public String encode(String text) {
        String binary = textToBinary(text);
        return separateByEight(binary);
    }
}