import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StatisticalAnalyzer {

    /**
     * Compara las frecuencias de caracteres entre un texto cifrado y uno de muestra.
     */
    public String analyze(String encrypted, String sample) {
        Map<Character, Double> freqEncrypted = calcularFrecuencias(encrypted);
        Map<Character, Double> freqSample = calcularFrecuencias(sample);
        return generarComparacion(freqEncrypted, freqSample);
    }

    /**
     * Calcula la frecuencia porcentual de cada carácter del alfabeto en un texto dado.
     */
    public Map<Character, Double> calcularFrecuencias(String texto) {
        Map<Character, Integer> contador = new HashMap<>();
        int total = 0;

        for (char c : texto.toUpperCase().toCharArray()) {
            for (char a : CaesarCipher.ALPHABET) {
                if (c == a) {
                    contador.put(c, contador.getOrDefault(c, 0) + 1);
                    total++;
                    break;
                }
            }
        }

        Map<Character, Double> frecuencias = new TreeMap<>();
        for (char c : CaesarCipher.ALPHABET) {
            int count = contador.getOrDefault(c, 0);
            frecuencias.put(c, (count * 100.0) / (total == 0 ? 1 : total));
        }

        return frecuencias;
    }

    /**
     * Genera una tabla comparativa de frecuencias entre dos textos.
     */
    private String generarComparacion(Map<Character, Double> enc, Map<Character, Double> ref) {
        StringBuilder sb = new StringBuilder();
        sb.append("Caracter\tCifrado(%)\tMuestra(%)\n");

        for (char c : CaesarCipher.ALPHABET) {
            double f1 = enc.getOrDefault(c, 0.0);
            double f2 = ref.getOrDefault(c, 0.0);
            sb.append(c).append("\t\t")
                    .append(String.format("%.2f", f1)).append("\t\t")
                    .append(String.format("%.2f", f2)).append("\n");
        }

        return sb.toString();
    }


}

