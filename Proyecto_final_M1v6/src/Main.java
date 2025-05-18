import javax.swing.*;
import java.io.File;
import java.util.Scanner;

/**
 * Clase principal del programa que permite al usuario cifrar, descifrar o analizar archivos de texto
 * utilizando el cifrado César. Ofrece un menú de opciones por consola.
 */
public class Main {
    public static void main(String[] args) {
        // Inicialización de clases principales
        CaesarCipher cipher = new CaesarCipher();
        FileManager fileManager = new FileManager();
        CustomValidator validator = new CustomValidator();
        StatisticalAnalyzer analyzer = new StatisticalAnalyzer();
        SmartBruteForce smartBruteForce = new SmartBruteForce();
        Scanner scanner = new Scanner(System.in);

        // Configurar estilo gráfico nativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Cifrar archivo");
            System.out.println("2. Descifrar archivo con clave");
            System.out.println("3. Descifrar con fuerza bruta");
            System.out.println("4. Análisis estadístico");
            System.out.println("0. Salir");

            opcion = validator.validarOpcion(scanner);

            switch (opcion) {
                case 1 -> {
                    System.out.println("📂 Selecciona el archivo que deseas cifrar:");
                    File input = fileManager.seleccionarArchivo("Selecciona archivo a cifrar");

                    if (validator.esArchivoInvalido(input, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }
                    System.out.println("📄 Archivo seleccionado:" + input.getAbsolutePath());

                    int key = validator.validarClave(scanner);
                    String content = fileManager.leerArchivo(input);
                    if (validator.estaCifrado(content)) {
                        System.out.println("⚠️ El archivo ya parece estar cifrado. Operación cancelada.");
                        break;
                    }

                    String encrypted = "[CIFRADO_CESAR]\n" + cipher.encrypt(content, key);
                    System.out.println("💾 Especifica dónde guardar el archivo cifrado:");
                    File output = fileManager.seleccionarGuardar("cifrado.txt");
                    if (output == null) {
                        System.out.println("❌ Error: archivo de salida no especificado.");
                        break;
                    }
                    System.out.println("📄 Archivo salida: " + output.getAbsolutePath());
                    fileManager.guardarArchivo(output, encrypted);
                    System.out.println("✅ Archivo cifrado guardado exitosamente.");
                }

                case 2 -> {
                    System.out.println("📂 Selecciona el archivo que deseas descifrar:");
                    File input = fileManager.seleccionarArchivo("Selecciona archivo cifrado");

                    if (validator.esArchivoInvalido(input, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }

                    System.out.println("📄 Archivo seleccionado:" + input.getAbsolutePath());
                    int key = validator.validarClave(scanner);
                    String content = fileManager.leerArchivo(input);
                    if (validator.estaDescifrado(content)) {
                        System.out.println("⚠️ El archivo ya fue descifrado anteriormente. Operación cancelada.");
                        break;
                    }
                    if (validator.estaCifrado(content)) {
                        content = content.substring("[CIFRADO_CESAR]".length()).trim();
                    }

                    String decrypted = cipher.decrypt(content, key);
                    System.out.println("💾 Especifica dónde guardar el archivo descifrado:");
                    File output = fileManager.seleccionarGuardar("descifrado.txt");
                    if (output == null) {
                        System.out.println("❌ Error: archivo de salida no especificado.");
                        break;
                    }

                    System.out.println("📄 Archivo salida: " + output.getAbsolutePath());
                    fileManager.guardarArchivo(output,"[DESCIFRADO_CESAR]\n" + decrypted);
                    System.out.println("✅ Archivo descifrado guardado correctamente.");
                }

                case 3 -> {
                    // Fuerza bruta
                    System.out.println("📂 Selecciona el archivo cifrado:");
                    File encryptedFile = fileManager.seleccionarArchivo("Selecciona archivo cifrado");
                    if (validator.esArchivoInvalido(encryptedFile, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }
                    System.out.println("📄 Archivo seleccionado: " + encryptedFile.getAbsolutePath());

                    System.out.println("📊 Selecciona el archivo de muestra para comparación:");
                    File sampleFile = fileManager.seleccionarArchivo("Selecciona archivo de muestra");
                    if (validator.esArchivoInvalido(sampleFile, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }
                    System.out.println("📄 Archivo muestra: " + sampleFile.getAbsolutePath());
                    String encrypted = fileManager.leerArchivo(encryptedFile);
                    String sample = fileManager.leerArchivo(sampleFile);

                    if (validator.estaDescifrado(encrypted)) {
                        System.out.println("⚠️ El archivo ya fue descifrado anteriormente. No se puede analizar.");
                        break;
                    }
                    if (validator.estaCifrado(encrypted)) {
                        encrypted = encrypted.substring("[CIFRADO_CESAR]".length()).trim();
                    }
                    String mejorResultado = smartBruteForce.runConAnalisis(encrypted, sample, analyzer);
                    System.out.println("💾 Selecciona dónde guardar el descifrado más probable:");
                    File output = fileManager.seleccionarGuardar("descifrado_inteligente.txt");
                    if (output == null) {
                        System.out.println("❌ Error: archivo no seleccionado.");
                        break;
                    }

                    System.out.println("📄 Archivo salida: " + output.getAbsolutePath());
                    fileManager.guardarArchivo(output,"[DESCIFRADO_CESAR]\n" + mejorResultado);
                    System.out.println("✅ Resultado del descifrado guardado correctamente.");
                }

                case 4 -> {
                    // Análisis estadístico
                    System.out.println("📂 Selecciona el archivo cifrado a analizar:");
                    File encryptedFile = fileManager.seleccionarArchivo("Selecciona archivo cifrado");

                    if (validator.esArchivoInvalido(encryptedFile, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }
                    System.out.println("📄 Archivo seleccionado: " + encryptedFile.getAbsolutePath());

                    System.out.println("📊 Selecciona el archivo de muestra para comparación estadística:");
                    File sampleFile = fileManager.seleccionarArchivo("Selecciona archivo de muestra");
                    if (validator.esArchivoInvalido(sampleFile, fileManager)) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }

                    System.out.println("📄 Archivo muestra: " + sampleFile.getAbsolutePath());

                    String encrypted = fileManager.leerArchivo(encryptedFile);
                    String sample = fileManager.leerArchivo(sampleFile);
                    String result = analyzer.analyze(encrypted, sample);
                    System.out.println("💾 Selecciona dónde guardar el análisis estadístico:");

                    File output = fileManager.seleccionarGuardar("analisis_estadistico.txt");
                    if (output == null) {
                        System.out.println("❌ Error: archivo no seleccionado o vacío.");
                        break;
                    }
                    System.out.println("📄 Archivo salida: " + output.getAbsolutePath());
                    fileManager.guardarArchivo(output, result);
                    System.out.println("✅ Resultado del análisis guardado correctamente.");
                }

                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}