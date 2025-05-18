import java.util.Map;

public class SmartBruteForce {

    /**
     * Descifra el texto cifrado probando todas las claves y usando análisis estadístico
     * para seleccionar la mejor coincidencia respecto a un texto de muestra.
     */
    public String runConAnalisis(String encryptedText, String sampleText, StatisticalAnalyzer analyzer) {
        CaesarCipher cipher = new CaesarCipher();
        double mejorSimilitud = Double.MAX_VALUE;
        String mejorResultado = "";


        Map<Character, Double> freqSample = analyzer.calcularFrecuencias(sampleText);

        for (int key = 1; key < CaesarCipher.ALPHABET.length; key++) {
            String descifrado = cipher.decrypt(encryptedText, key);
            Map<Character, Double> freqActual = analyzer.calcularFrecuencias(descifrado);
            double distancia = calcularDistancia(freqActual, freqSample);

            if (distancia < mejorSimilitud) {
                mejorSimilitud = distancia;
                mejorResultado = "Clave: " + key + "\n\n" + descifrado;

            }
        }

        return mejorResultado;
    }

    /**
     * Calcula la diferencia cuadrática total entre dos distribuciones de frecuencias.
     */
    private double calcularDistancia(Map<Character, Double> a, Map<Character, Double> b) {
        double total = 0.0;
        for (char c : CaesarCipher.ALPHABET) {
            double diff = a.getOrDefault(c, 0.0) - b.getOrDefault(c, 0.0);
            total += diff * diff;
        }
        return total;
    }
}