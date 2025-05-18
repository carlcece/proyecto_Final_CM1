import java.io.File;
import java.util.Scanner;

/**
 * Clase encargada de validar entradas del usuario por consola y archivos.
 */
public class CustomValidator {

    /**
     * Valida que la opción ingresada sea un número entre 0 y 5.
     */
    public int validarOpcion(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese opción (0-5): ");
            String entrada = scanner.nextLine();
            try {
                int opcion = Integer.parseInt(entrada);
                if (opcion >= 0 && opcion <= 5) return opcion;
                System.out.println("❌ Solo se admiten números entre 0 y 5.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada no válida. Ingrese solo un número.");
            }
        }
    }

    /**
     * Solicita y valida una clave numérica dentro del rango permitido (1 a 36).
     * El método seguirá solicitando la entrada hasta que el usuario proporcione un valor válido.
     */

    public int validarClave(Scanner scanner) {
        while (true) {
            System.out.print("🔐 Ingrese la clave (número entre 1 y 36): ");
            String entrada = scanner.nextLine();
            try {
                int clave = Integer.parseInt(entrada);
                if (clave >= 1 && clave <= 36) {
                    return clave;
                }
                System.out.println("❌ La clave debe estar entre 1 y 36.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada no válida. Debe ser un número.");
            }
        }
    }

    /**
     * Verifica si el archivo es inválido o tiene contenido vacio.
     */
    public boolean esArchivoInvalido(File archivo, FileManager fileManager) {
        if (archivo == null) {
            return true; // Es inválido porque es null
        }
        try {
            String contenido = fileManager.leerArchivo(archivo);
            return contenido.trim().isEmpty(); // Es inválido si está vacío
        } catch (Exception e) {
            return true; // Error al leer = inválido
        }
    }

    public boolean estaCifrado(String contenido) {
        return contenido.startsWith("[CIFRADO_CESAR]");
    }

    public boolean estaDescifrado(String contenido) {
        return contenido.startsWith("[DESCIFRADO_CESAR]");
    }

}