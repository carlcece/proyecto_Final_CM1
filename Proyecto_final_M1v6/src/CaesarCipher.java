/**
 * Clase que implementa el cifrado y descifrado César.
 * Utiliza un alfabeto personalizado que incluye letras, números y símbolos.
 */
public class CaesarCipher {

    public static final char[] ALPHABET = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '.', ',', '!', '?', ':', ';', '\'', '"', ' ', '(', ')'
    };

    private int indexOfChar(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) return i;
        }
        return -1;
    }

    /**
     * Cifra un texto con la clave dada.
     */
    public String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            int index = indexOfChar(c);
            if (index != -1) {
                int shiftedIndex = (index + key) % ALPHABET.length;
                result.append(ALPHABET[shiftedIndex]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Descifra un texto con la clave dada.
     */
    public String decrypt(String text, int key) {
        return encrypt(text, ALPHABET.length - (key % ALPHABET.length));
    }
}